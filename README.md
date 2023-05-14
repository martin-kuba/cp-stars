# Chemically Peculiar Stars Database Project

Chemically peculiar (CP) stars are the subject of astronomical research
in recent decades in particular. Their nature makes them classifiable
on different levels of certainty, creating inconsistent sets of stars.

CP-Stars application offers simple access to the list of [**Ap, HgMn and Am stars**](https://ui.adsabs.harvard.edu/abs/2009A%26A...498..961R/abstract).


There is also [**graphical user interface**](https://github.com/Kuliak/cp-stars-frontend) 
implemented and [**Python library**](https://github.com/Kuliak/cp-stars-python) 
exists in order to provide another way to access the database.

## Usage

In order to run the application, PostgreSQL database has to be present. 
Copy of the database is available in files folder ([**cpstars.sql**](https://github.com/Kuliak/cp-stars/tree/master/files)).

After downloading the file, following commands can be used
to initialize the database:

1.) Create database (Postgres terminal):
```
CREATE DATABASE cpstars;
```

2.) Initialize database using the downloaded file (using default Postgres user role)
```
psql -U postgres -d cpstars -f ./cp-stars/files/cpstars.sql
```

<u>**IMPORTANT:**</u> steps may not work the same way on different operating system.

### Run the application

When the database was successfully initialized, we may proceed and run the application.
In case different Postgres role is used for database access, it should be defined in **application.yml** file.

After configuring all the needed properties, go to the project root directory:

```
cd cp-stars
```

Run the following Maven command to install the application into local Maven repository:

```
mvn clean install
```

By default, the application should be installed into **.m2** 
folder located in the home folder. After that, execute following command 
to run the CP-Stars application:

```
java -jar ~/.m2/repository/cz/muni/fi/cp-stars/0.0.1-SNAPSHOT/cp-stars-0.0.1-SNAPSHOT.jar
```

