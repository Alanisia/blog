# Blog

Blog source code.

Frontend is written in Vue while backend is in Spring Boot.

### Build Project

1. Web Pages

  Execute command in app root path `/app`:

  ```bash
  cd app
  npm run build 
  # or 
  yarn build
  ```

2. Server

  Execute command in server root path `/api`:

  ```bash
  cd api
  mvn clean package -DskipTests # skip tests and build for development environment
  mvn clean package -DskipTests -P prod # skip tests and build for production environment
  ```
