[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=flat-square&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Playwright](https://img.shields.io/badge/Playwright-1.50.0-2EAD33?style=flat-square&logo=playwright&logoColor=white)](https://playwright.dev/java/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-23D96C?style=flat-square&logo=cucumber&logoColor=white)](https://cucumber.io/)
[![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-D24939?style=flat-square&logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat-square)](LICENSE)

# Prototype API Playwright

A robust API testing framework built with **Java**, **Playwright**, and **Cucumber BDD**. This project demonstrates a clean, maintainable approach to automated API testing using the API Client design pattern, Behaviour-Driven Development with Gherkin syntax, and integrated CI/CD delivery through Jenkins.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Test Scenarios](#test-scenarios)
- [Design Patterns](#design-patterns)
- [Reports](#reports)
- [CI/CD](#cicd)
- [Author](#author)

---

## Features

- **BDD with Cucumber** — Write test scenarios in plain English using Gherkin syntax for living documentation
- **Playwright for API** — Leverage Playwright's built-in `APIRequestContext` for fast, reliable HTTP calls
- **API Client Pattern** — Encapsulated API interactions through dedicated client classes for reusability
- **JSON Handling** — Deserialize and validate API responses with Jackson Databind
- **Structured Logging** — Consistent, configurable logging powered by Log4j 2
- **Cucumber Reports** — Rich HTML reports generated automatically via Maven Cucumber Reporting plugin
- **Jenkins Pipeline** — End-to-end CI/CD with a declarative Jenkinsfile
- **Externalised Configuration** — Base URLs and environment settings managed through `config.properties`

---

## Tech Stack

| Technology              | Version  | Purpose                          |
|-------------------------|----------|----------------------------------|
| Java (OpenJDK)          | 17       | Programming language             |
| Maven                   | 3.9+     | Build and dependency management  |
| Playwright for Java     | 1.50.0   | API request execution            |
| Cucumber JVM            | 7.20.1   | BDD test framework               |
| JUnit 5                 | 5.10+    | Test runner and assertions        |
| Jackson Databind        | 2.17+    | JSON serialization/deserialization|
| Log4j 2                 | 2.20.0   | Logging framework                |
| Maven Cucumber Reporting| 5.8.2    | HTML test report generation      |
| Jenkins                 | Latest   | CI/CD pipeline orchestration     |

---

## Project Structure

```
prototype-api-playwright/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── prototype/
│       │           ├── api/
│       │           │   ├── clients/          # API client classes (e.g., FactApiClient)
│       │           │   ├── models/           # POJO response models (e.g., FactResponse)
│       │           │   ├── stepdefs/         # Cucumber step definitions
│       │           │   └── utils/            # Helpers: config reader, logger setup
│       │           └── runners/
│       │               └── TestRunner.java   # Cucumber-JUnit 5 test runner
│       └── resources/
│           ├── features/
│           │   └── fact_api.feature          # Gherkin feature file
│           ├── config.properties             # Base URL and environment config
│           └── log4j2.xml                    # Log4j 2 configuration
├── Jenkinsfile                               # Declarative CI/CD pipeline
├── pom.xml                                   # Maven project descriptor
└── README.md
```

---

## Prerequisites

| Requirement | Minimum Version |
|-------------|-----------------|
| Java JDK    | 17              |
| Apache Maven| 3.9+            |
| Git         | 2.40+           |

Verify your environment:

```bash
java -version
mvn -version
git --version
```

---

## Getting Started

**1. Clone the repository**

```bash
git clone https://github.com/ridhotadjudin/prototype-api-playwright.git
cd prototype-api-playwright
```

**2. Install dependencies**

```bash
mvn clean install -DskipTests
```

**3. Run the API tests**

```bash
mvn test
```

**4. Generate the HTML report**

```bash
mvn verify
```

The Cucumber HTML report is generated at:

```
target/cucumber-reports/advanced-reports/cucumber-html-reports/overview-features.html
```

---

## Test Scenarios

The `fact_api.feature` file covers the following scenarios against the GET Fact endpoint:

| Scenario                          | Method | Endpoint    | Validation                                      |
|-----------------------------------|--------|-------------|--------------------------------------------------|
| Retrieve a random fact            | GET    | `/fact`     | Status code is `200`                             |
| Verify response contains fact     | GET    | `/fact`     | Response body includes `fact` field (non-empty)  |
| Verify response contains length   | GET    | `/fact`     | Response body includes `length` field (numeric)  |

**Example Gherkin:**

```gherkin
Feature: Fact API

  Scenario: Successfully retrieve a random fact
    Given the Fact API is available
    When I send a GET request to the fact endpoint
    Then the response status code should be 200
    And the response should contain a "fact" field
    And the response should contain a "length" field
```

---

## Design Patterns

### API Client Pattern

All HTTP interactions are encapsulated within dedicated client classes inside `api/clients/`. Each client manages its own Playwright `APIRequestContext`, keeping step definitions clean and focused on test logic.

```
FactApiClient
├── createContext()       → Initialise Playwright API context with base URL
├── getFact()             → Execute GET /fact and return APIResponse
└── dispose()             → Tear down the API context
```

**Benefits:**
- Single Responsibility — clients handle HTTP; step definitions handle assertions
- Reusability — clients can be shared across multiple feature files
- Maintainability — endpoint changes are isolated to one class

### Behaviour-Driven Development (BDD)

Cucumber bridges the gap between technical tests and business requirements. Feature files written in Gherkin serve as both executable specifications and living documentation, making test intent clear to all stakeholders.

---

## Reports

This project uses the **Maven Cucumber Reporting** plugin to generate detailed HTML reports after test execution.

**Report location:**

```
target/cucumber-reports/advanced-reports/cucumber-html-reports/overview-features.html
```

**Report includes:**
- Feature and scenario pass/fail overview
- Step-level execution details with duration
- Tags distribution and trends
- Failure screenshots and error messages

To generate the report:

```bash
mvn verify
```

---

## CI/CD

The project includes a declarative **Jenkinsfile** that automates the full test lifecycle.

### Pipeline Stages

```
┌─────────────┐     ┌───────────────────────┐     ┌─────────────────┐
│  Checkout    │────▶│  Install Dependencies │────▶│  Run API Tests  │
└─────────────┘     └───────────────────────┘     └─────────────────┘
```

| Stage                 | Description                                              |
|-----------------------|----------------------------------------------------------|
| **Checkout**          | Clones the repository from the configured SCM branch     |
| **Install Dependencies** | Executes `mvn clean install -DskipTests` to resolve all Maven dependencies |
| **Run API Tests**     | Executes `mvn test` to run Cucumber scenarios and generate reports |

### Jenkinsfile Overview

```groovy
pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Run API Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                reportDir: 'target/cucumber-reports/advanced-reports/cucumber-html-reports',
                reportFiles: 'overview-features.html',
                reportName: 'Cucumber Report'
            ])
        }
    }
}
```

---

## Author

**Ridho Tadjudin**

- Website: [ridhotadjudin.id](https://ridhotadjudin.id)
- GitHub: [@ridhotadjudin](https://github.com/ridhotadjudin)

---

<div align="center">
  <sub>Built with ☕ Java, 🎭 Playwright, and 🥒 Cucumber</sub>
</div>
