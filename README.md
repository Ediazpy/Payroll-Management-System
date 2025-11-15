# Payroll Management System

**Status:** ✅ Fully Functional

## Description
A complete Java-based Payroll Management System featuring a modern Swing GUI and SQLite database integration. This application demonstrates professional **OOP principles**, **JDBC database operations**, and **Swing GUI development**.

### Components
- `Employee.java` – Employee data model with validation
- `DataIO.java` – Complete SQLite database operations (CRUD)
- `PayrollGUI.java` – Full-featured Swing GUI with dual-tab interface
- `src/Main.java` – Simple test/demo class

## Features

### ✅ Completed Features

#### Employee Management
- Add, edit, and delete employees
- Full CRUD operations with SQLite database
- Automatic data persistence
- Input validation for all fields
- Unique employee ID enforcement

#### Job & Payroll Calculations
- Calculate individual job totals (commission-based)
- Formula: `((Sales - Parts) / 2) + Tips`
- Track daily job totals
- Load job totals to specific days of the week
- Calculate weekly earnings with daily breakdown

#### User Interface
- **Information Tab**: Enter job details and calculate earnings
  - Employee information input
  - Job calculation with sales, parts, and tips
  - Weekly sales tracker with day selection
  - Real-time calculation and display

- **Employee List Tab**: Manage employee database
  - Table view with all employee data
  - Add Employee dialog with validation
  - Edit Employee functionality
  - Delete Employee with confirmation
  - Refresh data from database
  - Display weekly pay calculations

#### Database Features
- Automatic SQLite database creation
- Prepared statements to prevent SQL injection
- Transaction management
- Error handling and user feedback
- Data validation before storage

## Installation & Usage

### Prerequisites
- Java 11 or higher
- Maven (for building)

### Building with Maven

```bash
# Clean and build the project
mvn clean package

# Run the application
java -jar target/payroll-system.jar
```

### Manual Compilation (without Maven)

```bash
# Download SQLite JDBC driver first
# wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.44.1.0/sqlite-jdbc-3.44.1.0.jar -P lib/

# Compile all Java files
javac -cp ".:lib/*" *.java src/*.java

# Run the GUI application
java -cp ".:lib/*" PayrollGUI

# Or run the test Main class
java -cp ".:lib/*" src.Main
```

## Project Structure

```
Payroll-Management-System/
├── Employee.java           # Employee data model
├── DataIO.java             # Database operations
├── PayrollGUI.java         # Main GUI application
├── src/
│   └── Main.java           # Test/demo class
├── pom.xml                 # Maven build configuration
├── README.md               # This file
├── .gitignore              # Git ignore file
└── database.db             # SQLite database (auto-created)
```

## How to Use

### 1. Information Tab - Job Calculations
1. Enter employee ID, name, and address (optional, for reference)
2. Enter Sales, Parts, and Tips for a job
3. Click **Calculate Job** to compute earnings
4. Select a day of the week from dropdown
5. Click **Load Total** to add the job to that day
6. Click **Calculate Week** to see total weekly earnings

### 2. Employee List Tab - Database Management
1. View all employees in the table
2. Click **Add Employee** to create a new employee record
3. Select an employee and click **Edit Employee** to modify
4. Select an employee and click **Delete Employee** to remove
5. Click **Refresh** to reload data from database

## Technical Details

### Employee Class
- Encapsulates employee data with proper validation
- Implements getters/setters with input validation
- Provides calculation methods for weekly sales
- Static validation helper methods

### DataIO Class
- JDBC-based SQLite database operations
- CRUD operations: Create, Read, Update, Delete
- Automatic table creation on initialization
- Uses PreparedStatements for security
- Helper methods: `exists()`, `getById()`

### PayrollGUI Class
- Swing-based graphical interface
- Tab-based navigation
- Dialog-based CRUD operations for employees
- Real-time data validation
- Comprehensive error handling
- Integration with database layer

## Dependencies

- **SQLite JDBC Driver** (org.xerial:sqlite-jdbc:3.44.1.0)
  - Managed automatically via Maven
  - Provides SQLite database connectivity

## Database Schema

```sql
CREATE TABLE employee (
    employeeID INTEGER PRIMARY KEY,
    EmployeeName TEXT,
    EmployeeAddress TEXT,
    Sales REAL,
    Tips REAL,
    Parts REAL
);
```

## Security Features

- Input validation on all user inputs
- SQL injection prevention via PreparedStatements
- Numeric validation for currency fields
- ID uniqueness enforcement
- Non-negative value constraints

## Future Enhancements (Optional)

- Export payroll reports to PDF/CSV
- Year-to-date (YTD) pay tracking
- Multi-user support with authentication
- Backup and restore functionality
- Advanced reporting and analytics
- Tax calculation integration

## Author

Emmanuel Diaz
Email: emmanueldiaz24@gmail.com

## License

This project is for educational purposes demonstrating Java Swing GUI development and database integration.
