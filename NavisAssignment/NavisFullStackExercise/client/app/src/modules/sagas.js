import { all, takeLatest, put } from 'redux-saga/effects';
import * as Actions from '@app/modules/actions';
import buildAction from '@app/util/buildAction';
import axios from 'axios';

function* fetchFeaturedMovies(action) {
  try {
    const response = yield axios.get('/api/movie/featured');
    yield put(buildAction(Actions.SET_FEATURED_MOVIES, response.data));

  } catch (error) {
    yield put(buildAction(Actions.SET_FEATURED_MOVIES_ERROR, error));
  }
}

function* fetchMovieDetails(action) {
 
  try {

    const response = yield axios.get('/api/movie/details/' + action.payload);
    yield put(buildAction(Actions.SET_MOVIE_DETAILS, response.data));
   
  } catch (error) {
    yield put(buildAction(Actions.SET_MOVIE_DETAILS_ERROR, error));

  }
}

function* fetchSearchedMovie(action) {

  try {

    const response = yield axios.get('/api/movie/search?title=' + action.payload.title + '&genre=' + action.payload.genre + '&actor=' + action.payload.actor+'&index=' + action.payload.index);
    yield put(buildAction(Actions.SET_SEARCHED_MOVIES, response.data));
    
  } catch (error) {

    yield put(buildAction(Actions.SET_SEARCHED_MOVIES_ERROR,"No such movies are available"));
    
  }
}

function* fetchGenreList(action) {
  const response = yield axios.get('/api/movie/genres');
  yield put(buildAction(Actions.SET_GENRE_LIST, response.data));
}




export default function* watchAll() {
  yield all([
    takeLatest(Actions.FETCH_FEATURED_MOVIES, fetchFeaturedMovies),
    takeLatest(Actions.FETCH_MOVIE_DETAILS, fetchMovieDetails),
    takeLatest(Actions.FETCH_SEARCHED_MOVIES, fetchSearchedMovie),
    takeLatest(Actions.FETCH_GENRE_LIST, fetchGenreList)
  ]);
}
