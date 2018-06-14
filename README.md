# slick exercises

> learn the Scala Slick DSL via test driven development

## about

This project adapts the challenges found on [PGExercises](https://pgexercises.com/) to Scala. PGExercises contains excellent, thought-provoking SQL challenges "built on a single dataset".  

The challenge to the user is to implement solution queries using the Slick DSL.

## how to use

This project is meant to be used in tandem with the [PGExercises](https://pgexercises.com/) website.  

The project currently implements 2 exercise categories:

Exercise Name  | PGExercises Link  |  Project Link
--|---|--
`Basic`  | [https://pgexercises.com/questions/basic/](https://pgexercises.com/questions/basic/)   | [https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/Basic.scala](https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/Basic.scala)  
`JoinsAndSubqueries`  | https://pgexercises.com/questions/joins/  | https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/JoinsAndSubqueries.scala  


The project has methods which align to exercises from [PGExercises](https://pgexercises.com/).  The methods are missing implementations.

Each class also has a test suite which calls the exercise methods and verifies the return values.

The job of the user is to move through the challenges on [PGExercises](https://pgexercises.com/), implementing the solution queries in this project using the Slick DSL, and verifying their code via the provided test suite.

## !! CRITICAL !! Mandatory Prerequisite

The test suite requires a connection to a Postgres instance containing the full dataset from [https://pgexercises.com/](https://pgexercises.com/)

A Dockerized PG image with the dataset has been created for convenience.  To use, run this command:

```sh
docker run -d -p 5432:5432  \
  -e "POSTGRES_USER=postgres" \
  -e "POSTGRES_PASSWORD=postgres" \
  lombardo/postgresql-exercises-docker:0.0.1
```

Leave this container running for the duration of your workflow.

By default, the test suite expects the PG host to be `localhost` and the port `5432`.  These values can be overridden via env vars (`POSTGRES_HOST`, `POSTGRES_PORT`) or hardcoded into the project by modifying the db connection object:
`src/main/scala/com/github/lombardo/chcg/database/Connection.scala`

Docker image source code: [https://github.com/lombardo-chcg/postgresql-exercises-docker](https://github.com/lombardo-chcg/postgresql-exercises-docker)

### Getting Started with Gradle

> "This is a Scala project...why is it built with Gradle instead of sbt?"

Just a personal preference.  Here's how to get started:

- Clone the repo
- Open IntelliJ

### Running the Tests
```
# run all the tests
./gradlew test

# run a single test class
./gradlew test --tests BasicSpec

# run a single test method
./gradlew test --tests "should Retrieve everything from a table"
```
