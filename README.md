# GitHub All Stars Service

## Overview

Find out the gitHub all-stars! - Code Challenge

This project is a Spring Boot application designed to interface with GitHub's API to retrieve and process information about starred repositories. It's built using Java 17 and Maven.

## Prerequisites

- JDK 17
- Maven 3.6 or newer
- IntelliJ IDEA (Recommended IDE)

## Setting Up Your Development Environment

1. **Install Java 17**: Ensure JDK 17 is installed on your system. You can download it from [AdoptOpenJDK](https://adoptopenjdk.net/) or the vendor of your choice.

2. **Install Maven**: Download and install Maven from [Apache Maven Project](https://maven.apache.org/download.cgi). Follow the installation instructions provided on the site.

3. **Setup IntelliJ IDEA**: If you haven't already, download and install IntelliJ IDEA from [JetBrains](https://www.jetbrains.com/idea/download/). The Community Edition is free.

4. **Clone the Repository**: Clone the project repository to your local machine using the following command:
    ```bash
    git clone https://github.com/ildefonso-nuno/github-allstars.git
    ```
   Replace `https://github.com/ildefonso-nuno/github-allstars.git` with the actual URL of your GitHub repository.

5. **Open the Project in IntelliJ IDEA**:
    - Open IntelliJ IDEA.
    - Select `File` > `Open` from the menu.
    - Navigate to the directory where you cloned the project, select the project, and click `OK`.

6. **Configure the JDK**:
    - In IntelliJ, navigate to `File` > `Project Structure` > `Project`.
    - From the `Project SDK` dropdown, select JDK 17. If it's not listed, click `Add SDK` and specify the JDK 17 path on your machine.

## Running the Application

1. **First locate the `application.properties` file and insert the github api token in `github.api.token=xxxxxxxxx`. Without this the application will not work.**

### From IntelliJ IDEA

1. Locate the GitHubAllStarsApplication file in the project (src/main/java/org/nildefonso/githuballstars/GitHubAllStarsApplication.java).
2. Right-click on the file and select `Run 'Application'` to start the application.

### Using Maven

To run the application from the command line using Maven, navigate to the root directory of the project and execute the following command:

```bash
mvn spring-boot:run
```

## Accessing and Using the Application

1. You can locally access the application in http://localhost:8080/

2. You can use swagger to test the application in http://localhost:8080/swagger-ui/index.html

3. There are two methods developed:
    - incompleteresults: This method will only call the github api 1 time and CAN retrieve incomplete results, depending on date and language inputs.
    - completeresults: This method will call the github api 1 one time for each 6 months span, and will return the complete results.

4. Usage:
    - Date needs to be in format YYYY-MM-DD
    - Top can be any integer between 1 and 100
    - Language can be any programming language



