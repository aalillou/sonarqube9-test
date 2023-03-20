pipeline {
    agent any
    stages {
        stage('SCM') {
            steps {
                git branch: 'main', url: 'https://github.com/aalillou/sonarqube9-test.git'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew check'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('start static code analysis pipeline') {
            steps {
                container('jdk17') {
                    script {
                        dir('./persona') {
                            sh 'ls -lat'
                            withSonarQubeEnv(installationName: 'SonarQube') {
                                // This expands the evironment variables SONAR_CONFIG_NAME, SONAR_HOST_URL, SONAR_AUTH_TOKEN that can be used by any script.
                                echo "SONAR_HOST_URL = ${env.SONAR_HOST_URL}"
                                gradle "jacocoTestReport sonar -Dsonar.verbose=true -Dsonar.language=java -Dsonar--scan"
                            }
                        }
                    }
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh './gradlew sonarqube'
                }
            }
        }

    }
}


podTemplate(label: 'pod-jdk17', containers: [
    containerTemplate(name: 'openjdk17', image: 'openjdk:17.0.2-slim', ttyEnabled: true, command: 'cat')
]) {
    node('pod-jdk17') {
        stage('Get a Java version') {
            container('openjdk17') {
                sh 'java --version'
            }
        }
    }
}

pipeline {
    agent any
    stages {
        stage('Java check') {
           agent {
                docker {
                    image 'openjdk:17.0.2-slim'
                    reuseNode true
                }
            }
            steps {
                sh 'java -version'
            }
        }
    }
}

podTemplate(label: 'pod-jdk17', containers: [
    containerTemplate(name: 'gradle-jdk17', image: 'gradle:8.0.2-jdk17', ttyEnabled: true, command: 'cat')
]) {
    node('pod-jdk17') {
        stage('Get a Java and Gradle version') {
            container('gradle-jdk17') {
                sh 'java --version'
                sh 'gradle --version'
            }
        }
        stage('SCM') {
            container('gradle-jdk17') {
                git branch: 'main', url: 'https://github.com/aalillou/sonarqube9-test.git'
            }
        }
        stage('Gradle check') {
            container('gradle-jdk17') {
                sh 'pwd'
                sh 'ls -lat'
                sh './gradlew check'
            }
        }
        stage('Build') {
            container('gradle-jdk17') {
                sh './gradlew clean build'
            }
        }
        stage('SonarQube analysis') {
            container('gradle-jdk17') {
                withSonarQubeEnv('SonarQube') {
                    sh './gradlew sonarqube'
                }
            }
        }

    }
}

podTemplate(label: 'gradlepod-jdk8', containers: [
    containerTemplate(name: 'gradle', image: 'gradle:4.7.0-jdk8-alpine', ttyEnabled: true, command: 'cat')
]) {
    node('gradlepod-jdk8') {
        stage('Get a Gradle version') {
            container('gradle') {
                sh 'gradle --version'
            }
        }
        stage('SCM') {
            container('gradle') {
                git branch: 'main', url: 'https://github.com/aalillou/sonarqube9-test.git'
            }
        }
    }
}