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




# Thoughts

First thing that came to mind was using a Patricia Trie for the prefix lookup. 
But it's likely not worth it, since the use case for it would be finding all strings that have a prefix,
and in our use case we only want to see if a prefix is valid.

I could do a longest prefix search algorithm for this, if I did not know that the prefix list is 99% 7 digits long with no overlaps.
Since I know this, I can optimize for this specific use case. But it would not be the best solution for a more general case.