import { handleActions } from 'redux-actions';
import * as Actions from './actions';

const initialState = {
  genres: [],
};

const genrereducer = handleActions(
  {
    [Actions.SET_GENRE_LIST]: (state, action) => {
      state.genres = action?.payload;
      return state;
    },
  },
  initialState
);

export default genrereducer;
