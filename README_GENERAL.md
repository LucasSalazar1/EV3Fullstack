# Sanos y Salvos - Proyecto Completo

Este archivo contiene el proyecto combinado:

- `backend/`: API Spring Boot.
- `frontend/`: proyecto React.

## Backend
Entrar a la carpeta del backend y ejecutar:

```powershell
cd .\Sanos_y_Salvos
& "C:\Users\jtqui\Downloads\apache-maven-3.9.15-bin\apache-maven-3.9.15\bin\mvn.cmd" spring-boot:run
```

La API queda disponible en:

```txt
http://localhost:8081
```

Endpoints principales:

```txt
/api/usuarios
/api/regiones
/api/comunas
/api/mascotas
```

## Frontend
Entrar a la carpeta del frontend y ejecutar:

```bash
npm install
npm run dev
```

El frontend se conectará con la API en:

```txt
http://localhost:8081
```
