#!/bin/bash

###############################################################################
# Payroll Management System - Release Builder
#
# This script creates a distributable package of the application including:
# - Compiled JAR file with all dependencies
# - Documentation
# - Source code
# - README and license files
#
# Usage: ./build-release.sh
###############################################################################

set -e  # Exit on error

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
APP_NAME="payroll-management-system"
VERSION="1.0.0"
BUILD_DIR="build"
DIST_DIR="dist"
RELEASE_DIR="${DIST_DIR}/${APP_NAME}-${VERSION}"

echo -e "${BLUE}╔════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║  Payroll Management System Build Script   ║${NC}"
echo -e "${BLUE}║              Version ${VERSION}                 ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════════╝${NC}"
echo ""

# Clean previous builds
echo -e "${YELLOW}→ Cleaning previous builds...${NC}"
rm -rf ${BUILD_DIR} ${DIST_DIR} target
mkdir -p ${RELEASE_DIR}

# Check for Maven
if command -v mvn &> /dev/null; then
    echo -e "${GREEN}✓ Maven found${NC}"
    USE_MAVEN=true
else
    echo -e "${YELLOW}⚠ Maven not found, will use manual compilation${NC}"
    USE_MAVEN=false
fi

# Check for Java
if command -v javac &> /dev/null; then
    JAVA_VERSION=$(javac -version 2>&1 | awk '{print $2}')
    echo -e "${GREEN}✓ Java compiler found (version ${JAVA_VERSION})${NC}"
else
    echo -e "${RED}✗ Java compiler not found!${NC}"
    echo -e "${RED}  Please install JDK 11 or higher${NC}"
    exit 1
fi

echo ""
echo -e "${BLUE}Building application...${NC}"

# Build with Maven or manually
if [ "$USE_MAVEN" = true ]; then
    echo -e "${YELLOW}→ Building with Maven...${NC}"
    mvn clean package -q

    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Maven build successful${NC}"
        cp target/payroll-system.jar ${RELEASE_DIR}/
    else
        echo -e "${RED}✗ Maven build failed${NC}"
        exit 1
    fi
