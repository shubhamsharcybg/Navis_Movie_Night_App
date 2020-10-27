import { createSelector } from 'reselect';

const app = state => state.app;
const searched = state=>state.search;
const genre  = state=>state.genre;
export const selectFeaturedMovies = createSelector(app, app => app.featuredMovies);
export const selectError = createSelector(app, app => app.errors);
export const selectSearchedMovies = createSelector(searched, searched => searched.searched_movies.content);
export const selectPageCount = createSelector(searched, searched => searched.searched_movies.totalPages);
export const selectSearchedMoviesError = createSelector(searched, searched => searched.searched_movies.errors);

export const selectGenreList = createSelector(genre, genre => genre.genres);
