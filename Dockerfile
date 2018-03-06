#FROM clojure as system1build
#WORKDIR /opt/system1
#RUN curl -sSL -H 'Accept: application/vnd.github.v3.raw' https://api.github.com/repos/tiagofvalerio/system1/tarball/master | tar zx --strip-components 1
#RUN lein uberjar


FROM openjdk:jre-alpine
RUN pwd && ls
WORKDIR /opt/system1
#COPY --from=system1build /opt/system1/target/system1.jar .
ADD target/system1.jar .
CMD ["java","-jar","system1.jar"]
EXPOSE 8080
