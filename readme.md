# Competition Management System (QuizApp)

**Module:** 5CS019 – Object Oriented Design and Programming  
**Student:** Piyush Rauniyar  
**Student ID:** 2005494  
**Date:** February 2026  

---

## 1. Project Overview

This application is a **Java Swing–based Competition Management System** designed to manage competitors, administer quizzes, and generate statistical reports. All data is persisted using a **MySQL database**, and the system supports two distinct user roles:

- **Player Mode**
  - Users can register or log in.
  - Players attempt a **5-question quiz**.
  - Scores are calculated by **dropping the minimum and maximum values**.
  - Final scores are stored in the database.

- **Manager Mode**
  - Password-protected administrative dashboard.
  - View all competitor details.
  - Search for competitors by ID.
  - Analyse overall statistics (top performers, score frequency).

---

## 2. GitHub Repository

**Project Link:**  
[https://github.com/Piyush-Rauniyar1/Quiz-App-oodp.git]

---

## 3. Directory Structure

The project follows the **MVC (Model–View–Controller)** architecture:

```text
.
├── src/
│   ├── Main.java                # Entry point of the application
│   ├── controller/              # Application logic
│   │   └── Manager.java
│   ├── databaseconfig/          # Database connection configuration
│   │   └── DBConnection.java
│   ├── gui/                     # Java Swing user interfaces
│   │   ├── LoginGUI.java
│   │   ├── ManagerReportGUI.java
│   │   ├── QuizGUI.java
│   │   └── ... (other screens)
│   ├── model/                   # Data models
│   │   ├── PiyCompetitor.java
│   │   ├── CompetitorList.java
│   │   └── Name.java
│   └── test/                    # JUnit test cases
│       ├── CompetitorTest.java
│       └── DBTest.java
├── doc/                         # Generated Javadoc documentation
├── CompetitionDB.sql            # MySQL database schema & sample data
└── README.md                    # Project documentation


4. Setup & Installation
4.1 Prerequisites

Java Development Kit (JDK): Version 8 or higher

MySQL Server: Running on localhost:3306

MySQL JDBC Driver: Added to the project classpath

4.2 Database Setup

Open MySQL Workbench or a terminal.

Import the provided CompetitionDB.sql file.

Terminal Command:

mysql -u root -p CompetitionDB < CompetitionDB.sql


This will:

Create the CompetitionDB database

Populate it with sample data

4.3 Configure Database Connection

Navigate to:

src/databaseconfig/DBConnection.java


Verify the database credentials:

Default User: root

Default Password: (empty)

If your local MySQL server uses a password, update the PASSWORD constant accordingly.

4.4 Run the Application

Open the project in IntelliJ IDEA (or Eclipse).

Run the following file:

src/Main.java

5. Login Credentials

Use the following credentials to test the different user modes.

5.1 Manager / Admin Mode

Access the Manager Report from the main menu.
This section is password-protected.

Role Password
Manager admin123
5.2 Player Mode

You may register a new user, or log in using the following pre-existing test accounts:

Role User ID Password Status
Player 1 1001 player123 Existing data
Player 2 1006 player34 Existing data

6. Testing

Unit testing is implemented using JUnit.

Test cases validate:

Competitor object behaviour

Database connectivity and data integrity

Test files are located in:

src/test/

7. Documentation

Full API documentation is generated using Javadoc.

The generated HTML files are available in the doc/ directory.
