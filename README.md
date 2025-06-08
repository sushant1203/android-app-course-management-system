# 🎓 University Course Management System (Android)

## 📝 Project Overview

This is a fully-featured Android application designed to provide university lecturers with a powerful and intuitive tool for managing their courses and student enrolments. The app streamlines administrative tasks by allowing lecturers to dynamically create courses, manage student records, and view detailed information all from their mobile device.

Built with a focus on a clean user interface and robust data management, this project serves as a practical demonstration of modern Android development principles, including `Activity` lifecycle management, `RecyclerView` for efficient data display, and persistent local data storage.

***
## ✨ Key Features

* **Course Creation & Management:** Lecturers can dynamically create new courses by providing a course code, name, and their own name. All existing courses are displayed in a clean, scrollable list on the main screen.
* **Detailed Course View:** Tapping a course reveals a detailed view showing all enrolled students, allowing lecturers to see their roster at a glance.
* **Full Student CRUD Functionality:** The app supports complete Create, Read, Update, and Delete (CRUD) operations for students within a course.
    * **Add Students:** Easily add new students to a course with validation to prevent duplicate enrolments based on matriculation number.
    * **Edit Student Details:** Update a student's name, email, or matriculation number through a simple dialogue-triggered edit screen.
    * **Remove Students:** Remove a student from a specific course without deleting their master record from the system.
* **Intuitive Navigation & UI:** The application uses `FloatingActionButton`s for primary actions (creating courses, adding students) and `RecyclerView`s for efficient, smooth scrolling of long lists. Actions like deleting a course are handled via an intuitive long-press gesture.
* **Data Persistence:** All course and student data is saved to a local database, ensuring that information is retained between app launches.

***
## 🛠️ Technologies & Architecture

* **Language:** `[e.g., Kotlin or Java]`
* **Platform:** Android Studio
* **UI/Layout:** XML, Material Design Components
* **Data Display:** `RecyclerView` for displaying dynamic lists of courses and students.
* **Database:** `MySQL`

---

## 🧠 Technical Skills & Concepts Demonstrated

* **Android Fundamentals:** Proficient use of `Activities`, `Intents`, and the `Activity` lifecycle.
* **UI/UX Design:** Implementation of a user-friendly interface using `RecyclerView`, `FloatingActionButton`, `Dialogues`, and `Toast` messages for user feedback.
* **Data Management:** Design and implementation of a local database schema to manage complex relationships (many-to-many between students and courses).
* **CRUD Operations:** Building the logic for creating, reading, updating, and deleting data within a mobile application context.
* **Event Handling:** Implementation of click listeners, long-press listeners, and other user interaction events.
* **Data Validation:** Logic to prevent the creation of duplicate records within the database.

---
