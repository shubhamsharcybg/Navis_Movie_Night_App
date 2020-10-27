package com.navis.movies.dto;



import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResultDTO {

	private int id;
	private String title;
	private String year;
	private String rating;
	private int sort;
	private String poster;
	private String plot;
	private String actor;
    private String genre;
    private Set<String> actorSet;
    private Set<String> genreSet;
    private String studio;
}
