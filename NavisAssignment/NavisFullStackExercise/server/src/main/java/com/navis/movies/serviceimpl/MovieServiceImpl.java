package com.navis.movies.serviceimpl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.navis.movies.dao.MoviesDAO;
import com.navis.movies.dto.Genre;
import com.navis.movies.dto.MovieResultDTO;
import com.navis.movies.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MoviesDAO moviesDAO;

	@Override
	public List<MovieResultDTO> getFeaturedMovies() throws SQLException { //service method for getting featured movies
		return moviesDAO.getFeaturedMovies();
	}

	@Override
	public Page<MovieResultDTO> searchByAll(Optional<String> actor, Optional<String> genre, Optional<String> title,
			Optional<Integer> index)throws SQLException { //service method for getting Page of all searched movies by all criteria. 
		try {  

			return moviesDAO.searchByAll(actor, genre, title, index);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public MovieResultDTO getMovieById(Optional<Integer> movie_id)throws SQLException {
		
			//service method for getting details for a movie by Id 

			MovieResultDTO movie = new MovieResultDTO();
			
			Set<String> actors = new HashSet<>();
			Set<String> genres = new HashSet<>();
			List<MovieResultDTO> movies = moviesDAO.getMovieById(movie_id);
			movies.stream().forEach(m -> {      //collecting values in a dto object including actorset & genre set for common. 
				String actor = m.getActor();
				String genre = m.getGenre();
				
				movie.setId(m.getId());
				movie.setPlot(m.getPlot());
				movie.setPoster(m.getPoster());
				movie.setRating(m.getRating());
				movie.setTitle(m.getTitle());
				movie.setSort(m.getSort());
				movie.setYear(m.getYear());
				movie.setStudio(m.getStudio());
				if (!actors.contains(actor))
					actors.add(actor);
				if (!genres.contains(genre))
					genres.add(genre);
				
				
			});

			movie.setActorSet(actors);
			movie.setGenreSet(genres);
			return movie;
	
	}

	@Override
	public List<Genre> getGenreList() {
		// TODO Auto-generated method stub
		return moviesDAO.getAllGenre();
	}

}
