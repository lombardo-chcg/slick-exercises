# slick exercises

> learn the Scala Slick DSL via red-to-green refactoring

### Mandatory Prerequisite

The test suite requires a connection to a Postgres instance containing the full dataset from [https://pgexercises.com/](https://pgexercises.com/)

A Dockerized PG image with the dataset has been created for convenience.  To use, simply run this command:

```sh
docker run -d -p 5432:5432  \
  -e "POSTGRES_USER=postgres" \
  -e "POSTGRES_PASSWORD=postgres" \
  lombardo/postgresql-exercises-docker:0.0.1
```

The default PG host is `localhost` and the default port is `5432`.  These values can be overridden via env vars (`POSTGRES_HOST`, `POSTGRES_PORT`) or hardcoded into the project by modifying this file: `src/main/scala/com/github/lombardo/chcg/database/Connection.scala` 

More details: [https://github.com/lombardo-chcg/postgresql-exercises-docker](https://github.com/lombardo-chcg/postgresql-exercises-docker)

### Run Tests
```
./gradlew test
```
