# API de Autenticación con JWT en Java

Esta API proporciona un sistema de autenticación basado en tokens JWT, implementado con Spring Boot, Spring Security y PostgreSQL. Incluye validaciones con Hibernate Validator, gestión de usuarios y control de acceso seguro. Ideal como base para aplicaciones modernas con seguridad robusta.

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (Java JSON Web Token)
- PostgreSQL
- Hibernate Validator
- Lombok

## Requisitos

- JDK 17 o superior
- Maven
- PostgreSQL

## Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Pixaica1986/api-pixaica-security.git
   cd api-pixaica-security
   ```

2. Configura tu conexión a la base de datos PostgreSQL en el archivo:

   ```
   src/main/resources/application.properties
   ```

   Ejemplo:

   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/mi_basedatos
   spring.datasource.username=mi_usuario
   spring.datasource.password=mi_contraseña
   ```

3. Ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

---

## Endpoints principales

| Método | Endpoint                  | Descripción                        |
|--------|---------------------------|------------------------------------|
| POST   | `/api/auth/register`      | Registrar nuevo usuario            |
| POST   | `/api/auth/login`         | Autenticar y obtener token JWT     |
| GET    | `/api/auth/check-auth`    | Verificar si el token es válido    |

---

## Ejemplos de uso

### 🔐 Registro de usuario

```bash
curl --location 'http://localhost:8080/api/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username":"juanxyz",
    "password":"123=abc",
}'
```

### 🔑 Inicio de sesión (Login)

```bash
curl --location 'http://localhost:8080/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username":"juanxyz",
    "password":"123=abc"
}'
```

> 📌 Este endpoint devuelve un token JWT que debe ser usado para acceder a recursos protegidos.

### ✅ Verificación de autenticación (Check Auth)

```bash
curl --location 'http://localhost:8080/api/auth/check-auth' \
--header 'Authorization: Bearer <tu_token_jwt>'
```

> 🔒 Este endpoint está protegido. Asegúrate de incluir el token JWT en el header `Authorization` con el prefijo `Bearer`.

---
## 🐳 Uso con Docker

### Construir la imagen

```bash
mvn clean package
docker build -t api-pixaica-security .
```

### Ejecutar contenedor

```bash
docker run -p 8080:8080 api-pixaica-security
```

