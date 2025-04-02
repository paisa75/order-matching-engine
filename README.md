## Run Postgres
- To run postgres docker container please run the followings:
```
docker pull postgres
docker images
docker run --name postgres-spring -e POSTGRES_PASSWORD=mypassword -e POSTGRES_USER=myuser -e POSTGRES_DB=mydatabase -p 5432:5432 -d postgres
```


## Query on Postgres Container
```
docker exec -it postgres-spring psql -U myuser -d mydatabase
SELECT * FROM "user-details";
```

