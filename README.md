# Music Library

## General info

The *Music Library* is a project created for learning purposes by the student of Gdansk University of Technology. It was required for Internet Services Architecture laboratory classes.

This web application provides a possibility to store favourite songs and to add, edit, and delete them. Specific releases of songs are grouped by artist who made them.

Whole project is based on REST architecture and contains a web application named *lab4*, two server applications providing services for *artists* and *songs*, and an API *gateway*.

## Technologies

Project was created with:
* Spring Boot 2.3.4

## Requirements

The list of tools required to build and run the project:
* Npm 6.13.x+
* Open JDK 11.x+
* Apache Maven 3.5.x+

## Building

In order to build server applications *songs* and *artists*, and an API *gateway* use the following command for each of them:

```
$ mvn clean package
```

In order to build a web application *lab4* use the following command:

```
$ npm install
```

## Running

In order to run server applications *songs* and *artists*, and an API *gateway* run the .jar files created during the building process with:

```
$ java -jar file_path
```

In order to run the web application using simple http server use:

```
$ npm run dev
```

## Credits

Project is based on the given example named [Simple RPG](https://git.pg.edu.pl/internet-services-architectures/simple-rpg), as it was required for the laboratory classes on the university.

## License

Project is licensed under the [MIT](../blob/master/LICENSE) license.
