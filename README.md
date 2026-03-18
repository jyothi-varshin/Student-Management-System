# Student Management System

## Project Overview
A desktop-based Java Swing application designed to efficiently manage student records in a simple and organized way.
It allows handling of admission details, marks, and attendance -  all in one place through an easy-to-use interface.

## Features
- Home screen with modules: Admission Details, Marks Entry, Attendance, and Exit
- Dedicated forms for each module to add or update student information
- Input validation for numeric fields to prevent incorrect data entry
- Attendance module with:
  - Radio buttons for Present/Absent
  - Student selection via class, section, and roll number
- Implemented using Java Swing with GridBagLayout and event handling
- File I/O for persistent storage of student data

## Technologies Used
- Java (Swing, AWT)
- File handling for data storage

## How to Run

### 1. Run using JAR file (Quickest way)

1. Make sure Java (JDK 8 or above) is installed
2. Download the `StudentApp.jar` file
3. Open terminal / command prompt in that folder
4. Run:

```bash
java -jar StudentApp.jar
```

---

### 2. Run using Source Code

1. Clone the repository:

```bash
git clone <repo-url>
```

2. Navigate to the project folder

3. Compile all Java files:

```bash
javac *.java
```

4. Run the main class:

```bash
java StudentDetails
```
## Notes:
- Make sure Java is properly installed and added to PATH
- The project uses basic file storage, so data will be saved locally

## Future Imporvements:
- Add database support (MySQL / SQLite)
- Improve UI design
- Add search and filter features
- Export reports (PDF/Excel)
