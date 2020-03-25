**Spring core part 2**

*To run the docker container, use:*
`docker run --name weather-reports -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=weather-reports -d postgres`

*To migrate the db, use*
`mvn flyway:migrate`

*To start the app, use*
`mvn jetty:run`
