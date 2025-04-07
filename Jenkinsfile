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

$filePath = "src\\main\\java\\com\\example\\demoAllTouhou\\controllers\\CharacterController.java"

if (Test-Path $filePath) {
    $content = Get-Content $filePath -Raw

    if ($content -notmatch '@GetMapping\\("/random"\\)') {

        $method = @"
    @GetMapping("/random")
    public ResponseEntity<CharacterEntity> getRandomCharacter() {
        try {
            CharacterEntity character = characterService.getRandomCharacter();
            return ResponseEntity.ok(character);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // O puedes devolver un CharacterEntity vacío
        }
    }

"@

        # Utilizar [regex] para reemplazo con grupos
        $pattern = "(?s)(.*)(\\n\\})"
        $replacement = '${1}' + "`n" + $method + '${2}'
        $content = [regex]::Replace($content, $pattern, $replacement)

        Set-Content -Path $filePath -Value $content
        Write-Output "Método añadido exitosamente."
    } else {
        Write-Output "El método ya existe. No se hace nada."
    }
} else {
    Write-Error "No se encontró el archivo: $filePath"
}
                    '''

                    bat '''powershell -ExecutionPolicy Bypass -File appendTestMethod.ps1'''
                }
            }
        }
        stage('Eliminar archivo de script') {
            steps {
                script {
                    bat 'del appendTestMethod.ps1'
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