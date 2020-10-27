// libs
import React from 'react';

// components
import { withStyles } from '@material-ui/core';

const styles = theme => ({
  root: {    
    margin: '0 auto',
    borderRadius: '0 0 4px 4px', 
    padding: '0 20px',
    height: 66,
    width: '977px',
    backgroundColor: theme.palette.app.darkGray
  
   },
});

const Footer = props => {
  const { classes } = props;

  return (
    <div className={classes.root}>
    </div>
  );
};

export default withStyles(styles)(Footer);
