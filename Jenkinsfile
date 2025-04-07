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
                    writeFile file: 'appendTestMethod.ps1', text: '''
        $filePath = "src\main\java\com\example\demoAllTouhou\controllers\CharacterController.java"

        if (Test-Path $filePath) {
            $content = Get-Content $filePath -Raw

            if ($content -notmatch '@GetMapping\("/test"\)') {

                $method = @"
            @GetMapping("/test")
            public ResponseEntity<Void> test() {
                return ResponseEntity.ok().build();
            }

        "@

                $content = $content -replace "(?s)(^.*)(\n\})", "`$1" + $method + "`$2"
                Set-Content -Path $filePath -Value $content
                Write-Output "Método añadido exitosamente."
            } else {
                Write-Output "El método ya existe. No se hace nada."
            }
        } else {
            Write-Error "No se encontró el archivo: $filePath"
        }

                    '''

                    bat 'powershell -ExecutionPolicy Bypass -File appendTestMethod.ps1'
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