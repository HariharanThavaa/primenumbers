# primenumbers
primenumbers

Execute ```./gradlew bootRun``` command in root folder to test the application functionality

To Build 

```./gradlew clean build```

Server port is configured to 9082

## Docker commands

to build image :

```docker build -t primenumbers:1.0.0 .```

to run the image : 

```docker run -it -p 9082:9082 primenumbers:1.0.0```


## Testing

Test Json Response : ```curl "http://localhost:9082/primes/10" --header "Content-Type: application/json"```

Test XML Response : ```curl "http://localhost:9082/primes/10" --header "Content-Type: application/xml"```



