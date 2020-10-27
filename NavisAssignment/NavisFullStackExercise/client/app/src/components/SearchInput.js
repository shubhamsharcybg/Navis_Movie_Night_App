// libs
import React, { useState, useEffect} from 'react';
import buildAction from '@app/util/buildAction';


// components
import {
  withStyles,
  TextField,
  Select,
  Button,
  MenuItem,
} from '@material-ui/core';
import { useDispatch, useSelector } from 'react-redux';
import { FETCH_GENRE_LIST, FETCH_SEARCHED_MOVIES, SET_SEARCHED_MOVIES, SET_SEARCHED_MOVIES_ERROR } from '../modules/actions';
import { selectGenreList } from '@app/modules/selectors';
import { Link } from 'react-router-dom';



const styles = theme => ({
  root: {
    display: 'flex',
    flexFlow: 'row wrap',
  },
  input: {
    backgroundColor: theme.palette.app.white,
    marginRight: 8,
    width: '150px',
    borderRadius: '4px',
    padding: '2px 0 0 9px'
  },

  searchButton: {
    backgroundColor: theme.palette.app.yellow,
    fontWeight: '700',
    textDecoration: 'none',
    color:'#1E2129',
    textAlign:'center',
    padding:'5px',
    borderRadius: '4px',
    width:'4rem'

  },
  inputLabel: {
    color: '#ABABAB',
  },
});

const SearchInput = props => {
  const { classes } = props;
  const [title, setTitle] = useState("");
  const [genre, setGenre] = useState("");
  const [actor, setActor] = useState("");
  

  const dispatch = useDispatch();
  const handleSearch = ()=>{
    dispatch(buildAction(SET_SEARCHED_MOVIES_ERROR,null));
    dispatch(buildAction(FETCH_SEARCHED_MOVIES,{ title: title, genre: genre, actor: actor,index:0}));
    setTitle("");
    setGenre("");
    setActor("");
  }
  


  useEffect(() => {
    dispatch(buildAction(FETCH_GENRE_LIST));
  }, []);

  const genres = useSelector(selectGenreList);

  return (
    <div className={classes.root}>
      <TextField
        placeholder='Title'
        className={classes.input}
        InputProps={{ disableUnderline: true }}
        value={title} onChange={(e) => { setTitle(e.target.value) }}
      />

      <TextField
        placeholder='Actor'
        className={classes.input}
        InputProps={{ disableUnderline: true }} value={actor} onChange={(e) => { setActor(e.target.value) }}
      />

      <Select className={classes.input} value={genre} onChange={(e) => { setGenre(e.target.value) }}>
        
        {genres.map((genre,index) => (
       <MenuItem value={genre.genre} key={index}>{genre.genre}</MenuItem>
      ))}
       
      </Select>

      <Link to={"/search?title=" + title + "&genre=" + genre + "&actor="+actor} className={classes.searchButton} onClick={handleSearch}>
        Search
      </Link>
    </div>
  );
};

export default withStyles(styles)(SearchInput);
