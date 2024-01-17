# Bootcamp Backend Java Sprint Nº 1 - Spring

## Descripción
Implementación de una API REST en Java con Spring para gestionar datos según los requisitos especificados en el enunciado del Bootcamp Backend Java Sprint Nº 1 de Mercado Libre.

## Definiciones de Equipo
La API se ejecuta localmente utilizando Spring Boot. Para las pruebas, se puede acceder a la colección de Postman alojada en la carpeta src -> main -> resources -> postman.

## Instrucciones de Ejecución:
```bash
### Clonar el repositorio
	 git clone https://github.com/Yoiber017/be_java_hisp_w24_g05.git

### Acceder al directorio del proyecto
	 cd be_java_hisp_w24_g05

### Ejecutar la aplicación
	 ./mvnw spring-boot:run


### Endpoints

- **GET /users/{userId}/follow/{userIdToFollow}**
  - Responsable: @storresarias

- **GET /users/{userId}/followers/count**
  - Responsable: @ImanolSuppoMELI

- **GET /users/{userId}/followers/list**
  - Responsable: @jzapatamacias

- **GET /users/{userId}/followed/list**
  - Responsable: @Yoiber017
  
- **POST /products/post**
  - Responsable: @lgordillomal

- **GET /products/followed/{userId}/list**
  - Responsable: @JuanIgnacioZunino
  
- **GET /users/{userId}/unfollow/{userIdToUnfollow}**
  - Responsable: @storresarias
  
- **GET /users/{UserID}/followers/list?order=name_asc**
  - Responsable: @storresarias
  
- **GET /users/{UserID}/followers/list?order=name_desc**
  - Responsable: @storresarias
  
- **GET /users/{UserID}/followed/list?order=name_asc**
  - Responsable: @Yoiber017
  
- **GET /users/{UserID}/followed/list?order=name_desc**
  - Responsable: @Yoiber017
  
- **GET /products/followed/{userId}/list?order=date_asc**
  - Responsable: @JuanIgnacioZunino
  
- **GET /products/followed/{userId}/list?order=date_desc**
  - Responsable: @JuanIgnacioZunino

### Integrantes
#### Developers:
- @ImanolSuppoMELI - Imanol Suppo Alaniz
- @JuanIgnacioZunino - Juan Ignacio Zunino
- @jzapatamacias - Juan Camilo Zapata Macias
- @lgordillomal - Laura Ximena Gordillo Maldonado
- @storresarias - Santiago Torres Arias
- @Yoiber017 - Yoiber Andres Beitar Renteria

#### Scrum Master:
- @extjotabell - Johanna Tabella

### Agradecimientos

Agradecemos la oportunidad de participar en este proyecto y la invaluable ayuda de nuestros compañeros y tutores. ¡Esperamos que esta API cumpla con las expectativas!


