# HRMS & Recruitment Management System | Spring Boot, Thymeleaf, MySQL, Java

[![GitHub stars](https://img.shields.io/github/stars/kumar-ayansh/hrms?style=for-the-badge)](https://github.com/kumar-ayansh/hrms/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/kumar-ayansh/hrms?style=for-the-badge)](https://github.com/kumar-ayansh/hrms/network/members)
[![GitHub issues](https://img.shields.io/github/issues/kumar-ayansh/hrms?style=for-the-badge)](https://github.com/kumar-ayansh/hrms/issues)
[![Java 17](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-Framework-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Templates-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

A web-based **Human Resource Management System (HRMS)** and **recruitment management application** built with **Java**, **Spring Boot**, **Spring MVC**, **Thymeleaf**, and **MySQL**. The project combines public job-portal pages with dedicated admin and job-seeker workflows for employee/recruitment operations.

> Note on language composition: GitHub repository language detection may show JavaScript as primary because of extensive static frontend assets, while application backend logic is implemented in Java/Spring Boot.

## Table of Contents
- [Why this project](#why-this-project)
- [Feature overview](#feature-overview)
  - [Admin experience](#admin-experience)
  - [User / Job Seeker experience](#user--job-seeker-experience)
  - [Public / Recruitment experience](#public--recruitment-experience)
- [Architecture & technology stack](#architecture--technology-stack)
- [Project structure](#project-structure)
- [Request & data flow (high-level)](#request--data-flow-high-level)
- [Configuration](#configuration)
- [Prerequisites](#prerequisites)
- [Local setup](#local-setup)
- [Security & environment guidance](#security--environment-guidance)
- [SEO implementation guidance (for deployment)](#seo-implementation-guidance-for-deployment)
- [Roadmap (future improvements)](#roadmap-future-improvements)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [Support / contact](#support--contact)
- [License](#license)

## Why this project
- Centralizes **HR and recruitment operations** in one web app.
- Offers role-oriented journeys for **admin** and **job seekers**.
- Uses common enterprise Java technologies for maintainable development.
- Supports public hiring visibility with job listing and enquiry pages.

## Feature overview

### Admin experience
- Admin login and session-based dashboard access.
- Post and manage job listings.
- View registered job seekers.
- View public enquiries.
- Review feedback/complaints/suggestions and update response status/replies.
- Change admin password.

### User / Job Seeker experience
- Job seeker registration and login.
- User dashboard with personalized context.
- View available jobs.
- Submit responses (feedback/suggestions/complaints) and track status.
- Delete own responses.
- Change password and logout.

### Public / Recruitment experience
- Public landing page and informational pages.
- Public pages for `about us`, `contact us`, and `jobs`.
- Contact/enquiry submission flow.
- Public login and registration entry points.

## Architecture & technology stack
- **Language & runtime:** Java 17
- **Backend framework:** Spring Boot
- **Web layer:** Spring MVC
- **Templating/UI:** Thymeleaf templates
- **Persistence:** Spring Data JPA
- **Database:** MySQL (Connector/J)
- **Build tooling:** Maven Wrapper (`mvnw`, `mvnw.cmd`)

## Project structure

```text
hrms/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    └── main/
        ├── java/com/example/hrms/
        │   ├── HrmsApplication.java
        │   ├── controller/
        │   ├── dto/
        │   ├── model/
        │   └── repo/
        └── resources/
            ├── application.properties
            ├── templates/
            │   ├── index.html, aboutus.html, contactus.html, jobs.html
            │   ├── login.html, registration.html, adminlogin.html
            │   ├── admin/
            │   └── user/
            └── static/
                ├── css/, js/, images/
                ├── admin/assets/
                └── user/assets/
```

## Request & data flow (high-level)
1. Browser requests public/admin/user routes handled by Spring MVC controllers.
2. Controllers prepare model data and return Thymeleaf templates.
3. User/admin actions (registration, login, job posting, enquiries, responses) submit form data.
4. Controllers map DTO/request data to entities and persist/query via Spring Data JPA repositories.
5. Views render updated content from the MySQL-backed data store.

## Configuration
Core runtime values from [`src/main/resources/application.properties`](src/main/resources/application.properties):
- Application name: `hrms`
- Database URL target: `jdbc:mysql://localhost:3306/hrmsdb`
- Default server port: `8181`
- JPA schema mode: `spring.jpa.hibernate.ddl-auto=update`

## Prerequisites
- JDK 17
- MySQL 8+ (or compatible)
- Git

## Local setup

### 1) Clone and enter project
```bash
git clone https://github.com/kumar-ayansh/hrms.git
cd hrms
```

### 2) Create database
```sql
CREATE DATABASE hrmsdb;
```

### 3) Configure database credentials securely
Do **not** commit real credentials. Set values in external configuration or environment variables before running.

### 4) Run the app

**macOS / Linux**
```bash
./mvnw spring-boot:run
```

**Windows (PowerShell / CMD)**
```bat
mvnw.cmd spring-boot:run
```

### 5) Open locally
- `http://localhost:8181`

## Security & environment guidance
- Never commit passwords, tokens, or production secrets.
- Prefer externalized config (environment variables, runtime profiles, or deployment secrets manager).
- Review `spring.jpa.hibernate.ddl-auto=update` before production use; controlled migrations are typically safer for production data integrity.
- Restrict database user privileges to least-required access.

## SEO implementation guidance (for deployment)
The following are recommendations for your deployed application pages (not claims of current implementation):

- **Suggested page title pattern:**
  - `HRMS | Human Resource Management & Recruitment Portal`
- **Suggested meta description:**
  - `HRMS is a Human Resource Management System and recruitment management portal for job posting, candidate registration, and hiring workflow management using Spring Boot, Thymeleaf, and MySQL.`
- **Target keywords:**
  - `Human Resource Management System`, `HRMS`, `employee management`, `recruitment management`, `job portal`, `Spring Boot HRMS`, `Thymeleaf job portal`, `Java MySQL recruitment system`
- **Open Graph / social preview:**
  - Add `og:title`, `og:description`, `og:image`, `og:url`, and matching Twitter card tags for public pages.
- **Canonical URL:**
  - Use canonical tags per public page to avoid duplicate content signals.
- **Semantic headings:**
  - Ensure each page has one clear `<h1>` and logical `<h2>/<h3>` hierarchy.
- **Sitemap & robots:**
  - Publish `sitemap.xml` and maintain `robots.txt` rules aligned with indexable public content.

## Roadmap (future improvements)
- [ ] Add production-grade authentication hardening and password storage improvements.
- [ ] Add role/permission refinement and stricter access controls.
- [ ] Introduce formal database migration tooling for production lifecycle.
- [ ] Expand automated testing coverage for controllers, repositories, and UI flows.
- [ ] Improve observability (structured logs/metrics) for operations.
- [ ] Add deployment documentation for staging/production environments.

## Troubleshooting
- **Port already in use:** change `server.port` locally or free port `8181`.
- **Database connection errors:** verify MySQL is running, DB name is `hrmsdb`, and credentials are configured correctly.
- **Template/static asset issues:** confirm files exist under `src/main/resources/templates` and `src/main/resources/static`.
- **Build execution permission (Linux/macOS):** run `chmod +x mvnw` if needed.

## Contributing
1. Fork the repository.
2. Create a feature branch.
3. Make focused changes and test locally.
4. Open a pull request with a clear summary.

## Support / contact
- Use the [Issues](https://github.com/kumar-ayansh/hrms/issues) tab for bug reports and feature requests.

## License
No explicit open-source license is currently declared at the repository root. Add a `LICENSE` file to define usage terms.
