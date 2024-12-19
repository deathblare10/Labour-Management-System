# Labour-Management-System

### Introduction

The **Labour Management System** is a comprehensive Java-based GUI application designed to simplify the management of labour-related information for organizations. Built using **Java Swing** for the graphical user interface and **MySQL** as the database backend, this application provides an efficient, user-friendly platform to manage, update, and retrieve data about workers in an organization.

#### Key Features:
1. **Add Labour Details**: Allows users to add new labour records, including information such as name, age, contact number, job role, salary, and status.
2. **View All Labour Records**: Displays all existing labour records from the database in a structured format, making it easy to analyze the data.
3. **Update Labour Status**: Facilitates the modification of the status (e.g., Active/Inactive) of an existing labour record.
4. **Delete Labour**: Provides an option to delete a labour record based on their unique ID.
5. **User-Friendly Interface**: The GUI is intuitive, with clear input fields, buttons, and an output area for viewing the results or errors.

#### Components:
1. **Frontend (Java Swing)**:
   - A visually appealing interface created using **Swing**, with panels, labels, text fields, buttons, and a scrollable output area.
   - The interface is designed to work on high-resolution screens (1920x1080) for better readability and usability.
   
2. **Backend (MySQL)**:
   - The application uses a **MySQL database** (`LabourDB`) to store and manage labour records.
   - A single table `labour` is used to hold all essential information such as ID, name, age, contact number, job role, salary, and status.

3. **Database Connectivity**:
   - The program establishes a connection to the MySQL database using the `JDBC` driver.
   - All SQL operations, including `INSERT`, `SELECT`, `UPDATE`, and `DELETE`, are executed via prepared statements, ensuring security and efficiency.

#### Technical Requirements:
- **Java Development Kit (JDK)**: Version 8 or later.
- **MySQL Server**: For database management.
- **JDBC Driver**: `mysql-connector-java` to connect Java to the MySQL database.

#### Example Use Cases:
- **HR Management**: An HR team can use this system to manage labour information in an organization.
- **Construction Projects**: Helps in managing workers' records on construction sites.
- **Small/Medium Businesses**: Tracks and updates employee information efficiently.

#### How It Works:
1. The user interacts with the GUI to perform actions like adding, viewing, deleting, or updating labour records.
2. The application connects to the MySQL database and executes the corresponding SQL commands.
3. Results are displayed in a text area for the user, along with error messages in case of any issues.

#### Benefits:
- **Efficiency**: Reduces the time and effort needed to manage large amounts of labour-related data.
- **Data Integrity**: Ensures accurate data storage and retrieval through robust SQL operations.
- **Usability**: Simplifies complex database operations with an easy-to-use graphical interface.

This project is an excellent demonstration of combining Java and MySQL to create a functional and practical application, showcasing skills in GUI development, database design, and software engineering.
