

$filePath = "src\main\java\com\example\demoAllTouhou\controllers\CharacterController.java"

if (Test-Path $filePath) {
    $content = Get-Content $filePath -Raw

    if ($content -notmatch '@GetMapping\("/random"\)') {

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
        $pattern = "(?s)(.*)(\n\})"
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
                    