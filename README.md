# Phone Aggregator

## Test

```bash
./gradlew test
```


## Integration Test

```bash
docker-compose up -d --build
./gradlew integrationTest
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

## Prefix logic

First thing that came to mind was using a Patricia Trie for the prefix lookup. 
But it's likely not worth it, since the use case for it would be finding all strings that have a prefix,
and in our use case we only want to see if a prefix is valid.

I could do a longest prefix search algorithm for this, if I did not know that the prefix list is 99% 7 digits long with no overlaps.
Since I know this, I can optimize for this specific use case. But it would not be the best solution for a more general case.


## Service

The API could still be improved by keeping a cache of numbers/sectors already found, in which case I would reduce the calls to the external API. But this would
require knowledge more knowledge of the phone to sector mapping, so I did not do it.