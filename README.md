# Techventory

**Techventory** es un sistema de gestión de inventario desarrollado con Java y Spring Boot. Proporciona una API RESTful robusta para manejar operaciones sobre productos, movimientos, categorías, estados y usuarios, con autenticación segura basada en JWT.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Seguridad y Autenticación](#seguridad-y-autenticación)
- [Guías de Consumo de la API](#guías-de-consumo-de-la-api)
- [Endpoints de la API](#endpoints-de-la-api)
    - [Autenticación](#autenticación)
    - [Categorías](#categorías)
    - [Estados](#estados)
    - [Productos](#productos)
    - [Movimientos](#movimientos)
    - [Movimientos de Productos](#movimientos-de-productos)
    - [Roles](#roles)
- [Sígueme y contacta](#sígueme-y-contacta)

---

## Características

- API RESTful para productos, categorías, movimientos y estados.
- Autenticación y autorización basada en JWT.
- Búsqueda avanzada de productos por nombre, categoría y estado.
- Control detallado de movimientos de inventario.
- Manejo de usuarios y roles personalizados.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- Lombok
- Jakarta Validation
- Maven

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/elAsksito/Techventory.git
   ```

2. Entra al directorio:

   ```bash
   cd Techventory
   ```

3. Compila el proyecto:

   ```bash
   mvn clean install
   ```

4. Ejecuta la app:

   ```bash
   mvn spring-boot:run
   ```

---

## Seguridad y Autenticación

Techventory usa JWT para proteger sus endpoints. El filtro `JwtAuthenticationFilter` valida el token y permite acceso solo a usuarios autenticados.

- Las rutas `/api/auth/**` y Swagger están libres.
- Resto de rutas requieren token JWT en el header:  
  `Authorization: Bearer <token>`

Flujo general:

1. El usuario se registra o inicia sesión.
2. Recibe un token JWT.
3. Este token se usa para acceder a las rutas protegidas.

---

## Guías de Consumo de la API

### Autenticación con JWT

1. **Registrar o iniciar sesión** vía `/api/auth/register` o `/api/auth/login`.
2. Copia el token que devuelve la respuesta.
3. Para acceder a endpoints protegidos, usa el encabezado:  
   `Authorization: Bearer <token>`

### Uso en Postman

- Crea una colección y variable `token`.
- Usa `Bearer {{token}}` como encabezado.
- Autentícate primero y reutiliza el token.

### Uso en Frontend (Angular, React, etc.)

- Guarda el token en `localStorage`.
- Añade `Authorization` en cada solicitud protegida.
- Maneja expiraciones y redirecciones desde el frontend.

### Uso en Flutter

- Usa `SharedPreferences` o `SecureStorage` para guardar el token.
- Agrega el token en los headers de tus peticiones HTTP.
- Puedes automatizar esto con un interceptor (`dio` recomendado).

### Recomendaciones de Seguridad

- Nunca expongas el token en consola o en código fuente.
- Implementa logout limpiando el token.
- Usa guards para proteger rutas en frontend.
- Configura tiempos de expiración adecuados.

---

## Endpoints de la API

> 📌 Asegúrate de incluir el header `Authorization: Bearer <token>` para todos los endpoints protegidos.

### Autenticación

- `POST /api/auth/register` – Registro de usuario.
- `POST /api/auth/login` – Inicio de sesión, retorna JWT.

### Categorías

- `POST /api/categorias` – Crear categoría.
- `GET /api/categorias` – Listar categorías.
- `GET /api/categorias/{id}` – Obtener una categoría.
- `PUT /api/categorias/{id}` – Actualizar una categoría.
- `DELETE /api/categorias/{id}` – Eliminar una categoría.

### Estados

- `POST /api/estados` – Crear estado.
- `GET /api/estados` – Listar estados.
- `GET /api/estados/{id}` – Obtener un estado.
- `PUT /api/estados/{id}` – Actualizar un estado.
- `DELETE /api/estados/{id}` – Eliminar un estado.

### Productos

- `POST /api/productos` – Crear producto.
- `GET /api/productos` – Listar productos.
- `GET /api/productos/{id}` – Obtener producto.
- `GET /api/productos/buscarPorNombre?nombreProducto=X` – Buscar por nombre.
- `GET /api/productos/buscarPorCategoria?categoriaId=X` – Buscar por categoría.
- `GET /api/productos/buscarPorEstado?estadoId=X` – Buscar por estado.
- `PUT /api/productos/{id}` – Actualizar producto.
- `DELETE /api/productos/{id}` – Eliminar producto.

### Movimientos

- `POST /api/movimientos` – Crear movimiento.
- `GET /api/movimientos` – Listar movimientos.
- `GET /api/movimientos/{id}` – Obtener movimiento.
- `PUT /api/movimientos/{id}` – Actualizar movimiento.
- `DELETE /api/movimientos/{id}` – Eliminar movimiento.

### Movimientos de Productos

- `POST /api/movimientos-productos` – Crear movimiento de producto.
- `GET /api/movimientos-productos` – Listar todos.
- `GET /api/movimientos-productos/{id}` – Obtener uno.
- `PUT /api/movimientos-productos/{id}` – Actualizar.
- `DELETE /api/movimientos-productos/{id}` – Eliminar.

### Roles

- `POST /api/roles` – Crear rol.
- `GET /api/roles` – Listar roles.
- `GET /api/roles/{id}` – Obtener rol.
- `PUT /api/roles/{id}` – Actualizar rol.
- `DELETE /api/roles/{id}` – Eliminar rol.

---

## Sígueme y contacta

[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/elAsksito)
[![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=instagram&logoColor=white)](https://www.instagram.com/_ask.dev/)
[![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/r5xgVvqS3B)