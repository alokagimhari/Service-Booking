# 🛠️ Service Booking System

## 📌 Overview  
The **Service Booking System** is a full-stack web application that allows users to book and manage service appointments efficiently. It provides a seamless experience for both customers and service providers with role-based authentication.

## 🚀 Features  
✅ **User Authentication & Authorization** (Spring Security & JWT)  
✅ **Service Listings** – Browse available services & providers  
✅ **Booking Management** – Schedule, modify, and cancel bookings  
✅ **Admin Dashboard** – Manage services, bookings, and user roles  

## 🏗️ Tech Stack  
**Frontend:** Angular  
**Backend:** Spring Boot  
**Database:** SQL Server  
**Authentication:** Spring Security, JWT  

---

## ⚙️ Installation Guide  

### 1️⃣ Prerequisites  
Ensure you have the following installed:  
- **Java 17+**  
- **Node.js & npm**  
- **Angular CLI**  
- **SQL Server**  

### 2️⃣ Backend Setup (Spring Boot)  
```bash
# Clone the repository  
git clone https://github.com/yourusername/service-booking-system.git  
cd service-booking-system/backend  

# Configure the database in application.properties  
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=service_booking  
spring.datasource.username=your_db_user  
spring.datasource.password=your_db_password  

# Build and run the backend  
mvn clean install  
mvn spring-boot:run

3️⃣ Frontend Setup (Angular)
cd ../frontend  

# Install dependencies  
npm install  

# Run the development server  
ng serve --open  

🔗 API Endpoints
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	User login
GET	/api/services	Fetch all services
POST	/api/bookings	Create a new booking

🎯 Usage
Open http://localhost:4200 in your browser
Register/Login to access the platform
Browse services and book appointments
Admins can manage services & users

🤝 Contributing
Contributions are welcome! Fork the repo and submit a pull request.

📌 Developed with ❤️ using Spring Boot & Angular




