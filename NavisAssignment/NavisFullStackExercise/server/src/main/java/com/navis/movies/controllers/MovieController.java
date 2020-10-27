package com.navis.movies.controllers;

import static com.navis.movies.Application.MOVIE_URL;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.navis.movies.dto.Genre;
import com.navis.movies.dto.MovieResultDTO;
import com.navis.movies.exceptions.MovieNotFoundException;
import com.navis.movies.exceptions.SearchQueryNotFound;
import com.navis.movies.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(MOVIE_URL)
public class MovieController {

	@Autowired
	MovieService movieService;

	@Operation(description = "Get featured Movies")
	@GetMapping("/featured") //endpoint for returning featured movies List.
	public List<MovieResultDTO> getFeaturedMovies() throws MovieNotFoundException, SQLException {
		
		List<MovieResultDTO> featuredMovies = movieService.getFeaturedMovies();
		
		if(featuredMovies.isEmpty())
			throw new MovieNotFoundException("Featured movies are not available");
		
		return featuredMovies;
	}

	@GetMapping("/search") //endpoint for returning Page of search queries having optional/query params/request params
	public Page<MovieResultDTO> searchByAll(@RequestParam Optional<String> actor, @RequestParam Optional<String> genre,
			@RequestParam Optional<String> title, @RequestParam Optional<Integer> index) throws MovieNotFoundException, SearchQueryNotFound, SQLException {

		Page<MovieResultDTO> movies = movieService.searchByAll(actor, genre, title, index);
		if(actor.isEmpty()&&genre.isEmpty()&&title.isEmpty())
			throw new SearchQueryNotFound("No Search Query available in request");
		if (movies.getContent().isEmpty())
			throw new MovieNotFoundException("No such movies are available");
		
		
		return movies;
	}

	@GetMapping("/details/{movieId}") //endpoint for returning movie details
	public MovieResultDTO getMovieById(@PathVariable Optional<Integer> movieId) throws MovieNotFoundException, MethodArgumentTypeMismatchException, SQLException{
		
		MovieResultDTO movie = movieService.getMovieById(movieId);
		if (movie.getTitle()==null)
			throw new MovieNotFoundException("Movie With id : "+movieId.get()+" is not available");
		
		return movieService.getMovieById(movieId);
	}
	
	@GetMapping("/genres") // endpoint for returning list of genres
	public List<Genre> getGenreList(){
	
		return movieService.getGenreList();
	}
}
