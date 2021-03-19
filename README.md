# Base app


## Test

```bash
./gradlew test
```

## Build and run

```bash
docker build -t baseapp:0.0.1 .
docker run -p 8080:8080 -d baseapp:0.0.1
```
 or
 
```bash
docker-compose up -d --build
```