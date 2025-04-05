pipeline {
    agent any
    tools{
        maven "maven_3_9_9"
        jdk "jdk_17"
        nodejs "nodejs_18_17_1"
    }
    stages {
        stage('Build JAR File'){
            steps{
                checkout scmGit(branches: [[name: 'origin/ramajorge_QA']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MatiasMontNec/demoAllTouhou']])
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