<div align="center">

# HRMS

### Human Resource Management System

A server-rendered human resource management application built with Spring Boot, Thymeleaf, Spring Data JPA, and MySQL.

<p>
  <a href="https://github.com/kumar-ayansh/hrms/actions"><img src="https://img.shields.io/github/actions/workflow/status/kumar-ayansh/hrms/maven.yml?branch=main&style=flat-square&label=build" alt="Build status"></a>
  <a href="https://github.com/kumar-ayansh/hrms"><img src="https://img.shields.io/github/repo-size/kumar-ayansh/hrms?style=flat-square" alt="Repository size"></a>
  <a href="https://github.com/kumar-ayansh/hrms/graphs/contributors"><img src="https://img.shields.io/github/contributors/kumar-ayansh/hrms?style=flat-square" alt="Contributors"></a>
  <a href="https://github.com/kumar-ayansh/hrms/blob/main/LICENSE"><img src="https://img.shields.io/github/license/kumar-ayansh/hrms?style=flat-square" alt="License"></a>
</p>

<p>
  <a href="#features">Features</a> •
  <a href="#technology-stack">Technology</a> •
  <a href="#project-structure">Structure</a> •
  <a href="#getting-started">Getting started</a> •
  <a href="#configuration">Configuration</a> •
  <a href="#testing">Testing</a>
</p>

</div>

> **Project status:** Active development. The current application uses a Spring MVC + Thymeleaf architecture with MySQL persistence and a local development server on port `8181`.

## Overview

HRMS is a web-based platform for managing common employee and recruitment workflows. It separates administrative and user-facing experiences and provides server-rendered pages for authentication, job management, enquiries, feedback, complaints, and user account actions.

The application is organized around a conventional Spring Boot layered architecture:

- **Controllers** handle web requests and navigation.
- **DTOs** carry form and request data between the web layer and domain layer.
- **Models** represent persisted HRMS data.
- **Repositories** provide Spring Data JPA persistence access.
- **Thymeleaf templates** render the user interface.
- **Static assets** provide CSS, JavaScript, and images for the frontend.

## Features

### Public experience

- Landing page and informational pages
- About-us and contact-us pages
- Job listing page
- User registration and login entry points

### User experience

- User dashboard
- View available jobs
- Submit responses to job opportunities
- Change user password
- User-specific navigation and layouts

### Administrative experience

- Administrator login and dashboard
- Change administrator password
- Create and manage job postings
- View job seekers
- Review enquiries
- View complaints and feedback
- Separate administrator layouts and navigation

## Technology stack

| Area | Technology |
| --- | --- |
| Backend | Java 17, Spring Boot 4.1.0 |
| Web layer | Spring MVC, Thymeleaf |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL |
| Frontend | HTML, CSS, JavaScript, Thymeleaf templates |
| Build tool | Apache Maven with Maven Wrapper |
| Testing | Spring Boot test starters, JUnit-based application test |

## Architecture

```text
Browser
  │
  ▼
Thymeleaf templates + static CSS/JavaScript
  │
  ▼
Spring MVC controllers
  │
  ├── DTOs for request/form data
  ├── Domain models mapped with JPA
  └── Spring Data repositories
          │
          ▼
       MySQL database
```

The main web modules are exposed through the following controllers:

- `MainController` — public pages and general application navigation
- `AdminController` — administrator authentication and management workflows
- `UserController` — user authentication, dashboard, jobs, responses, and account actions

## Project structure

```text
hrms/
├── pom.xml
├── mvnw                         # Maven Wrapper for Linux/macOS
├── mvnw.cmd                     # Maven Wrapper for Windows
├── src/
│   ├── main/
│   │   ├── java/com/example/hrms/
│   │   │   ├── controller/      # Main, admin, and user request handlers
│   │   │   ├── dto/             # Request and form data transfer objects
│   │   │   ├── model/           # JPA domain entities
│   │   │   └── repo/            # Spring Data repositories
│   │   └── resources/
│   │       ├── static/          # CSS, JavaScript, images, and role assets
│   │       ├── templates/       # Thymeleaf views
│   │       │   ├── admin/
│   │       │   └── user/
│   │       └── application.properties
│   └── test/
│       └── java/                # Application tests
└── README.md
```

### Domain model

The current persistence layer includes models for:

- Administrators
- Users/job seekers
- Job information
- Enquiries
- Responses

Repositories such as `UserRepo`, `JobInfoRepo`, `EnquiryRepo`, `ResponseRepo`, and `AdminInfoRepo` provide database access for these areas.

