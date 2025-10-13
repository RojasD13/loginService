# Changelog

Todos los cambios notables en este proyecto serán documentados en este archivo.  
El formato sigue las recomendaciones de [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/)  
y [Semantic Versioning](https://semver.org/lang/es/).

---

## [Unreleased]
### Agregado
- Inicialización del proyecto `loginService` con estructura básica de Spring Boot.
- Configuración de `.env.properties` y archivo dummy para CI/CD.
- Flujo de integración continua (`flow.yml`) para build automatizado.

---

## [0.1.0] - 2025-10-13
### Creado
- Clase principal `Main.java` con carga de propiedades.
- Configuración base de Maven (`pom.xml`).
- `.gitignore`, `.gitattributes` y `README.md` iniciales.

### Nuevas características
- Configuración inicial del proyecto **Spring Boot**.
- Clase principal `Main.java` con anotación `@SpringBootApplication`.
- Carga de propiedades desde `secrets/.env.properties` mediante `@PropertySource`.
- Configuración de `Maven` y estructura estándar del módulo `loginService`.

### Estructura establecida
- Paquete base: `com.edu.uptc.loginService`
- Directorios:
  - `src/main/java` → Código fuente principal.
  - `src/main/resources` → Configuración y propiedades.
  - `secrets/` → Variables privadas (`.env.properties`).

### Tests
- Se eliminan los tests automáticos de CI temporalmente.
- Flujo de CI ajustado para realizar **solo el build** con Maven (sin ejecutar tests).

---

## Próximos pasos
- Configurar ejecución de tests una vez existan componentes funcionales.
- Añadir integración continua para despliegue en entorno `dev`.
- Incluir documentación del API REST (Swagger o SpringDoc).