package com.navis.movies.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.navis.movies.dto.Genre;
import com.navis.movies.dto.MovieResultDTO;

public interface MovieService {
	
	public List<MovieResultDTO> getFeaturedMovies() throws SQLException;
	public Page<MovieResultDTO> searchByAll(Optional<String> actor, Optional<String> genre, Optional<String> title,Optional<Integer> index) throws SQLException;
	public MovieResultDTO getMovieById(Optional<Integer> movie_id)throws SQLException;
	public List<Genre> getGenreList();


}
