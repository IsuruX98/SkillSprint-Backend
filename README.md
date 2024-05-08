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
- [Docker](https://docs.docker.com/get-docker/) installed on your machine
- [Kubernetes](https://minikube.sigs.k8s.io/docs/start/) installed and configured, such as Minikube
- [Kube Forward app](https://github.com/pixel-point/kube-forwarder) installed (for port forwarding)
- Java Development Kit (JDK) installed

## Getting Started

1. Clone the project repository:

```bash
git clone https://github.com/IsuruX98/SkillSprint-Backend.git
```

2. Navigate to the `yml` folder:

```bash
cd yml
```

3. Apply Kubernetes configurations for the services:

```bash
kubectl apply -f .
```

4. Check the status of the deployed services:

```bash
kubectl get all
```
OR
Use Minikube dashboard to monitor the status visually.

5. Port Forwarding

   a. For Eureka Service Registry:
   ```bash
   kubectl port-forward service/eureka-lb 8761:80 -n default
   ```

   b. For Cloud Gateway:
   ```bash
   kubectl port-forward service/cloud-gateway-svc 9191:80 -n default
   ```
OR
Use Kube Forward app, select the Minikube cluster, and add the following port forwarding configurations:
   - Resource Namespace: default
   - Kind: Service
   - Name: eureka-lb
   - Local Port: 8761
   - Resource Port: 80

   Repeat the same steps for cloud-gateway-svc:
   - Name: cloud-gateway-svc
   - Local Port: 9191
   - Resource Port: 80

7. Access the services:
   - Eureka Service Registry: http://localhost:8761/
   - Cloud Gateway: http://localhost:9191/

### API Documentation:
- Detailed API documentation for each service can be found in the respective service directories or at [API Documentation URL].

### Security:
- Authentication is implemented using JWT tokens. Users must include their JWT token in the Authorization header for protected routes.

### Scalability and Performance:
- The backend services are designed with scalability and performance in mind, utilizing Microservices architecture and containerization with Docker and Kubernetes.

### Additional Functionalities:
- **Email and SMS Notifications:** Upon successful enrollment, learners receive confirmation via email and SMS using third-party services for sending notifications.

### Contributors:
- Isuru Madusanka
- Yasiru Deshan
- Chamith kavinda
- Sehan Deemantha
