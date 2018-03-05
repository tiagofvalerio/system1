FROM clojure
ADD system1/target/system1.jar /opt/system1/
WORKDIR "/opt/system1/"
CMD ["java","-jar","system1.jar"]
EXPOSE 8080
