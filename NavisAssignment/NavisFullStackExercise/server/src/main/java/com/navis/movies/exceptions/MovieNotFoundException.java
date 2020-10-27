package com.navis.movies.exceptions;

public class MovieNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1491467259229093323L;

	public MovieNotFoundException() {
		super();
	}

	public MovieNotFoundException(final String message) {
		super(message);
	}

}
