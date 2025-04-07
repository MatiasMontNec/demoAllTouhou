pipeline {
    agent any
    tools {
        maven "maven"
    }

            stage('Add Method to CharacterController') {
                steps {
                    script {
                        bat '''
                        echo @GetMapping("/test") >> src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                        echo public ResponseEntity<String> testEndpoint() { >> src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                        echo return ResponseEntity.ok("Test endpoint is working!"); >> src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                        echo } >> src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                        '''
                    }
                }
            }

    stages {
        stage('Build JAR File') {
            steps {
                checkout scmGit(branches: [[name: '.*master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MatiasMontNec/demoAllTouhou']])
                script {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    bat 'mvn test'
                }
            }
        }

        stage('Commit and Push Changes') {
            steps {
                script {
                    bat '''
                    git config user.email "you@example.com"
                    git config user.name "Your Name"
                    git add src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                    git commit -m "Add test endpoint to CharacterController"
                    git push origin master
                    '''
                }
            }
        }

        stage('Build Frontend') {
            steps {
                script {
                    bat 'cd Frontend && npm install && npm run build'
                }
            }
        }

    }
    post {
        failure {
            echo 'Error in pipeline.'
        }
        success {
            echo 'Pipeline completed.'
        }
    }
}