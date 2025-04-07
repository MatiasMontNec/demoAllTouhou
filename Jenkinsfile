pipeline {
    agent any
    tools {
        maven "maven"
    }
    enviroment {
        GITHUB_TOKEN = credentials('${GITHUB_TOKEN}')
    }
    stages {
        stage('Add Method to CharacterController') {
            steps {
                script {
                    bat '''
                    git config user.email "patricio.paez@usach.com"
                    git config user.name "niptuS"
                    git add src/main/java/com/example/demoAllTouhou/controllers/CharacterController.java
                    git commit -m "Add test endpoint to CharacterController"
                    git remote set-url origin https://$GITHUB_TOKEN@github.com/MatiasMontNec/demoAllTouhou.git
                    git push origin master
                    '''
                }
            }
        }

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
                    git config user.email "patricio.paez@usach.com"
                    git config user.name "niptuS"
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