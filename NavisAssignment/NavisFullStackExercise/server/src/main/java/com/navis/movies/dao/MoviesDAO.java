package com.navis.movies.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.navis.movies.dto.Genre;
import com.navis.movies.dto.MovieResultDTO;

@Repository
public class MoviesDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int count() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM MOVIES", Integer.class); 		//Method to Get overall count for results exist in the table.
	}

	public List<MovieResultDTO> getFeaturedMovies() {    //Method to select featured movies from table by joining movies & Featured_movies Table
		final List<MovieResultDTO> movies = jdbcTemplate.query(
				"SELECT m.id, m.title, m.year, m.rating, m.poster,m.plot ,m.studio ,"
				+ "	 f.sort from movies m inner join featured_movies f on f.movie_id = m.id order by sort",
				(rs, rowNum) -> {
					MovieResultDTO dto = new MovieResultDTO();
					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setYear(rs.getString("year"));
					dto.setRating(rs.getString("rating"));
					dto.setPoster(rs.getString("poster"));
					dto.setSort(rs.getInt("sort"));
					dto.setPlot(rs.getString("plot"));
					dto.setStudio(rs.getString("studio"));

					return dto;
				});

		return movies;
	}


	public Page<MovieResultDTO> searchByAll(Optional<String> actor, Optional<String> genre, Optional<String> title,
			Optional<Integer> index) {   //Method to select searched movies by actor, genre, title from table by joining all Tables 

		Pageable page = PageRequest.of(index.orElse(0), 6);
		final List<MovieResultDTO> movies = jdbcTemplate.query(
				"Select DISTINCT m.ID, m.* from MOVIES m INNER JOIN  MOVIE_TO_ACTOR ma on m.ID = ma.MOVIE_ID  INNER JOIN ACTORS a on a.ID  = ma.ACTOR_ID INNER JOIN MOVIE_TO_GENRE  mg on mg.MOVIE_ID = m.ID INNER JOIN GENRES g on g.ID = mg.GENRE_ID WHERE TITLE LIKE '%"
						+ title.orElse("_") + "%' AND g.DESCRIPTION  LIKE '%" + genre.orElse("_")
						+ "%' AND a.NAME Like '%" + actor.orElse("_") + "%' order by m.ID LIMIT " + page.getPageSize()
						+ " OFFSET " + page.getOffset(),
				(rs, rowNum) -> {
					MovieResultDTO dto = new MovieResultDTO();
					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setYear(rs.getString("year"));
					dto.setRating(rs.getString("rating"));
					dto.setPoster(rs.getString("poster"));
					dto.setPlot(rs.getString("plot"));
					dto.setStudio(rs.getString("studio"));
					return dto;
				});
		
		int count = jdbcTemplate.queryForObject("Select  COUNT(DISTINCT m.id) from MOVIES m INNER JOIN  MOVIE_TO_ACTOR ma on m.ID = ma.MOVIE_ID  INNER JOIN ACTORS a on a.ID  = ma.ACTOR_ID INNER JOIN MOVIE_TO_GENRE  mg on mg.MOVIE_ID = m.ID INNER JOIN GENRES g on g.ID = mg.GENRE_ID WHERE"
				+ " TITLE LIKE '%"+title.orElse("_") +"%'"
				+ " AND g.DESCRIPTION  LIKE '%"+genre.orElse("_")+"%' AND a.NAME Like '%"+actor.orElse("_")+"%'" , Integer.class); //selecting count of searched movies
           
		return new PageImpl<MovieResultDTO>(movies, page, count); //pagination for results
	}

	public List<MovieResultDTO> getMovieById(Optional<Integer> movie_id) {  
		
		
		//selecting the movie details by id from database table giving all details for a movie .  

		
		final List<MovieResultDTO> movies = jdbcTemplate.query(
				"Select * from MOVIES m INNER JOIN  MOVIE_TO_ACTOR ma on m.ID = ma.MOVIE_ID  INNER JOIN ACTORS a on a.ID  = ma.ACTOR_ID INNER JOIN MOVIE_TO_GENRE  mg on mg.MOVIE_ID = m.ID INNER JOIN GENRES g on g.ID = mg.GENRE_ID WHERE m.ID="
						+ movie_id.orElse(0),
				(rs, rowNum) -> {
					MovieResultDTO dto = new MovieResultDTO();
					dto.setId(rs.getInt("id"));
					dto.setTitle(rs.getString("title"));
					dto.setYear(rs.getString("year"));
					dto.setRating(rs.getString("rating"));
					dto.setPoster(rs.getString("poster"));
					dto.setSort(rs.getInt("sort"));
					dto.setActor(rs.getString("name"));
					dto.setPlot(rs.getString("plot"));
					dto.setGenre(rs.getString("description"));
					dto.setStudio(rs.getString("studio"));
					return dto;
				});

		return movies;
	}

	public List<Genre> getAllGenre(){
		
		List<Genre> genres =  jdbcTemplate.query("SELECT * FROM GENRES ",
				(rs, rowNum) -> {
					Genre genre_dto = new Genre(rs.getInt("id"),rs.getString("description"));
					
					return genre_dto;
		});
		
		return genres;
	}
}