else
    echo -e "${YELLOW}→ Manual compilation...${NC}"

    # Create lib directory and download SQLite JDBC
    mkdir -p lib

    if [ ! -f "lib/sqlite-jdbc-3.44.1.0.jar" ]; then
        echo -e "${YELLOW}→ Downloading SQLite JDBC driver...${NC}"
        curl -L -o lib/sqlite-jdbc-3.44.1.0.jar \
            https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.44.1.0/sqlite-jdbc-3.44.1.0.jar
        echo -e "${GREEN}✓ Download complete${NC}"
    fi

    # Compile
    echo -e "${YELLOW}→ Compiling Java files...${NC}"
    javac -cp ".:lib/*" *.java src/*.java

    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✓ Compilation successful${NC}"

        # Create JAR
        echo -e "${YELLOW}→ Creating JAR file...${NC}"
        mkdir -p ${BUILD_DIR}/classes
        cp *.class ${BUILD_DIR}/classes/
        cp -r src ${BUILD_DIR}/classes/

        # Extract SQLite JDBC classes
        cd ${BUILD_DIR}/classes
        jar xf ../../lib/sqlite-jdbc-3.44.1.0.jar
        rm -rf META-INF

        # Create manifest
        cat > MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: PayrollGUI
Created-By: Payroll Management System Build Script
Implementation-Version: ${VERSION}
EOF

        # Package JAR
        jar cfm payroll-system.jar MANIFEST.MF .
        mv payroll-system.jar ../../${RELEASE_DIR}/
        cd ../..

        echo -e "${GREEN}✓ JAR created successfully${NC}"
    else
        echo -e "${RED}✗ Compilation failed${NC}"
        exit 1
    fi
fi

# Copy documentation
echo ""
echo -e "${YELLOW}→ Copying documentation...${NC}"
cp README.md ${RELEASE_DIR}/
cp pom.xml ${RELEASE_DIR}/

# Create run scripts
echo -e "${YELLOW}→ Creating run scripts...${NC}"

# Unix/Linux/Mac run script
cat > ${RELEASE_DIR}/run.sh << 'EOF'
#!/bin/bash
echo "Starting Payroll Management System..."
java -jar payroll-system.jar
EOF
chmod +x ${RELEASE_DIR}/run.sh

# Windows batch file
cat > ${RELEASE_DIR}/run.bat << 'EOF'
@echo off
echo Starting Payroll Management System...
java -jar payroll-system.jar
pause
EOF

echo -e "${GREEN}✓ Run scripts created${NC}"

# Copy source files
echo -e "${YELLOW}→ Copying source files...${NC}"
mkdir -p ${RELEASE_DIR}/source
cp Employee.java ${RELEASE_DIR}/source/
cp DataIO.java ${RELEASE_DIR}/source/
cp PayrollGUI.java ${RELEASE_DIR}/source/
cp -r src ${RELEASE_DIR}/source/

echo -e "${GREEN}✓ Source files copied${NC}"

# Create installation instructions
cat > ${RELEASE_DIR}/INSTALL.txt << 'EOF'
================================================================================
PAYROLL MANAGEMENT SYSTEM - INSTALLATION INSTRUCTIONS
================================================================================

REQUIREMENTS:
- Java Runtime Environment (JRE) 11 or higher

INSTALLATION:
1. Ensure Java is installed on your system
   - Test by running: java -version
   - If not installed, download from: https://www.oracle.com/java/

2. Extract this ZIP file to any directory

3. Run the application:

   On Windows:
   - Double-click run.bat
   OR
   - Open Command Prompt in this folder
   - Type: java -jar payroll-system.jar

   On Mac/Linux:
   - Double-click run.sh (may need to right-click -> Open)
   OR
   - Open Terminal in this folder
   - Type: ./run.sh
   OR
   - Type: java -jar payroll-system.jar

4. The application will create a database file (database.db) on first run

FEATURES:
- Add, edit, and delete employee records
- Calculate commission-based payroll
- Track daily and weekly sales
- SQLite database for data persistence

For more information, see README.md

SUPPORT:
Email: emmanueldiaz24@gmail.com
GitHub: https://github.com/Ediazpy/Payroll-Management-System

================================================================================
EOF

# Create quick start guide
cat > ${RELEASE_DIR}/QUICKSTART.txt << 'EOF'
================================================================================
PAYROLL MANAGEMENT SYSTEM - QUICK START GUIDE
================================================================================

RUNNING THE APPLICATION:
------------------------
Windows: Double-click run.bat
Mac/Linux: Double-click run.sh or run: ./run.sh

FIRST TIME SETUP:
-----------------
No setup required! The application creates its database automatically.

BASIC USAGE:
------------

1. INFORMATION TAB - Calculate Job Earnings:
   a) Enter Sales amount (total sale value)
   b) Enter Parts cost (parts used)
   c) Enter Tips received
   d) Click "Calculate Job"
   e) Select a day of week and click "Load Total" to track it
   f) Click "Calculate Week" to see weekly total

2. EMPLOYEE LIST TAB - Manage Employees:
   a) Click "Add Employee" to create new employee
   b) Select an employee and click "Edit Employee" to modify
   c) Select an employee and click "Delete Employee" to remove
   d) Click "Refresh" to reload data from database

PAYROLL FORMULA:
----------------
Weekly Pay = ((Sales - Parts) / 2) + Tips

This represents a 50% commission on (Sales minus Parts) plus tips.

TIPS:
-----
- Employee IDs must be unique
- All money values must be non-negative
- Database file (database.db) is created in the application folder
- Back up database.db file regularly to preserve data

For detailed documentation, see README.md

================================================================================
EOF

# Create VERSION file
cat > ${RELEASE_DIR}/VERSION.txt << EOF
Payroll Management System
Version: ${VERSION}
Build Date: $(date)
Built with: $(if [ "$USE_MAVEN" = true ]; then echo "Maven"; else echo "Manual compilation"; fi)

Changelog:
- Full CRUD operations for employee management
- Commission-based payroll calculations
- Weekly sales tracking by day
- SQLite database integration
- Input validation and error handling
- Professional GUI with dual-tab interface
EOF

# Create package info
cat > ${RELEASE_DIR}/PACKAGE_CONTENTS.txt << 'EOF'
================================================================================
PACKAGE CONTENTS
================================================================================

payroll-system.jar      - Main application (executable JAR file)
README.md              - Full documentation and user guide
INSTALL.txt            - Installation instructions
QUICKSTART.txt         - Quick start guide
VERSION.txt            - Version and build information
PACKAGE_CONTENTS.txt   - This file
pom.xml                - Maven build configuration

run.sh                 - Unix/Linux/Mac run script
run.bat                - Windows run script

source/                - Directory containing source code
  Employee.java        - Employee data model
  DataIO.java          - Database operations
  PayrollGUI.java      - Main GUI application
  src/                 - Additional source files

NOTES:
- The database file (database.db) will be created on first run
- All dependencies are included in the JAR file
- No additional installation required beyond Java Runtime

================================================================================
EOF

# Create ZIP archive
echo ""
echo -e "${YELLOW}→ Creating distributable archive...${NC}"

cd ${DIST_DIR}
ZIP_NAME="${APP_NAME}-${VERSION}.zip"
zip -r ${ZIP_NAME} ${APP_NAME}-${VERSION}/ > /dev/null 2>&1

if [ $? -eq 0 ]; then
    ZIP_SIZE=$(du -h ${ZIP_NAME} | cut -f1)
    echo -e "${GREEN}✓ Archive created: ${ZIP_NAME} (${ZIP_SIZE})${NC}"
else
    echo -e "${RED}✗ Failed to create archive${NC}"
    exit 1
fi

cd ..

# Create checksum
echo -e "${YELLOW}→ Generating checksums...${NC}"
cd ${DIST_DIR}
sha256sum ${ZIP_NAME} > ${ZIP_NAME}.sha256
echo -e "${GREEN}✓ SHA256: $(cat ${ZIP_NAME}.sha256)${NC}"
cd ..

# Summary
echo ""
echo -e "${GREEN}╔════════════════════════════════════════════╗${NC}"
echo -e "${GREEN}║          BUILD SUCCESSFUL! ✓               ║${NC}"
echo -e "${GREEN}╚════════════════════════════════════════════╝${NC}"
echo ""
echo -e "${BLUE}Distribution package created:${NC}"
echo -e "  📦 ${DIST_DIR}/${ZIP_NAME}"
echo -e "  📄 ${DIST_DIR}/${ZIP_NAME}.sha256"
echo ""
echo -e "${BLUE}Contents:${NC}"
echo -e "  • Executable JAR file"
echo -e "  • Complete documentation"
echo -e "  • Run scripts for all platforms"
echo -e "  • Source code"
echo ""
echo -e "${YELLOW}To test the application:${NC}"
echo -e "  cd ${RELEASE_DIR}"
echo -e "  java -jar payroll-system.jar"
echo ""
echo -e "${GREEN}Release is ready for distribution!${NC}"
echo ""

exit 0
