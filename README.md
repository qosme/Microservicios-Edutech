# Edutech

Backend de sitio web de gestión escolar Edutech, microservicios Java Spring Boot con OpenFeign.

## Arquitectura

| Service | Port | Description |
|---------|------|-------------|
| api-usuario | 8080 | Usuarios e Instructores |
| api-educacion | 8083 | Cursos, Contenido, Evaluaciones |
| api-administracion | 8087 | Roles, Permisos, Reportes |
| api-gateway | 8000 | API Gateway (rutas a los microservicios) |

## Prerrequisitos

- Java 17+
- Maven 3.6+
- MySQL 8+ (localhost:3306)

## Base de Datos

El servicio crea automáticamente las bases de datos:

- `bd-usuarios` (api-usuario)
- `bd_educacion` (api-educacion)
- `bd-administracion` (api-administracion)

## Ejecución

**Importante:** Cada microservicio corre de forma independiente. Todos deben correr para que el sistema completo funcione.

Inicializar **manualmente** en 4 terminales diferentes:
1. `mvn -pl api-usuario spring-boot:run`
2. `mvn -pl api-educacion spring-boot:run`
3. `mvn -pl api-administracion spring-boot:run`
4. `mvn -pl api-gateway spring-boot:run`

Esperar ~30 segundos y verificar:
- http://localhost:8080/usuarios/todos
- http://localhost:8181/cursos/todos
- http://localhost:8087/roles

## Rutas por Gateway

- `http://localhost:8000/usuarios/**` → api-usuario:8080
- `http://localhost:8000/instructores/**` → api-usuario:8080
- `http://localhost:8000/cursos/**` → api-educacion:8181
- `http://localhost:8000/evaluaciones/**` → api-educacion:8181
- `http://localhost:8000/contenidos/**` → api-educacion:8181
- `http://localhost:8000/admin/**` → api-administracion:8087

## Comunicación por OpenFeign

- **api-usuario** → api-educacion: evaluaciones por usuario
- **api-educacion** → api-usuario: usuarios e instructores por curso
