# 💊 CareDrop

**Sistema de Gestão da Rotina de Medicamentos em Casas de Repouso**

O CareDrop é um sistema desenvolvido com o objetivo de auxiliar casas de repouso no gerenciamento da rotina de medicamentos de idosos, proporcionando maior organização, controle e acompanhamento das administrações realizadas pelos cuidadores.

O projeto busca centralizar as informações dos residentes, medicamentos, prescrições e horários, facilitando o trabalho dos profissionais responsáveis pelos cuidados.

## 🎯 Objetivo do Projeto

Desenvolver uma aplicação web que permita o gerenciamento da rotina de medicamentos em casas de repouso, contribuindo para a organização das atividades dos cuidadores e o acompanhamento das administrações de medicamentos dos residentes.

## ⚙️ Funcionalidades

- Cadastro de casas de repouso.
- Cadastro e autenticação de administradores e cuidadores.
- Gerenciamento de residentes.
- Cadastro e gerenciamento de medicamentos.
- Registro de prescrições médicas.
- Organização dos medicamentos por horários.
- Acompanhamento dos medicamentos pendentes e administrados.
- Registro do histórico de administração de medicamentos.

*As funcionalidades estão sendo implementadas progressivamente.*

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Aplicação |
|---|---|
| Java | Desenvolvimento do backend |
| JSP | Construção das páginas web |
| Servlets | Processamento das requisições |
| JDBC | Comunicação com o banco de dados |
| MySQL | Armazenamento das informações |
| Apache Tomcat | Servidor web |
| HTML | Estrutura das páginas |
| CSS | Estilização das interfaces |

## 🏗️ Arquitetura do Projeto

O sistema utiliza uma organização baseada no padrão MVC (Model-View-Controller), com separação das responsabilidades:

- **Model:** representação das entidades do sistema.
- **DAO:** operações de acesso e manipulação dos dados.
- **Servlet:** processamento das requisições e comunicação entre as camadas.
- **View:** interfaces desenvolvidas com JSP.

### Estrutura inicial

```text
CareDrop/
│
├── Source Packages/
│   ├── dao/
│   │   ├── CasaRepousoDAO.java
│   │   ├── UsuarioDAO.java
│   │   └── CadastroInicialDAO.java
│   │
│   ├── model/
│   │   ├── CasaRepouso.java
│   │   └── Usuario.java
│   │
│   ├── servlet/
│   │   └── CadastroInicialServlet.java
│   │
│   └── util/
│       ├── ConnectionFactory.java
│       └── PasswordUtil.java
│
└── Web Pages/
    ├── META-INF/
    ├── WEB-INF/
    ├── app.css
    ├── cadastro-inicial.jsp
    └── index.jsp
```

## 🗄️ Banco de Dados

O CareDrop utiliza MySQL para armazenar e organizar as informações do sistema.

Principais entidades:

- Casa de Repouso
- Usuário
- Residente
- Medicamento
- Prescrição
- Agendamento
- Administração

A estrutura foi planejada para permitir o gerenciamento de múltiplas casas de repouso, mantendo os dados de cada instituição separados.

## 🔐 Segurança

O projeto utiliza mecanismos de segurança, incluindo:

- Armazenamento de senhas utilizando hash com PBKDF2.
- Separação dos dados por instituição.
- Controle de usuários e seus respectivos tipos de acesso.

## 🚀 Ambiente de Desenvolvimento

Para executar o projeto, são utilizadas as seguintes ferramentas:

- JDK 24
- Apache NetBeans 25
- Apache Tomcat 8.5
- MySQL / XAMPP
- MySQL Connector/J

A configuração do banco de dados é realizada por meio da classe `ConnectionFactory`.

## 📌 Status do Projeto

🚧 **Em desenvolvimento**

O CareDrop está sendo desenvolvido como projeto acadêmico do curso de Engenharia de Software, com implementação progressiva das funcionalidades e evolução da arquitetura da aplicação.

## 👨‍💻 Desenvolvimento

Projeto acadêmico desenvolvido por estudantes de Engenharia de Software.

---

**CareDrop — Mais organização, controle e cuidado na rotina de medicamentos.**
