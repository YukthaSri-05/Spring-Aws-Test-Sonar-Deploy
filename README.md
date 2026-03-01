# 🚀 Spring-Aws-Test-Sonar-Deploy

## 📌 Overview

Spring-Aws-Test-Sonar-Deploy is a complete end-to-end CI/CD implementation project demonstrating automated build, testing, static code analysis, and deployment of a Spring Boot application to AWS EC2 using Jenkins and SonarQube.

The pipeline integrates GitHub, Maven, JUnit, SonarQube, and AWS to enforce quality gates and automate deployment using systemd services.

---

## 🏗️ Architecture Workflow

1. Developer pushes code to GitHub  
2. Jenkins Pipeline automatically triggers  
3. Maven builds the project  
4. Unit tests are executed  
5. SonarQube performs static code analysis  
6. Quality Gate validation is checked  
7. JAR file is transferred to AWS EC2 via SSH  
8. Application is restarted using systemd service  

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot  
- Maven  
- JUnit  
- Jenkins (Declarative Pipeline)  
- SonarQube  
- Git & GitHub  
- AWS EC2 (Linux)  
- systemd  
- SSH Publisher Plugin  

---

## ⚙️ CI/CD Pipeline Stages

### 1️⃣ Pull Code
Fetches source code from GitHub repository.

### 2️⃣ Install Dependencies


### 3️⃣ Unit Testing

- Publishes JUnit test reports in Jenkins.

### 4️⃣ SonarQube Analysis

- Performs static code analysis.
- Validates Quality Gate.

### 5️⃣ Build JAR


### 6️⃣ Deploy to AWS EC2
- Transfers JAR file to `/home/ubuntu/app`
- Restarts application using systemd

```bash
sudo systemctl daemon-reload
sudo systemctl restart springapp
sudo systemctl enable springapp
sudo systemctl status springapp
```

---

## 🖥️ Jenkins Declarative Pipeline

```groovy
pipeline {
    agent any

    tools {
        maven 'CseMaven'
    }

    environment {
        SONARQUBE_ENV = 'CSESonar'
    }

    stages {

        stage('Pull Code') {
            steps {
                git url: 'https://github.com/YukthaSri-05/Spring-Aws-Test-Sonar-Deploy.git',
                    branch: 'master'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Unit Testing') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    bat 'mvn clean verify sonar:sonar'
                }
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('DeployToEC2') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(
                        configName: 'ec2-server',
                        transfers: [
                            sshTransfer(
                                sourceFiles: 'target/*.jar',
                                removePrefix: 'target',
                                remoteDirectory: 'app',
                                execCommand: '''
                                    sudo systemctl restart springapp
                                '''
                            )
                        ]
                    )
                ])
            }
        }
    }
}
```

---

## ☁️ AWS systemd Service Configuration

**File:** `/etc/systemd/system/springapp.service`

```ini
[Unit]
Description=Spring Boot Application
After=network.target

[Service]
User=ubuntu
WorkingDirectory=/home/ubuntu/app
ExecStart=/usr/bin/java -jar /home/ubuntu/app/Spring-Aws-Test-Sonar-Deploy-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always
RestartSec=5

[Install]
WantedBy=multi-user.target
```

---

## 📂 Project Structure

```
Spring-Aws-Test-Sonar-Deploy
│── src/main/java/com/example/demo
│   ├── CseController.java
│   ├── SpringAwsTestSonarDeployApplication.java
│
│── src/test/java/com/example/demo
│   ├── CseControllerTest.java
│
│── pom.xml
│── Jenkinsfile
```

---

## 🎯 Key DevOps Concepts Demonstrated

- CI/CD automation
- Static code analysis with Quality Gates
- JUnit test report publishing
- Artifact deployment via SSH
- systemd service management
- Production-ready AWS deployment

---

## ✅ Conclusion

This project demonstrates a real-world DevOps workflow combining development, testing, quality analysis, and cloud deployment using industry-standard tools.
