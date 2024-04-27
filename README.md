## SkillSprint Backend

Welcome to the SkillSprint Backend Repository! This repository contains the backend services for our educational platform, SkillSprint, designed to provide a seamless learning experience for both learners and instructors.

### Technologies Used:
- **Framework**: Spring Boot
- **Service Discovery**: Edureka Service Discovery
- **API Gateway**: Spring Cloud Gateway
- **Database**: MongoDB
- **Authentication**: JSON Web Tokens (JWT)
- **Containerization**: Docker
- **Orchestration**: Kubernetes

### Prerequisites:
- Docker installed on your machine
- Kubernetes cluster set up (if deploying on Kubernetes)
- Java Development Kit (JDK) installed
- MongoDB installed and running

### Setup Instructions:
1. **Clone the Repository:**
   ```bash
   git clone [repository_url]
   cd skillsprint-backend
   ```

2. **Environment Configuration:**
   - Copy the `.env.example` file to `.env` and fill in the necessary environment variables, such as database connection details, API keys for third-party services, and JWT secret.

3. **Build and Run Services:**
   - Build each service using Maven and run them individually:
     ```bash
     cd authentication-service
     mvn spring-boot:run
     
     cd ../course-service
     mvn spring-boot:run
     
     # Repeat for other services
     ```

4. **Deployment on Kubernetes (Optional):**
   - If deploying on Kubernetes, apply the Kubernetes manifests provided in the `kubernetes/` directory:
     ```bash
     kubectl apply -f kubernetes/
     ```

### API Documentation:
- Detailed API documentation for each service can be found in the respective service directories or at [API Documentation URL].

### Security:
- Authentication is implemented using JWT tokens. Users must include their JWT token in the Authorization header for protected routes.

### Scalability and Performance:
- The backend services are designed with scalability and performance in mind, utilizing Microservices architecture and containerization with Docker and Kubernetes.

### Additional Functionalities:
- **Email and SMS Notifications:** Upon successful enrollment, learners receive confirmation via email and SMS using third-party services for sending notifications.

### Contributors:
- [List of contributors]
