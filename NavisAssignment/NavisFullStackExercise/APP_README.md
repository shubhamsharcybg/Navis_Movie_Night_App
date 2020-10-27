# Getting Started
 1. Unzip the package named <NavisAssignmentFinalShubham>
 2. After unzipping it contains -
         - client(folder)
         - server(folder)
         - DetailsModal.PNG
         - Results.PNG
         - Search.PNG
         - README.md
		 - READMEAPP.md (you are reading this)
 3. Now configure for Enviornment setup.

# Setup for client 

 # for Windows

     1. Check Version for node.js v12.16.3 using `node -v` on windows command prompt.
        if not download `https://nodejs.org/dist/v12.16.3/win-x64/node.exe` from here.
     2. Check version for npm  using `npm -v` on windows command prompt.
     3. Install pnpm using `npm install -g pnpm` 
     4. Install webpack version `4.41.0` using `pnpm i -g webpack@4.41.0`
     5. `cd` to `client/` (the directory this file is in)
     6. Install dependencies using `make deps` if GNUWin32 installed,otherwise use `pnpmp i`.


 # for Ubuntu
  1. Use [nvm](https://github.com/creationix/nvm) to install node.js v12.16.3
    - If you don't have nvm installed:
      - `curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.36.0/install.sh | bash`
    - Once nvm is installed:
      - `nvm install 12.16.3`
      - `nvm alias default node`
  2. Install [pnpm](https://github.com/pnpm/pnpm#install) if you don't already have it
    - `curl -L https://raw.githubusercontent.com/pnpm/self-installer/master/install.js | node`
  3. `cd` to `client/` (the directory this file is in)
  
  4. Install dependencies by running `make deps`

  ----------------------------------------------------------------------------------------------------------

  
# setup for server 
    1. Check version for java jdk `11` using `java --version` windows command prompt. otherwise install jdk 11.
    2. Check version for mvn using `mvn -v` if not mvn command recognized
       then download from `https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip` unzip downloaded source & go to `\bin` directory and setup the environment variable.
   

# Running client & Server
  # For Server 
  1. Go to `Projet_direcory/server/target` and open command prompt on this location.          
  2. Run the server using ```java -jar spring-boot-0.0.1-SNAPSHOT.jar```.
# For client 
   1. Go to `Projet_direcory/client` and open command prompt on this location.  
   2. Run the webpack dev server using `webpack --config ./webpack.config.js --watch --progress`.
   3. In a new command prompt, start the node server using `nodemon ./web-server/server.js` to serve          the bundled assets.

# On Chrome Browser 
   1. Go to your browser & hit this url `http://localhost:8080/`.
_______________________________________________________________________________________________________________

# About the Application

  Navis Movie Night is an React/Java based Single Page Application application for searching the movies according such searching criteria like Title, Actor, Genre. User can search movie & can get the detailed view of any movie.This application also contains Featured Movies on Home Page with detailed Movies. you can explore the application by running the functional things are available. 
=> Home Page
  1. If you are at `http://localhost:8080/` on Browser thats fine.
  2. you are At Home Page of Navis MovieNight App. you can see Featured Movies on home page.
  3. if you click on any movie card on this page you can see the detailed view of movie you have selected on  modal/popup.
  4. you have one search Bar also on everypage containing 2 text fields & 1 Dropdownn.

=> Search Bar
  1. Search bar containing 2 text fields & 1 Dropdownn.
  2. TextFields are for search Actor,Title & Dropdown for Genres 
  3. Genres are coming from server containing genres list.
  4. you can search using all combinations & criteria. 
  5. one search button is also there for search operation.

=> Search Page 
  1. this Page is for results for selected search criteria. 
  2. you will get paged view for results. 
  3. you can select pages by clicking on page number . 
  4. you will get a message if movies are unavialable for selected criteria. 
