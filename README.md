# Event Management System

This project is an Event Management System developed in Java using Swing for the user interface and MySQL for the database. The application allows users to manage events, including creating, editing, and deleting events.

## Project Structure

```
event-management-system
├── src
│   ├── Main.java                # Entry point of the application
│   ├── ui
│   │   ├── LoginFrame.java      # User login interface
│   │   ├── DashboardFrame.java   # Main interface after login
│   │   └── EventForm.java       # Interface for creating and editing events
│   ├── db
│   │   └── DatabaseConnection.java # Manages MySQL database connection
│   ├── models
│   │   └── Event.java           # Represents the event entity
│   └── controllers
│       └── EventController.java  # Handles business logic for events
├── lib                          # External libraries (e.g., JDBC drivers)
├── README.md                   # Project documentation
└── pom.xml                     # Maven configuration file
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   ```

2. **Navigate to the project directory:**
   ```
   cd event-management-system
   ```

3. **Install dependencies:**
   Ensure you have Maven installed. Run the following command to install the required dependencies:
   ```
   mvn install
   ```

4. **Configure Database:**
   Update the `DatabaseConnection.java` file with your MySQL database credentials.

5. **Run the Application:**
   Use the following command to run the application:
   ```
   mvn exec:java -Dexec.mainClass="src.Main"
   ```

## Usage Guidelines

- **Login:** Use the login interface to authenticate.
- **Dashboard:** After logging in, you will be directed to the dashboard where you can manage events.
- **Event Management:** Use the event form to create or edit events.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.# event
