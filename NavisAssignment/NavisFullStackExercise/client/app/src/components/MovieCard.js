// libs
import React from 'react';
// components
import { withStyles, Typography } from '@material-ui/core';

// modules

// util
import { createBrowserHistory } from 'history';

const styles = theme => ({
  root: {
    padding: '30px 20px 0px',
    float: 'left',
    '& .MuiTypography-root': {
      color: theme.palette.app.white,
    },
  },
  click: {
    cursor: 'pointer',

  },
  image: {
    boxShadow: "0 0 3px #000",
    borderRadius: '4px',
  },
  movie_title: {
    fontWeight: '2rem'
  },
});

const MovieCard = props => {
  const { data, large = false, classes } = props;

  const width = large ? 267 : 200;
  const height = large ? 396 : 295;


  const handleOpen = () => {
    props.Open(true);
    dispatchAction()
  };
  const dispatchAction = () => {
    props.Id(data.id);
  }
  return (
    <div className={classes.root} style={{ width }}>
      <div className={classes.click}>
        <a onClick={handleOpen}>
          <img
            src={`/images/${data.poster}`}
            alt={`${data.title} Poster`}
            className={classes.image}
            style={{ width, height }}
          />
          <Typography className={classes.movie_title}>
            {data.title}
          </Typography>
          <Typography>
            {data.year} ({data.rating})

        </Typography>
        </a>
      </div>
    </div>
  );
};

export default withStyles(styles)(MovieCard);
