// libs
import React from 'react';
import {
  Router,
  Switch,
  Route,
  Redirect,
} from 'react-router-dom';

// components
import { withStyles } from '@material-ui/core';
import Header from '@app/components/Header';
import FeaturedMovies from '@app/components/FeaturedMovies';
import SearchedMovies from '@app/components/SearchedMovies';
import Footer from '@app/components/Footer';

// modules
import { immerHistory } from '@app/modules/store';

// assets
import bgImage from '@app/assets/moviebg.jpg';

const styles = theme => ({
  root: {
    // TODO
  },
  content: {
      backgroundImage: "url(" + bgImage + ")",
			backgroundRepeat: "no-repeat",
			backgroundPosition: "center center",
			backgroundSize: "cover",
			backgroundAttachment: "fixed",
      height: "100%",
      padding: "30px 0 30px 0",
  },
  body: {
    backgroundColor: theme.palette.app.gray,
    margin: '0 auto',
    width: '977px',
    padding: '0 20px 30px 20px',
    webkitBoxShadow: "0 0 4px #000",
    mozBoxShadow: "0 0 4px #000",
    boxShadow: "0 0 4px #000"
  },
  clear : {
    clear: 'both',
  },
});



const MainLayout = props => {
  const { classes } = props;
  
  return (
    <div className={classes.root}>
      <Router history={immerHistory}>
        <div className={classes.content}>
          <Header />

          <div className={classes.body}>
            <Switch>
              <Route path='/' exact component={FeaturedMovies} />
              <Route path='/search' component={SearchedMovies}/>
              <Redirect from='*' to='/' />
            </Switch>
            <div className={classes.clear}></div>
          </div>

          <Footer />
        </div>
      </Router>
    </div>
  );
};

export default withStyles(styles)(MainLayout);
