FROM openjdk:8-jdk
COPY . /src/main
WORKDIR /src/main
RUN javac src/main/java/com/commandoby/sonyShop/Application.java
CMD ["java", "Application"]