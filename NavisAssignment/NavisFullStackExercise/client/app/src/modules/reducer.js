import { handleActions } from 'redux-actions';
import * as Actions from './actions';

const initialState = {
  featuredMovies: [],
  errors:{}
};

const reducer = handleActions(
  {
    [Actions.SET_FEATURED_MOVIES]: (state, action) => {
      state.featuredMovies = action?.payload;
      return state;
    },
    [Actions.SET_FEATURED_MOVIES_ERROR]: (state, action) => {
      state.errors = action?.payload;
      return state;
    },
  },
  initialState
);

export default reducer;
