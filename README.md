# TERMINAL MANAGEMENT SYSTEM
#### The set-top box terminal management system is the university graduation project of student Nguyen Thanh Chung - School of Electrical and Electronics Engineering of Hanoi University of Science and Technology.
###### If you have any questions, you can contact me via mail: chunhthanhde.dev@gmail.com
```
Dear programer

when I wrote this code, only god
and I knew how it worked
Now, only god know it

Therefore, if you are trying to optimize
this routine and it fails (most surely),
please increase this counter as a
warning for the next person :(

total_hours_wasted_here = 230
 
 
Best regards

ChunhThanhDe
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:3306/{name_database}
spring.datasource.username= postgres
spring.datasource.password= password

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

# App Properties
TMS.app.jwtSecret=ChunhthanhdeSecretKey
TMS.app.jwtExpirationMs=86400000
```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/{name_database}
spring.datasource.username= root
spring.datasource.password= {root_password}

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

#if hosting by ubuntu
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver

# App Properties
TMS.app.jwtSecret=ChunhthanhdeSecretKey
TMS.app.jwtExpirationMs=86400000
```
## Run Spring Boot application
```
mvn spring-boot:run
```
## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```


