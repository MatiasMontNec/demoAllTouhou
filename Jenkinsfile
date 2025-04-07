pipeline {
    agent any
    tools {
        maven "maven"
    }
    environment {
        GITHUB_TOKEN = '33ff7af5-264a-4e8c-8b5e-f2000831c9cc'
    }
    stages {

        stage('Construir archivo JAR') {
            steps {
                checkout scmGit(branches: [[name: '.*master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MatiasMontNec/demoAllTouhou']])
                script {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Test unitarios') {
            steps {
                script {
                    bat 'mvn test'
                }
            }
        }

        stage('Construir Frontend') {
            steps {
                script {
                    bat 'cd Frontend && npm install && npm run build'
                }
            }
        }

        stage('Mandando cambios al repositorio') {
            steps {
                script {
                    bat '''
                    git config user.email "patricio.paez@usach.com"
                    git config user.name "niptuS"
                    git add .
                    git commit -m "Añadido CharacterController"
                    git remote set-url origin https://$GITHUB_TOKEN@github.com/MatiasMontNec/demoAllTouhou.git
                    git push origin master
                    '''
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