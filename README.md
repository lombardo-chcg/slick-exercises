# slick exercises

> learn the Scala Slick DSL via test driven development

## thesis

[Slick](http://slick.lightbend.com/doc/3.2.3/introduction.html) provides a fun Scala DSL for writing SQL.  

Viewing SQL thru the lens of Scala collections and functional programming techniques can open new doors of perception, leading the developer to think about relational data models in a new way.  In the process the developer will improve foundational SQL skills and become a stronger Scala application developer.

> We don‘t try to fight the relational model, we embrace it through a functional paradigm.

*[source: http://slick.lightbend.com/doc/3.2.3/introduction.html#functional-relational-mapping](http://slick.lightbend.com/doc/3.2.3/introduction.html#functional-relational-mapping)*

## about

This project maps the challenges found on [PGExercises](https://pgexercises.com/) to a Scala/Slick context. The PGExercises site contains thought-provoking SQL challenges presented in a "learn by doing" format.  

The challenge to the user of this project is to solve the problems presented on PGExercises using the Slick DSL.

## how to use

Move through the challenges on [PGExercises](https://pgexercises.com/), implementing the solution queries in this project using the Slick DSL, and verifying their code via the provided test suite.

This project is meant to be used in tandem with the [PGExercises](https://pgexercises.com/) website.  It currently implements 2 exercise categories from that site:

Exercise Name  | PGExercises Link  |  Project Link
--|---|--
`Basic`  | [https://pgexercises.com/questions/basic/](https://pgexercises.com/questions/basic/)   | [src/main/scala/com/github/lombardo/chcg/exercises/Basic.scala](https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/Basic.scala)  
`JoinsAndSubqueries`  | https://pgexercises.com/questions/joins/  |[src/main/scala/com/github/lombardo/chcg/exercises/JoinsAndSubqueries.scala ](https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/JoinsAndSubqueries.scala)


The project has methods which map directly to exercises from [PGExercises](https://pgexercises.com/).  These methods are missing their implementations.  Navigate here for the methods:
- `src/main/scala/com/github/lombardo/chcg/exercises`

Each method has a matching test which calls the exercise methods and verifies the return values.  All the tests are currently failing.  The user's job is to make them go green.  Tests can be found here:
- `src/test/scala/com/github/lombardo/chcg/exercises/`

## 🚨 Mandatory Prerequisite 🚨

The exercises are all based on a single dataset from [https://pgexercises.com/](https://pgexercises.com/).

The test suite **requires** a connection to a Postgres instance containing this full dataset.

A Dockerized PG image containing the dataset has been created for convenience.  To use it, run this command:

```sh
docker run -d -p 5432:5432  \
  -e "POSTGRES_USER=postgres" \
  -e "POSTGRES_PASSWORD=postgres" \
  lombardo/postgresql-exercises-docker:0.0.1
```

Leave this container running for the duration of your workflow.

By default, the test suite expects the PG host to be `localhost` and the port `5432`.  These values can be overridden via env vars (`POSTGRES_HOST`, `POSTGRES_PORT`) or hardcoded into the project by modifying the db connection object:
`src/main/scala/com/github/lombardo/chcg/database/Connection.scala`

Build the image locally from this repo:  [https://github.com/lombardo-chcg/postgresql-exercises-docker](https://github.com/lombardo-chcg/postgresql-exercises-docker)

Alternatively, the PGExercises site contains instructions for loading the dataset in your own PG server: [https://pgexercises.com/gettingstarted.html](https://pgexercises.com/gettingstarted.html)

### Getting Started with Gradle

> "This is a Scala project...why is it built with Gradle instead of sbt?"

Just a personal preference.  Here's how to get started:

- Clone the repo
- Open IntelliJ and **IMPORT** the project directory (not Open)
- Choose `Import project from external model` and select Gradle
- Select `Use gradle wrapper task configuration`

IntelliJ should guide you thru setup of a local Scala SDK for each module (main, test) if one is not already configured.  

### Running the Tests

**Prerequisite!  make sure to start the local Docker container first!**

The tests can be run from inside IntelliJ using the standard methods or from the command line.  

```
# run all the tests
./gradlew test

# run a single test class
./gradlew test --tests BasicSpec

# run a single test method using the test name
./gradlew test --tests "should Retrieve everything from a table"
```

### Tips

- Review the Slick Queries Docs: [http://slick.lightbend.com/doc/3.2.3/queries.html](http://slick.lightbend.com/doc/3.2.3/queries.html)
- The Slick data model for this project has been auto-generated and is available for use in the exercises.  `TableQuery` objects for the `Members`, `Bookings` and `Facilities` tables await: [/src/main/scala/com/github/lombardo/chcg/database/Tables.scala](https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/database/Tables.scala)
- The nature of the tests is such that the query must be composed, turned into a `DBIOAction`, run, awaited and the result returned for verification.  A database connection has been provided in the project via the `Connection` object.
- [Here is a full example of how to implement a solution](https://github.com/lombardo-chcg/slick-exercises/blob/master/src/main/scala/com/github/lombardo/chcg/exercises/Basic.scala#L10-L21) (Admittedly this "synchronous" approach is not how Slick would be used in an application, however the focus is on the Query DSL and the returned values, therefore I am ok with it.)
- SOLUTIONS to the queries are provided in the `solutions` package and PRs to improve these solutions from expert Slick-sters are highly encouraged


### Credits

The original & totally awesome PostgreSQL Exercises website and dataset was made by [Alisdair Owens](https://pgexercises.com/about.html).  [License Info: https://github.com/AlisdairO/pgexercises/blob/master/LICENSE](https://github.com/AlisdairO/pgexercises/blob/master/LICENSE)
