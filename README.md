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
1. Run using JAR file (Quickest way):
   Make sure Java (JDK 8 or above) is installed
   Download the StudentApp.jar file
   Open terminal / command prompt in that folder
   Run:
      java -jar StudentApp.jar
   
2. Run using Source Code:
   Clone the repository:
      git clone <repo-url>
      Navigate to the project folder
      Compile all Java files:
         javac *.java
      Run the main class:
         java StudentDetails
   
## Notes:
- Make sure Java is properly installed and added to PATH
- The project uses basic file storage, so data will be saved locally

## Future Imporvements:
- Add database support (MySQL / SQLite)
- Improve UI design
- Add search and filter features
- Export reports (PDF/Excel)
