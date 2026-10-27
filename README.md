 🍔 FoodExpress – Food Delivery Application

FoodExpress is a full-stack food delivery web application that allows customers to explore food items, 
search for dishes, add items to their cart, and place orders online. The application also provides an 
admin panel to manage food items and other application data.

The project uses a React.js frontend and a Java Spring Boot backend to provide a structured and secure food ordering experience.

🚀 Features

👤 Customer Features

* User registration and login.
* Secure authentication using JWT.
* Browse available food items.
* Search for food items.
* Add and remove items from the shopping cart.
* Place food orders.
* Online payment integration using Razorpay.
* Access customer-specific functionality.

🛠️ Admin Features

* Admin authentication and authorization.
* Manage food items.
* Add, update, and delete food products.
* Manage food-related application data.
* Access administrative functionality.

🔐 Security

* Spring Security integration.
* JWT-based authentication.
* Role-based access control.
* Protected API endpoints.

☁️ Image Storage

* AWS S3 integration for food images.
* Store image references and retrieve images for display in the frontend.

💳 Payment Integration

* Razorpay integration for online payments.
* Support for the payment workflow.

🛠️ Tech Stack

| Technology          | Purpose                                    |
| ------------------- | ------------------------------------------ |
| Java 21             | Backend programming                        |
| Spring Boot         | REST API development                       |
| Spring Security     | Authentication and authorization           |
| JWT                 | Token-based authentication                 |
| Spring Data MongoDB | Database operations                        |
| MongoDB             | Database                                   |
| React.js            | Frontend development                       |
| Vite                | Frontend development server and build tool |
| Bootstrap           | Responsive UI styling                      |
| AWS S3              | Food image storage                         |
| Razorpay            | Payment integration                        |
| Maven               | Backend build management                   |
| Git & GitHub        | Version control                            |
| Docker              | Containerization and local deployment      |

🏗️ Application Architecture

text
              Customer / Admin
                     |
                     v
              React.js + Vite
                     |
                     v
               REST APIs
                     |
                     v
              Spring Boot
                     |
          +----------+----------+
          |          |          |
          v          v          v
      MongoDB      AWS S3    Razorpay
      Database     Images     Payments


📂 Project Structure

text
FoodExpress/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/foodexpress/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── repository/
│   │       │       ├── entity/
│   │       │       ├── dto/
│   │       │       ├── security/
│   │       │       └── config/
│   │       │
│   │       └── resources/
│   │           └── application.properties
│   │
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── assets/
│   │   └── App.jsx
│   │
│   ├── package.json
│   └── vite.config.js
│
├── docker-compose.yml
└── README.md


Adjust the folder structure to match your actual project.

⚙️ Installation and Setup

Prerequisites

Install the following:

* Java JDK 21
* Maven
* Node.js and npm
* MongoDB
* Git
* Docker (optional)

1. Clone the Repository

bash
git clone YOUR_GITHUB_REPOSITORY_URL
cd FoodExpress


2. Configure the Backend

Navigate to the backend directory:

bash
cd backend


Configure your database connection and other required settings in application.properties.

Example configuration:

properties
spring.application.name=FoodExpress
spring.data.mongodb.uri=${MONGODB_URI}
server.port=8080


Set MONGODB_URI in your environment before starting the application.

Also configure the required JWT secret, AWS S3 credentials, and Razorpay credentials through environment variables or a secure configuration mechanism.

*Important:* Never upload passwords, secret keys, payment credentials, or AWS credentials to GitHub.

3. Start the Backend

bash
mvn spring-boot:run


The backend will start on the configured port.

4. Start the Frontend

Open a new terminal:

bash
cd frontend
npm install
npm run dev


Open the local development URL displayed in the terminal.

5. Run with Docker

If your Docker configuration is available, build and start the application using:

bash
docker compose up --build


Make sure your Docker Compose configuration correctly defines the frontend, backend, and MongoDB services and supplies the required environment variables.

🔌 Main Application Modules

| Module           | Description                                 |
| ---------------- | ------------------------------------------- |
| Authentication   | Customer and admin login and registration   |
| Food Management  | Manage food items and related information   |
| Search           | Search available food items                 |
| Cart             | Manage selected food items                  |
| Orders           | Handle food ordering                        |
| Payments         | Integrate Razorpay payment processing       |
| Image Management | Store and retrieve food images using AWS S3 |
| Admin Dashboard  | Manage food-related application data        |

🔐 Security Considerations

* Protect private API endpoints using Spring Security.
* Validate JWT tokens before granting access to protected resources.
* Enforce role-based access for admin operations.
* Keep AWS and Razorpay credentials outside source control.
* Verify payment status on the backend rather than trusting frontend responses.

🔮 Future Enhancements

* Order tracking and delivery status updates.
* Customer order history.
* Food ratings and reviews.
* Coupons and discount management.
* Email and order notifications.
* Improved admin analytics.
* Automated backend and frontend testing.
* Production deployment using Docker and cloud infrastructure.

🎯 Project Objectives

* Build a full-stack food delivery application.
* Develop REST APIs using Java and Spring Boot.
* Implement secure authentication and authorization.
* Integrate MongoDB for application data storage.
* Explore cloud-based image storage using AWS S3.
* Integrate online payments using Razorpay.
* Learn containerization and application deployment using Docker.

## 👨‍💻 Developer

Lokesh Rajput

Java Backend Developer | Spring Boot | REST APIs | Spring Security | MongoDB | React.js | AWS

---

⭐ If you find this project interesting, consider giving the repository a star!
