// libs
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

// components
import { withStyles, Typography } from '@material-ui/core';
import MovieCard from '@app/components/MovieCard';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Chip from '@material-ui/core/Chip';

// modules
import { FETCH_FEATURED_MOVIES, FETCH_MOVIE_DETAILS } from '@app/modules/actions';
import { selectFeaturedMovies } from '@app/modules/selectors';

// util
import buildAction from '@app/util/buildAction';
import { selectError } from '../modules/selectors';



function getModalStyle() {
  const top = 50
  const left = 50

  return {
    top: `${top}%`,
    left: `${left}%`,
    transform: `translate(-${top}%, -${left}%)`,
  };
}

const useStyles = makeStyles((theme) => ({
  card: {
    width: '800px',
    height: '400px',
    background: 'transparent',
    position: 'absolute',
    left: '0',
    right: '0',
    margin: 'auto',
    top: '0',
    bottom: '0',
    borderRadius: '10px 10px 10px 10px',
    boxShadow: '0px 20px 30px 3px rgba(0, 0, 0, 0.55)'
  },
  card_top: {
    height: '30%',
    width: '100%',
    backgroundColor: '#fddb3a',
    borderRadius: '10px 10px 0px 0px',
    overflow: 'visible',
  },
  card_left: {
    width: '40%',
    height: '20rem',
    float: 'left',
    borderRadius: '0px 0px 0px 10px',
    backgroundColor: '#313640'
  },
  card_img: {
    width: '70%',
    height: '95%',
    position: 'fixed',
    top: '-4rem',
    left: '4rem',
    borderRadius: '10px 10px 10px 10px',
    position: 'relative',
  },
  card_right: {
    width: '60%',
    float: 'left',
    background: '#000000',
    height: '20rem',
    borderRadius: '0px 0px 10px 0px',
    backgroundColor: '#313640',
  },
  movie_title: {
    position: 'absolute',
    top: '4rem',
    left: '20rem',
  },
  title: {
    color: '#313640',
    fontWeight: 'bold',
  },
  year: {
    color: '#313640',
    fontWeight: 'normal',
  },
  studio: {
    color: '#ffff',
    fontWeight: 'light',
    margin: '1rem',
    fontSize: '0.75rem'
  },
  plot: {
    color: '#ffff',
    fontWeight: 'normal',
    margin: '1rem'
  },
  staring: {
    color: '#ffff',
    fontWeight: 'normal',
    marginLeft: '1rem'
  },
  actors: {
    color: '#ffff',
    fontWeight: 'normal',
    marginLeft: '1rem',
    fontSize: '0.75rem'
  },
  genre: {
    backgroundColor: '#fddb3a',
    marginTop: '1rem',
    marginLeft: '0.25rem'
  },
  closeButton: {
    position: 'absolute',
    top: '0.5rem',
    left: '47rem',
    fontWeight: 'bolder',
    fontSize: '1rem',
    borderRadius:'100%',
    backgroundColor:'#313640',
    color:"#ffff",
    border: "none"
  }
}));


const styles = theme => ({
  root: {
    
  },
  errorStyle: {
    padding: '2rem',
    color: '#ffff',
    position: 'absolute',
    top: '3rem',
    left: '35%'
  }
});

const FeaturedMovies = props => {
  const { classes, large = false } = props;
  const [modalStyle] = React.useState(getModalStyle);
  const [open, setOpen] = React.useState(false);
  const [id, setId] = useState(0);
  const modalClasses = useStyles();
  const dispatch = useDispatch();

  const handleOpen = (setopen) => {
    setOpen(setopen);

  };

  const dispatchAction = (ids) => {

    dispatch(buildAction(FETCH_MOVIE_DETAILS, ids));
  }

  const handleClose = () => {
    setOpen(false);
  };



  useEffect(() => {
    dispatch(buildAction(FETCH_FEATURED_MOVIES));
  }, []);


   const moviedetails = useSelector(state => state.movie);
  const { movieDetails } = moviedetails;


  const movies = useSelector(selectFeaturedMovies);
  const error = useSelector(selectError);

  const width = large ? 267 : 200;
  const height = large ? 396 : 295;


  const body = (
    <div className={modalClasses.card}>
      <div className={modalClasses.card_top}>
        <button onClick={handleClose} className={modalClasses.closeButton}>&#10005;</button>
        <div className={modalClasses.movie_title}>
          <Typography className={modalClasses.title}>
            {movieDetails.title}
          </Typography>
          <Typography className={modalClasses.year}>
            {movieDetails.year} ({movieDetails.rating})
       </Typography>
        </div>
      </div>
      <div className={modalClasses.card_left}>
        <img className={modalClasses.card_img} src={`/images/${movieDetails.poster}`}
          alt={`${movieDetails.title} Poster`} />
      </div>
      <div className={modalClasses.card_right}>
        <div className={modalClasses.card_right__details}>
          <Typography className={modalClasses.studio}>
            {movieDetails.studio}
          </Typography>
          <Typography className={modalClasses.plot}>
            {movieDetails.plot}
          </Typography>
          <Typography className={modalClasses.staring}>
            staring :
          </Typography>
          <Typography className={modalClasses.actors}>
            {movieDetails.actorSet.toString()}
          </Typography>
          {movieDetails.genreSet.map((genre) => (<Chip className={modalClasses.genre} label={genre} />))}


        </div>

      </div>

    </div>
  );


  return (

    <div className={classes.root}>
      
      {movies.map((movie, index) => (
        <MovieCard large data={movie} Open={handleOpen} Id={dispatchAction} key={index} />
      ))}
      {error ? <div className={classes.errorStyle}>
        <h4>{error.message}</h4>
      </div> : null}

      <div>

        <Modal
          open={open}
          onClose={handleClose}
        >
          {body}
        </Modal>
      </div>
    </div>


  );
};

export default withStyles(styles)(FeaturedMovies);
