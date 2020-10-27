import { handleActions } from 'redux-actions';
import * as Actions from './actions';

const initialState = {
    movieDetails: {actorSet:[],genreSet:[]},
    errors:{}
  };
  
  const moviereducer = handleActions(
    {
      [Actions.SET_MOVIE_DETAILS]: (state, action) => {
        state.movieDetails = action?.payload;
        return state;
      },
      [Actions.SET_MOVIE_DETAILS_ERROR]: (state, action) => {
        state.errors = action?.payload.message;
        return state;
      },
    },
    initialState
  );

  export default moviereducer;