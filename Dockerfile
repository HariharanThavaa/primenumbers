FROM openjdk:17
COPY ./build/libs/primenumbers-0.0.1-SNAPSHOT.jar /usr/src/primenumbers/
WORKDIR /usr/src/primenumbers
EXPOSE 9082
CMD ["java", "-jar", "primenumbers-0.0.1-SNAPSHOT.jar"]