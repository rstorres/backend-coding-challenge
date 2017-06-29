backend-coding-challenge API
============================

This project implements the backend API that will interact with the backend-coding-challenge frontend.

In terms of tasks, the two mandatory stories were implemented, as well as, the two additional stories that are flagged as extra work.

## Technical decisions

The tools and technologies chosen were:

   - *Spring Framework* for the REST API. The reason to choose Spring is because it is a JAVA framework and one of the requiremets was to develop a JAVA REST Api. The second reason to choose this framework is because any Spring application can be deployed on a Tomcat server that is one of the servers that Engage uses;

   - For the database, I chose *MySQL* database mainly because it is the database engine used by Engage and because I am also familiar with it.  


In terms of servers, I decided to have just one server (Tomcat) that will be responsible for serving the frontend static resources and for answering the API requests. In order to make the deployment easier, a small change was made on the Gulp configuration in order to automate the deployment process. (For further information please refer to [Gulp changes](#gulp-changes))

Regarding the **User story 3**, I decided to use the external service *http://fixer.io/* to get the current conversion rates. It can be submitted expenses in all the currencies that *fixer.io* supports. 

## Setup

To setup the application follow the steps below:


- Create a database on a MySQL database engine;
- Run the script `database_ddl.sql` available on `config` folder. This script will create the database table that will hold the expenses
- Edit the file `conf.properties` available on `src/main/resources`. In this file you can setup the following parameters for the connection pool:
   - The host where the database is placed; 
   - The database name;
   - The database user;
   - The database password;
   - The initial number of connections created by the connection pool;
   - The maximum number of active connections.

   An example of this configuration file is:
   ```properties
   db.url=jdbc:mysql://localhost/engage
   db.user=engage_user
   db.password=engage_pass
   db.initialSize=10
   db.maxActive=20
   ```
- Run the Gulp task *build* ( execute `gulp build` ). This task will copy the compiled frontend code to a Tomcat folder with goal of serving the frontend resources through Tomcat.

- Run the command `mvn clean install` to compile all the API code;

- Run the command `mvn tomcat7:run` to run the Tomcat server;

- Access the application through the URL `http://localhost:9090/index.html` on a web browser;


## Gulp changes

As mentioned before the frontend static resources are being served by Tomcat. In order to automate the deployment of the frontend resources correctly to the Tomcat folder, two additional Gulp tasks were created `clean-apiserverfrontend` and `copyfrontend`, that are responsible for cleaning old static resources still present on the server and for deploying the new compiled frontend resources, respectively.  