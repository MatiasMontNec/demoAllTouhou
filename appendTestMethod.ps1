

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
                    