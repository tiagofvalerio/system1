FROM clojure
RUN mkdir system1
RUN curl -sSL -H 'Accept: application/vnd.github.v3.raw' https://api.github.com/repos/tiagofvalerio/system1/tarball/master | tar zx --strip-components 1 -C system1
RUN cd system1 && lein uberjar && cd ../
RUN pwd && ls
ADD system1/target/system1.jar /opt/system1/
WORKDIR "/opt/system1/"
CMD ["java","-jar","system1.jar"]
EXPOSE 8080
