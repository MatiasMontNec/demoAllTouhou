pipeline {
    agent any
    tools {
        maven "maven"
    }
    environment {
        GITHUB_TOKEN = '33ff7af5-264a-4e8c-8b5e-f2000831c9cc'
    }
    stages {

         stage('Obtener repositorio') {
             steps {
                 script {
                     bat '''
                     git checkout master
                     git pull origin master
                     '''
                 }
             }
         }
        stage('Agregar funcionalidad de test') {
            steps {
                script {
                    bat '''
                    powershell -Command ^
                    "(Get-Content src\\main\\java\\com\\example\\demoAllTouhou\\controllers\\CharacterController.java) -replace '(^\\s*})$', '    @GetMapping(\"/test\")\\n    public ResponseEntity<Void> test() {\\n        return ResponseEntity.ok().build();\\n    }\\n$1' | Set-Content src\\main\\java\\com\\example\\demoAllTouhou\\controllers\\CharacterController.java"
                    '''
                }
            }
        }

        stage('Mandando cambios al repositorio') {
            steps {
                script {
                    bat '''
                        git checkout master
                        git config user.email "patricio.paez@usach.com"
                        git config user.name "niptuS"
                        git add .
                        git commit -m "Añadido los cambios al repositorio" || echo "Nada que commitear"
                        git push origin master
                    '''
                }
            }
        }

    }
    post {
        failure {
            echo 'Error en pipeline.'
        }
        success {
            echo 'Pipeline completado.'
        }
    }
}