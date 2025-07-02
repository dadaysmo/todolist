# To-do List API com Spring Boot

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

API RESTful para um sistema de lista de tarefas (To-do List), desenvolvida com Spring Boot. O projeto permite criar, listar, atualizar e deletar tarefas, incluindo tratamento de erros e validação de dados.

---

### 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **MySQL Database**
* **Maven**
* **Jakarta Bean Validation**

---

### ✨ Funcionalidades

- [x] **CRUD Completo:** Criar, Ler, Atualizar e Deletar tarefas.
- [x] **Validação de Dados:** Impede a criação de tarefas com dados inválidos (ex: título em branco).
- [x] **Tratamento de Exceções Centralizado:** Respostas de erro amigáveis e padronizadas para toda a API (`@ControllerAdvice`).

---

### Endpoints da API

| Método | Endpoint                | Descrição                                         | Exemplo de Body (para POST/PUT)                               |
| :----- | :---------------------- | :------------------------------------------------ | :------------------------------------------------------------ |
| `POST` | `/todo`                   | Cria uma nova tarefa.                             | `{"titulo": "Nova Tarefa", "descricao": "Detalhes", "feito": false}` |
| `GET`  | `/todo`                   | Lista todas as tarefas cadastradas.               | N/A                                                           |
| `PUT`  | `/todo/{id}`              | Atualiza uma tarefa existente pelo seu ID.        | `{"titulo": "Tarefa Att", "descricao": "Detalhes Att", "feito": true}` |
| `DELETE`| `/todo/{id}`              | Deleta uma tarefa pelo seu ID.                    | N/A                                                           |

---

### 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/dadaysmo/todolist.git](https://github.com/dadaysmo/todolist.git)
    ```
2.  **Configure o Banco de Dados:**
    * Crie um banco de dados MySQL chamado `todolist`.
    * Altere as credenciais do banco no arquivo `src/main/resources/application.properties` se necessário.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/todolist
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    ```
3.  **Execute a aplicação** pela sua IDE (Eclipse, IntelliJ) ou via terminal:
    ```bash
    mvn spring-boot:run
    ```
4.  A API estará disponível em `http://localhost:8080`.

---

### ✍️ Autor

**Dayanne Souza** - [LinkedIn](https://www.linkedin.com/in/dadaysmo/) [GitHub](https://github.com/dadaysmo)
