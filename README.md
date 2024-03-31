# Password Authentication APIPassword Authentication API

## Overview
This project is an API built in Java using Spring Boot and MySQL, following the MVC (Model-View-Controller) architecture pattern. It provides functionality for user authentication, password management, role assignment, and session management. The API can send email and SMS for user verification and password reset purposes. Additionally, it includes a feature to prevent users from logging in after three consecutive incorrect password attempts. Users are assigned roles, and their session time is calculated, preventing login for one hour after logout.

## Features
- User authentication
- Password management (including forget password and OTP via email and SMS)
- Role assignment
- Session management
- Email and SMS verification
- Password lockout after three consecutive incorrect attempts

## Installation
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Import the required libraries located in the `demo/lib` directory into your IDE.
5. Run the project.

## Dependencies
- Java
- Spring Boot
- MySQL
- Libraries for sending email and SMS (located in `demo/lib`)

## Configuration
2. Ensure the email and SMS service settings (e.g., SMTP server details, API keys) are correctly configured in the application.

## MVC Architecture
This project follows the MVC (Model-View-Controller) architecture pattern:
- **Model**: Represents the data and business logic of the application. It interacts with the database (e.g., user data, roles).
- **View**: As it is only a API it doesn't have any interface
- **Controller**: Acts as an intermediary between the model and view, processing requests from clients, invoking necessary business logic, and returning appropriate responses.

## Usage
1. Use API endpoints for user authentication, password management, role assignment, etc.
2. Utilize email and SMS verification for user registration and password reset.
3. Implement session management to control user access and session duration.
4. Monitor password lockout functionality to prevent unauthorized access.

## Contributing
Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

## License
This project is licensed under the [MIT License](LICENSE).
