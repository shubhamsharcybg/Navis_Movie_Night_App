// libs
import React from 'react';

// components
import { withStyles } from '@material-ui/core';
import Logo from '@app/components/Logo';
import SearchInput from '@app/components/SearchInput';
import { Link } from 'react-router-dom';

const styles = theme => ({
  root: {
    backgroundColor: theme.palette.app.darkGray,
    // TODO
  },
  container:{
    margin: '0 auto',
    width: '977px',
    backgroundColor: theme.palette.app.darkGray,
    borderRadius: '4px 4px 0 0',
    padding: '0 20px',
  },
  logo: {
    padding: '21px 0px 11px 0px',
    float:'left',
  },
  search:{
    padding: '24px 56px 21px 0px',
    float:'right'
  },
  refresh : {
    clear: 'both',
  },
});

const Header = props => {
  const { classes } = props;

  return (
    <div>
      <div className={classes.container}>
          <div className={classes.logo}><Link to="/"><Logo /></Link></div>
          <div className={classes.search}><SearchInput /></div>
          <div className={classes.refresh}></div>
     </div>
    </div>
  );
};

export default withStyles(styles)(Header);