## Getting started

### Prerequisites

Install the following before running the project:

- Java Development Kit 17 or newer
- MySQL Server 8.x or a compatible MySQL installation
- Git
- A terminal or IDE such as IntelliJ IDEA, Eclipse, or VS Code

Verify Java and Maven Wrapper availability:

```bash
java -version
./mvnw -version
```

On Windows PowerShell, use:

```powershell
java -version
.\mvnw.cmd -version
```

### 1. Clone the repository

```bash
git clone https://github.com/kumar-ayansh/hrms.git
cd hrms
```

### 2. Create the MySQL database

Create the database configured by the application:

```sql
CREATE DATABASE hrmsdb;
```

Make sure the MySQL server is running and that the configured user has permission to create and update tables in `hrmsdb`.

### 3. Configure the database connection

Update `src/main/resources/application.properties` with your local MySQL credentials:

```properties
spring.application.name=hrms
spring.datasource.url=jdbc:mysql://localhost:3306/hrmsdb
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8181
```

> **Security note:** Do not commit real database passwords or production credentials. For production deployments, move secrets to environment variables or an external secrets manager and use a production-safe schema migration strategy instead of relying on `ddl-auto=update`.

### 4. Run the application

Using the Maven Wrapper on Linux/macOS:

```bash
./mvnw spring-boot:run
```

Using the Maven Wrapper on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, build and run the packaged application:

```bash
./mvnw clean package
java -jar target/hrms-0.0.1-SNAPSHOT.jar
```

Open the application at:

```text
http://localhost:8181
```

## Configuration

The default configuration is stored in `src/main/resources/application.properties`.

| Property | Current purpose | Development default |
| --- | --- | --- |
| `spring.application.name` | Application name | `hrms` |
| `spring.datasource.url` | MySQL JDBC connection | `jdbc:mysql://localhost:3306/hrmsdb` |
| `spring.datasource.username` | Database username | `root` |
| `spring.datasource.password` | Database password | Empty in the repository template |
| `spring.jpa.hibernate.ddl-auto` | Hibernate schema behavior | `update` |
| `spring.jpa.show-sql` | Log generated SQL | `true` |
| `server.port` | HTTP port | `8181` |

For production, review database credentials, SQL logging, schema management, session security, password hashing, validation, and error handling before deployment.

## Testing

Run the test suite with:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

The test source tree is located under `src/test/java`. Add controller, repository, service, and integration tests as new functionality is introduced.

## Build commands

| Command | Description |
| --- | --- |
| `./mvnw clean` | Remove generated build output |
| `./mvnw compile` | Compile the application |
| `./mvnw test` | Run automated tests |
| `./mvnw clean package` | Build the executable JAR |
| `./mvnw spring-boot:run` | Start the application in development mode |

## Development workflow

1. Create a feature branch from `main`.
2. Update the relevant controller, DTO, model, repository, template, or static asset.
3. Run `./mvnw test`.
4. Verify the affected flow manually at `http://localhost:8181`.
5. Review database changes before committing entity updates.
6. Open a pull request with a concise summary and testing notes.

## Troubleshooting

### Port `8181` is already in use

Stop the process using the port or change the port in `application.properties`:

```properties
server.port=8080
```

### Cannot connect to MySQL

Check that:

- MySQL is running.
- The `hrmsdb` database exists.
- The JDBC URL uses the correct host and port.
- The username and password are correct.
- The MySQL user can access the database.

### Tables are not created or updated

Confirm that the application can connect to MySQL and that `spring.jpa.hibernate.ddl-auto=update` is enabled for local development. Check the startup logs for the first database or entity mapping error.

### Maven Wrapper cannot execute on Linux/macOS

Grant execute permission once:

```bash
chmod +x mvnw
```

## Contributing

Contributions are welcome. Before submitting a pull request:

- Keep changes focused and consistent with the existing Spring Boot structure.
- Avoid committing credentials, generated files, or local IDE metadata.
- Add or update tests where practical.
- Run the Maven test suite locally.
- Document user-facing behavior and configuration changes.

## License

No license file is currently included in the repository. Until a license is added, the project should be treated as **all rights reserved** and reused only with the author's permission.

## Maintainer

**Ayansh Kumar**

- GitHub: [@kumar-ayansh](https://github.com/kumar-ayansh)
- Repository: [kumar-ayansh/hrms](https://github.com/kumar-ayansh/hrms)
