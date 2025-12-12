# 📦 Sistema de Control de Almacén (SCA) - API

Una API RESTful robusta y escalable para la gestión de inventarios,
desarrollada con **Java 17** y **Spring Boot 3**. Este proyecto
implementa una arquitectura profesional enfocada en la seguridad, la
integridad de datos y las buenas prácticas de ingeniería de software.

## 🚀 Características Principales

-   **Gestión de Productos:** CRUD completo con validaciones de negocio.
-   **Control de Movimientos:** Registro transaccional de Entradas y
    Salidas de stock.
-   **Evidencias Visuales:** Subida de imágenes (comprobantes/daños)
    asociadas a cada movimiento (Almacenamiento Local).
-   **Seguridad Avanzada:** Autenticación Stateless con **JWT** (JSON
    Web Tokens).
-   **RBAC (Role-Based Access Control):** Sistema de permisos jerárquico
    (`SUPER_ADMIN`, `ADMIN`, `INVENTORY_MANAGER`).
-   **Documentación Viva:** Interfaz interactiva con **Swagger UI /
    OpenAPI**.

## 🛠️ Tech Stack

-   **Lenguaje:** Java 17
-   **Framework:** Spring Boot 3.5.7
-   **Base de Datos:** MySQL 8.0 (Ejecutado en Docker)
-   **Seguridad:** Spring Security 6 + JWT (jjwt)
-   **Testing:** JUnit 5 + Mockito
-   **Herramientas:** Maven, Lombok, Docker Compose

## 📋 Prerrequisitos

Asegúrate de tener instalado en tu entorno local: 
1. **Java 17 SDK**
2. **Docker & Docker Compose** (Para la base de datos) 3. **Maven**
(Opcional, el proyecto incluye el wrapper `./mvnw`)

------------------------------------------------------------------------

## ⚙️ Configuración del Entorno (¡Obligatorio!)

Este proyecto utiliza **Perfiles de Spring** para seguridad. Las
credenciales sensibles NO se suben al repositorio. Para ejecutar la app
en tu máquina, debes crear la configuración local.

1.  Crea un archivo nuevo en:
    `src/main/resources/application-dev.properties`
2.  Pega el siguiente contenido dentro de ese archivo:

``` properties
# === Configuración de Base de Datos (Docker) ===
spring.datasource.url=jdbc:mysql://localhost:3307/sca_db
spring.datasource.username=root
spring.datasource.password=root

# === Seguridad JWT ===
# Clave secreta para firmar los tokens (HMAC-SHA256)
application.security.jwt.secret-key=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970

# === Almacenamiento de Archivos ===
# Carpeta donde se guardarán las imágenes (Ruta relativa a la raíz del proyecto)
application.storage.upload-dir=uploads
```

> **Nota:** El archivo `application.properties` principal ya tiene
> configurado `spring.profiles.active=dev`, por lo que cargará
> automáticamente esta configuración al arrancar.

------------------------------------------------------------------------

## ⚡ Instalación y Ejecución

1.  **Clonar el repositorio:**

    ``` bash
    git clone https://github.com/the-indigo0218/spring-inventory-api.git
    cd spring-inventory-api
    ```

2.  **Levantar la Base de Datos:** Asegúrate de que Docker Desktop esté
    corriendo y ejecuta:

    ``` bash
    docker-compose up -d
    ```

    *Esto iniciará un contenedor MySQL expuesto en el puerto `3307`.*

3.  **Ejecutar la Aplicación:** Usa el wrapper de Maven incluido:

    ``` bash
    ./mvnw spring-boot:run
    ```

## 📖 Documentación de la API (Swagger)

Una vez que la aplicación arranque (verás "Started
SpringInventoryApiApplication"), abre tu navegador para ver la
documentación interactiva y probar los endpoints:

👉 **http://localhost:8080/swagger-ui/index.html**

## 🔐 Credenciales Iniciales (Database Seeder)

El sistema incluye un **Seeder** inteligente. Si la base de datos está
vacía al iniciar, creará automáticamente un usuario administrador:

-   **Username:** `root`
-   **Password:** `root`
-   **Role:** `SUPER_ADMIN`

*Usa estas credenciales en el endpoint `/api/v1/auth/login` para obtener
tu primer Token Bearer.*

## 🧪 Ejecutar Tests

Para verificar que la lógica de negocio (inventario, cálculos de stock)
funciona correctamente:

``` bash
./mvnw test
```

## 📂 Estructura del Proyecto

El código sigue una **Arquitectura en Capas** limpia para facilitar la
escalabilidad:

-   `controller`: Endpoints REST y manejo de peticiones HTTP.
-   `service`: Lógica de negocio pura y validaciones.
-   `repository`: Acceso a datos (Spring Data JPA).
-   `domain`: Entidades del modelo de datos (JPA).
-   `security`: Configuración de filtros JWT y `SecurityFilterChain`.
-   `exception`: Manejo global de errores y excepciones personalizadas.

------------------------------------------------------------------------

Desarrollado con ☕ y [Miguel González](www.linkedin.com/in/miguel-gonzalez-p-dev).
