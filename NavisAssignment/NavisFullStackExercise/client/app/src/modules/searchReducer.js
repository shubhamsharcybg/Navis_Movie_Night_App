import { handleActions } from 'redux-actions';
import * as Actions from './actions';

const initialState = {
    searched_movies: {content:[]},
    errors:null,
    totalPages:null,
    
};

const searchreducer = handleActions(
  { 
    [Actions.SET_SEARCHED_MOVIES]: (state, action) => {
      state.searched_movies = action?.payload;
      state.totalPages = action?.payload.totalPages;
      return state;
    },
    [Actions.SET_SEARCHED_MOVIES_ERROR]: (state, action) => {
        state.errors = action?.payload;
        return state;
      },
  },
  initialState
);

export default searchreducer;
