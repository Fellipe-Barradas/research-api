# research-api
## Sobre o projeto
<br>

- API para aprimorar as habilidades Spring Boot e banco de dados.<br>
- API permite criar, deletar e atualizar usuários/pesquisas/rating assim como metódos unicos.

# Indice 
- Como utilizar
- Modelo conceitual
- Resources
- Routes
- Tecnologias
- Autor


# Como implementar

Pré requisitos: Java 17
``` bash
    # Clonar repositório
    git clone https://github.com/Fellipe-Barradas/research-api.git

    # Entrar na pasta do projeto
    cd research-api

    # Executar projeto
    ./mvnw spring-boot:run
```
<br>

# Modelo conceitual
![diagrama_research](https://github.com/Fellipe-Barradas/research-api/assets/115052701/2b144e14-9e16-4923-9ade-efac731482cd)

# Resources
- /users
- /researchies
- /ratings
<br>

# Routes

- **GET** ----- localhost:8080/users
- **GET**  -----     localhost:8080/users/1
- **POST**   ---   localhost:8080/users
- **PUT**     -----  localhost:8080/users/1
- **DELETE**  -   localhost:8080/users/1

<br>

# Tecnologias
- Java
- Spring Boot 3
- Banco de dados H2
- JPA/Hibernate
<br>
<br>

# Autor
<p >Luis Fellipe Bezerra Barradas</p>
<a href="https://www.linkedin.com/in/luis-fellipe-100759204/?originalSubdomain=br" style=text-align:center;>Linkedin</a>

