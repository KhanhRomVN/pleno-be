# Sử dụng image chính thức của OpenJDK 21
FROM openjdk:21-jdk-slim as build

# Cài đặt Maven
RUN apt-get update && apt-get install -y maven

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file pom.xml và src vào container
COPY pom.xml .
COPY src ./src

# Build ứng dụng với Maven
RUN mvn clean package -DskipTests

# Giai đoạn runtime
FROM openjdk:21-jdk-slim

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR từ giai đoạn build
COPY --from=build /app/target/*.jar app.jar

# Expose cổng mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]