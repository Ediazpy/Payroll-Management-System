# 💼 Payroll Management System

**Status:** ✅ **Fully Functional & Production Ready**

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![SQLite](https://img.shields.io/badge/SQLite-3.x-green.svg)](https://www.sqlite.org/)
[![License](https://img.shields.io/badge/License-Educational-yellow.svg)](#license)

A complete, professional-grade Java application for managing employee payroll with commission-based calculations. Features a modern Swing GUI, SQLite database integration, and comprehensive CRUD operations.

---

## 📋 Table of Contents

- [Features](#-features)
- [Screenshots](#-screenshots)
- [Quick Start](#-quick-start)
- [Installation](#-installation)
- [Usage Guide](#-usage-guide)
- [Technical Details](#-technical-details)
- [Project Structure](#-project-structure)
- [Database Schema](#-database-schema)
- [Building from Source](#-building-from-source)
- [Security](#-security)
- [Troubleshooting](#-troubleshooting)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

---

## ✨ Features

### 🎯 Core Functionality

#### Employee Management
- ✅ **Full CRUD Operations**: Create, Read, Update, Delete employee records
- ✅ **Database Persistence**: Automatic SQLite database with transactional support
- ✅ **Input Validation**: Comprehensive validation on all user inputs
- ✅ **Unique ID Enforcement**: Prevents duplicate employee IDs
- ✅ **Real-time Updates**: Instant synchronization between UI and database

#### Payroll Calculations
- ✅ **Commission-Based Pay**: Formula: `((Sales - Parts) / 2) + Tips`
- ✅ **Job-by-Job Tracking**: Calculate earnings for individual sales
- ✅ **Daily Sales Tracking**: Assign jobs to specific days of the week
- ✅ **Weekly Totals**: Automatic calculation with daily breakdown
- ✅ **Formatted Currency**: Professional money formatting throughout

#### User Interface
- ✅ **Dual-Tab Design**:
  - **Information Tab**: Job entry and weekly tracking
  - **Employee List Tab**: Database management interface
- ✅ **Table View**: Sortable, scrollable employee data grid
- ✅ **Dialog-Based Forms**: Modal dialogs for add/edit operations
- ✅ **Confirmation Prompts**: Safety checks for delete operations
- ✅ **Error Handling**: User-friendly error messages

### 🔒 Security & Data Integrity

- ✅ **SQL Injection Prevention**: PreparedStatements throughout
- ✅ **Input Sanitization**: All inputs validated before processing
- ✅ **Type Safety**: Strong typing with numeric validation
- ✅ **Non-negative Constraints**: Currency values cannot be negative
- ✅ **Transaction Management**: Proper connection handling

---

## 📸 Screenshots

> **Note**: Screenshots show the application's dual-tab interface with job calculations and employee management features.

### Information Tab - Job Calculations
The main interface for calculating individual job earnings and tracking weekly sales by day.

### Employee List Tab - Database Management
Complete CRUD interface with table view, add/edit/delete dialogs, and refresh functionality.

---

## 🚀 Quick Start

### Prerequisites
- **Java 11 or higher** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi)) - Optional but recommended

### Run in 3 Steps

```bash
# 1. Clone the repository
git clone https://github.com/Ediazpy/Payroll-Management-System.git
cd Payroll-Management-System

# 2. Build the application
mvn clean package

# 3. Run the application
java -jar target/payroll-system.jar
```

**That's it!** The application will launch and create the database automatically.

---

## 📦 Installation

### Option 1: Using Maven (Recommended)

Maven will automatically download all dependencies (SQLite JDBC driver).

```bash
# Clean previous builds
mvn clean

# Compile and package
mvn package

# Run the application
java -jar target/payroll-system.jar
```

### Option 2: Using Pre-built JAR

Download the latest release JAR file:

```bash
# Download from releases (example)
wget https://github.com/Ediazpy/Payroll-Management-System/releases/download/v1.0.0/payroll-system.jar

# Run directly
java -jar payroll-system.jar
```

### Option 3: Manual Compilation

If you don't have Maven, you can compile manually:

```bash
# 1. Create lib directory
mkdir -p lib

# 2. Download SQLite JDBC driver
wget https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.44.1.0/sqlite-jdbc-3.44.1.0.jar -O lib/sqlite-jdbc.jar

# 3. Compile all Java files
javac -cp ".:lib/*" *.java src/*.java

# 4. Run the main GUI
java -cp ".:lib/*" PayrollGUI
```

**Windows Users**: Replace `:` with `;` in classpath:
```cmd
javac -cp ".;lib/*" *.java src/*.java
java -cp ".;lib/*" PayrollGUI
```

---

## 📖 Usage Guide

### 1. Information Tab - Daily Job Calculations

This tab is used to calculate earnings for individual jobs and track them by day.

#### Calculate a Job Total
1. Enter **Sales** amount (total sale value)
2. Enter **Parts** cost (parts used in the sale)
3. Enter **Tips** received
4. Click **Calculate Job**
5. View the calculated commission in the popup

**Formula**: Commission = `((Sales - Parts) / 2) + Tips`

#### Track Weekly Sales
1. Calculate a job first
2. Select a **day of the week** from the dropdown
3. Click **Load Total** to assign the job to that day
4. Repeat for multiple jobs throughout the week
5. Click **Calculate Week** to see total weekly earnings

The weekly sales list shows cumulative totals for each day.

### 2. Employee List Tab - Database Management

This tab provides full employee database management.

#### Add a New Employee
1. Click **Add Employee** button
2. Fill in the dialog form:
   - **Employee ID**: Unique positive integer
   - **Name**: Employee's full name
   - **Address**: Employee's address
   - **Sales**: Current/initial sales amount (default: 0.0)
   - **Tips**: Current tips (default: 0.0)
   - **Parts**: Parts cost (default: 0.0)
3. Click **OK** to save

The system validates all inputs and prevents duplicate IDs.

#### Edit an Employee
1. Select an employee row in the table
2. Click **Edit Employee**
3. Modify the desired fields (ID cannot be changed)
4. Click **OK** to save changes

#### Delete an Employee
1. Select an employee row in the table
2. Click **Delete Employee**
3. Confirm the deletion in the prompt
4. Employee is permanently removed from database

#### Refresh Data
Click **Refresh** to reload all employee data from the database.

---

## 🔧 Technical Details

### Architecture

```
┌─────────────────────────────────────────┐
│         PayrollGUI (Swing UI)           │
│  - Information Tab                      │
│  - Employee List Tab                    │
│  - Event Handlers                       │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│         DataIO (Database Layer)         │
│  - CRUD Operations                      │
│  - Connection Management                │
│  - PreparedStatements                   │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      Employee (Data Model)              │
│  - Encapsulation                        │
│  - Validation Logic                     │
│  - Business Rules                       │
└─────────────────────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      SQLite Database (database.db)      │
│  - employee table                       │
│  - Automatic creation                   │
└─────────────────────────────────────────┘
```

### Components

#### Employee.java
**Responsibility**: Data model and business logic

- **Attributes**: employeeID, name, address, sales, tips, parts
- **Validation**: All setters include input validation
- **Calculations**: `calculateWeeklySales()` method
- **Helpers**: Static validation methods (`isValidID()`, `isValidNumber()`)
- **Formatting**: `getDetails()` for display, `toString()` override

**Key Methods**:
```java
public double calculateWeeklySales()  // Commission calculation
public static boolean isValidID(String id)  // ID validation
public static boolean isValidNumber(String num)  // Numeric validation
```

#### DataIO.java
**Responsibility**: Database operations and persistence

- **Connection**: SQLite JDBC with connection string `jdbc:sqlite:database.db`
- **Initialization**: Auto-creates `employee` table if not exists
- **CRUD**: `add()`, `getList()`, `getById()`, `update()`, `delete()`
- **Helpers**: `exists()` for ID uniqueness checking
- **Security**: Uses PreparedStatements to prevent SQL injection

**Key Methods**:
```java
public void add(Employee emp)  // Insert new employee
public boolean update(Employee emp)  // Update existing employee
public boolean delete(int employeeID)  // Delete by ID
public Employee getById(int employeeID)  // Fetch single employee
public ArrayList<Employee> getList()  // Fetch all employees
public boolean exists(int employeeID)  // Check if ID exists
```

#### PayrollGUI.java
**Responsibility**: User interface and interaction

- **Framework**: Java Swing with NetBeans Form Editor
- **Tabs**: JTabbedPane with Information and Employee List panels
- **Models**: DefaultListModel for job list and weekly sales list
- **Tables**: JTable with DefaultTableModel for employee grid
- **Dialogs**: JOptionPane for add/edit forms and messages
- **Integration**: DataIO instance for database operations

**Key Features**:
- Event-driven architecture
- Real-time validation feedback
- Automatic data refresh
- User-friendly error messages
- Professional formatting (DecimalFormat)

### Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| SQLite JDBC | 3.44.1.0 | Database connectivity |
| Java SE | 11+ | Runtime environment |
| Maven | 3.6+ | Build automation (optional) |

---

## 📁 Project Structure

```
Payroll-Management-System/
├── 📄 Employee.java              # Employee data model with validation
├── 📄 DataIO.java                # Database operations (CRUD)
├── 📄 PayrollGUI.java            # Main Swing GUI application
│
├── 📁 src/
│   └── 📄 Main.java              # Test/demo class
│
├── 📄 pom.xml                    # Maven build configuration
├── 📄 README.md                  # This file
├── 📄 .gitignore                 # Git ignore rules
│
├── 📁 target/                    # Maven build output (generated)
│   └── 📦 payroll-system.jar     # Executable JAR file
│
├── 📁 lib/                       # External libraries (manual build)
│   └── sqlite-jdbc-3.44.1.0.jar
│
└── 💾 database.db                # SQLite database (auto-created on first run)
```

---

## 🗄️ Database Schema

### `employee` Table

```sql
CREATE TABLE IF NOT EXISTS employee (
    employeeID      INTEGER PRIMARY KEY,
    EmployeeName    TEXT,
    EmployeeAddress TEXT,
    Sales           REAL,
    Tips            REAL,
    Parts           REAL
);
```

**Column Descriptions**:

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| `employeeID` | INTEGER | PRIMARY KEY | Unique employee identifier |
| `EmployeeName` | TEXT | - | Employee's full name |
| `EmployeeAddress` | TEXT | - | Employee's address |
| `Sales` | REAL | - | Total sales amount |
| `Tips` | REAL | - | Tips received |
| `Parts` | REAL | - | Cost of parts used |

**Notes**:
- `employeeID` must be unique and positive
- All monetary values (Sales, Tips, Parts) are validated as non-negative
- Database file created automatically as `database.db` in application directory

---

## 🛠️ Building from Source

### Clone the Repository

```bash
git clone https://github.com/Ediazpy/Payroll-Management-System.git
cd Payroll-Management-System
```

### Build with Maven

```bash
# Clean and build
mvn clean package

# Run tests (if any)
mvn test

# Generate JavaDoc
mvn javadoc:javadoc

# Create distribution package
mvn clean package assembly:single
```

### Build Artifacts

After building, you'll find:
- **payroll-system.jar** in `target/` - Executable fat JAR with all dependencies
- **payroll-management-system-1.0.0.jar** in `target/` - Regular JAR (requires classpath)

### Run the Application

```bash
# Using the fat JAR (recommended)
java -jar target/payroll-system.jar

# Or specify main class
java -cp target/payroll-system.jar PayrollGUI
```

---

## 🔒 Security

This application implements multiple security measures:

### SQL Injection Prevention
✅ All database operations use **PreparedStatements**
```java
PreparedStatement pstmt = conn.prepareStatement(
    "INSERT INTO employee (employeeID, EmployeeName, ...) VALUES (?,?,...)");
pstmt.setInt(1, emp.getEmployeeID());
```

### Input Validation
✅ **Type checking** for all numeric inputs
✅ **Range validation** (e.g., positive IDs, non-negative amounts)
✅ **Null/empty checks** for required fields
✅ **Trim whitespace** from text inputs

### Data Integrity
✅ **Unique ID enforcement** via `exists()` check before insert
✅ **Transaction handling** with proper connection management
✅ **Non-negative constraints** on currency values
✅ **Setter validation** in Employee class

### Best Practices
✅ Proper exception handling throughout
✅ Logging with java.util.logging
✅ User-friendly error messages (no stack traces shown to users)
✅ Read-only table in GUI (prevents accidental inline edits)

---

## 🐛 Troubleshooting

### Common Issues

#### 1. "Database locked" error
**Cause**: Another instance of the application is running
**Solution**: Close all instances and restart

#### 2. "ClassNotFoundException: org.sqlite.JDBC"
**Cause**: SQLite JDBC driver not found
**Solution**:
- If using Maven: Run `mvn clean package` to download dependencies
- If manual: Download sqlite-jdbc.jar to `lib/` directory

#### 3. Application won't start on macOS
**Cause**: Security settings blocking Java application
**Solution**:
```bash
# Allow the application
xattr -d com.apple.quarantine payroll-system.jar
```

#### 4. "Module not found" error (Java 9+)
**Cause**: Module system issues
**Solution**: Run with compatibility flags
```bash
java --add-opens java.base/java.lang=ALL-UNNAMED -jar payroll-system.jar
```

#### 5. Numbers not formatting correctly
**Cause**: Locale settings
**Solution**: Application uses `DecimalFormat("$#,##0.00")` - adjust in code if needed for different currency

### Debug Mode

Run with verbose logging:
```bash
java -Djava.util.logging.config.file=logging.properties -jar payroll-system.jar
```

### Getting Help

- Check [Issues](https://github.com/Ediazpy/Payroll-Management-System/issues) page
- Review the source code comments
- Contact the author (see below)

---

## 🚧 Future Enhancements

Potential features for future versions:

- [ ] **Export Functionality**: Export to PDF, Excel, CSV
- [ ] **Reporting**: Generate payroll reports by date range
- [ ] **Year-to-Date Tracking**: Implement YTD pay calculations
- [ ] **Multi-User Support**: User authentication and roles
- [ ] **Backup/Restore**: Database backup and restore functionality
- [ ] **Advanced Filtering**: Search and filter employees
- [ ] **Tax Calculations**: Integrate tax computation
- [ ] **Print Support**: Print employee records and reports
- [ ] **Cloud Sync**: Optional cloud database synchronization
- [ ] **Mobile Companion**: Mobile app for viewing data

---

## 🤝 Contributing

Contributions are welcome! This is an educational project, but improvements are always appreciated.

### How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines

- Follow existing code style and conventions
- Add comments for complex logic
- Test thoroughly before submitting
- Update documentation as needed

---

## 👨‍💻 Author

**Emmanuel Diaz**

- 📧 Email: emmanueldiaz24@gmail.com
- 💼 GitHub: [@Ediazpy](https://github.com/Ediazpy)

---

## 📄 License

This project is for **educational purposes** demonstrating:
- Java Swing GUI development
- JDBC database integration
- OOP principles and design patterns
- Software engineering best practices

Feel free to use this code for learning, teaching, or as a foundation for your own projects.

---

## 🙏 Acknowledgments

- Built with **Java Swing** framework
- Database powered by **SQLite**
- Build automation by **Apache Maven**
- Form design with **NetBeans IDE**

---

## 📊 Project Stats

- **Language**: Java 11+
- **LOC**: ~1,500+ lines of code
- **Classes**: 3 main classes
- **Methods**: 30+ methods
- **Database Tables**: 1 table (employee)
- **Dependencies**: 1 external (SQLite JDBC)

---

<div align="center">

**⭐ If you find this project helpful, please consider giving it a star! ⭐**

Made with ❤️ by Emmanuel Diaz

</div>
