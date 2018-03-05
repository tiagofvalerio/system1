FROM clojure
RUN pwd && ls
RUN cd system1
RUN lein with-profile integration-test migrate
RUN lein uberjar
RUN cd ../
ADD target/system1.jar /opt/system1/
WORKDIR "/opt/system1/"
CMD ["java","-jar","system1.jar"]
EXPOSE 8080
