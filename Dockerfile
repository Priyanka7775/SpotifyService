FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE="TrackUserdb"
ENV MONGO_URL="mongodb://localhost:27017/TrackUserdb"

ADD ./target/UserTrackservices-0.0.1-SNAPSHOT.jar /usr/lib/UserTrackservices-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","UserTrackservices-0.0.1-SNAPSHOT.jar"]