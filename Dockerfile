FROM clojure as system1build
WORKDIR /opt
RUN curl -sSL -H 'Accept: application/vnd.github.v3.raw' https://api.github.com/repos/tiagofvalerio/system1/tarball/master | tar zx --strip-components 1 -C system1
RUN cd system1 && lein uberjar


FROM openjdk:jre-alpine
RUN pwd && ls
WORKDIR /opt/system1
COPY --from=system1build /opt/system1/target/system1.jar .
CMD ["java","-jar","system1.jar"]
EXPOSE 8080
