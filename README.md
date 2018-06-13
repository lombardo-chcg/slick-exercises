# slick exercises

> learn the Scala Slick DSL via red-to-green refactoring

### Mandatory Prerequisite

The test suite requires a connection to a Postgres instance containing the full dataset from [https://pgexercises.com/](https://pgexercises.com/)

A Dockerized PG image with the dataset has been created for convenience.  To use, run this command:

```sh
docker run -d -p 5432:5432  \
  -e "POSTGRES_USER=postgres" \
  -e "POSTGRES_PASSWORD=postgres" \
  lombardo/postgresql-exercises-docker:0.0.1
```

By default, the test suite expects the PG host to be `localhost` and the port `5432`.  These values can be overridden via env vars (`POSTGRES_HOST`, `POSTGRES_PORT`) or hardcoded into the project by modifying the db connection object: 
`src/main/scala/com/github/lombardo/chcg/database/Connection.scala` 

Docker image source code: [https://github.com/lombardo-chcg/postgresql-exercises-docker](https://github.com/lombardo-chcg/postgresql-exercises-docker)

### Running the Tests
```
# run all the tests
./gradlew test

# run a single test class
./gradlew test --tests BasicSpec

# run a single test method
./gradlew test --tests "should Retrieve everything from a table"
```
