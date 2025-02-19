# Quarkus Social API

A **Quarkus Social API** é uma API de operações de uma rede social construída com **Java** utilizando o framework **Quarkus**, banco de dados **MySQL** e **Docker** para facilitar o desenvolvimento, a implantação e a execução da aplicação em diferentes ambientes.  Esta API oferece funcionalidades essenciais para uma rede social, como gestão de usuários, postagens e seguidores, permitindo que você crie e gerencie sua própria plataforma social.

## Estrutura do Projeto

A estrutura do projeto está organizada da seguinte maneira:

### 1. `src/main/java/quarkussocial/rest`

Contém os controllers responsáveis pelos endpoints da API, ou seja, a interface que permite a interação com a API através de requisições HTTP.

- **FollowerResource**: Gerencia as operações relacionadas aos seguidores, como seguir, deixar de seguir e listar seguidores.
- **PostResource**: Gerencia as postagens, incluindo criação, edição, listagem e exclusão.
- **UserResource**: Gerencia as operações relacionadas aos usuários, como cadastro, login, atualização de perfil e exclusão.

### 2. `src/main/java/quarkussocial/persistence/dto`

Contém os objetos de transferência de dados (DTOs) usados para enviar e receber dados entre a API e o cliente.  DTOs são estruturas de dados simplificadas que facilitam a comunicação entre diferentes partes da aplicação, evitando a exposição de detalhes internos dos modelos.

### 3. `src/main/java/quarkussocial/persistence/models`

Contém os modelos de dados e os repositórios que interagem com o banco de dados.  Os modelos representam as entidades do seu domínio (usuários, posts, etc.) e os repositórios são responsáveis por realizar as operações de acesso aos dados.

- **Modelos**: Representações das entidades no banco de dados (por exemplo, User, Post, Follower).
- **Repositórios**: Repositórios que realizam as operações CRUD (Create, Read, Update, Delete) no banco de dados.

### 4. Banco de Dados

A API utiliza o **MySQL** como banco de dados para armazenar as informações dos usuários, postagens e seguidores. As configurações podem ser adaptadas para outro banco de dados, caso necessário, alterando as variáveis no arquivo `.env`.  Este arquivo `.env` permite que você configure as credenciais do banco de dados de forma flexível, sem precisar alterar o código da aplicação.

## Funcionalidades

- **Gestão de Usuário**: Permite criar, exibir, alterar e excluir usuários, além de outras operações como login e logout.
- **Gestão de Postagens**: Permite criar, editar, listar e deletar postagens, incluindo funcionalidades como comentários e curtidas (se implementadas).
- **Gestão de Seguidores**: Permite que usuários sigam outros usuários, criando a rede social da aplicação.

## Requisitos

- **Java 11+**
- **Maven**
- **MySQL**
- **Docker**
- **Docker Compose**

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/by-scottlucas/quarkus-social-api.git
```

2. Navegue até o diretório do projeto:

```bash
cd quarkus-social-api
```

3. Certifique-se de que o Docker e o Docker Compose estão instalados na sua máquina. Você pode verificar a instalação com os comandos `docker --version` e `docker-compose --version`.

4. Crie o arquivo `.env` na raiz do projeto e preencha com suas informações:

```env
DB_USERNAME=seu_user
DB_PASSWORD=sua_senha
DB_URL=jdbc:mysql://mysql:3306/quarkus_social
```

5. Inicie o banco de dados MySQL e a API Quarkus utilizando o Docker Compose:

```bash
docker-compose up -d
```

Este comando irá construir as imagens da API e do MySQL (se ainda não existirem), e iniciar os containers em segundo plano (-d).

6. Verifique se os containers estão rodando corretamente:

```bash
docker-compose ps
```

Este comando lista os containers em execução, mostrando seus nomes, status e portas.

7. Acesse a API através do endereço `http://localhost:8080`.  Você pode usar ferramentas como Postman ou curl para testar os endpoints da API.

## Parando os containers

Para parar os containers em execução, execute o seguinte comando no mesmo diretório onde você iniciou os containers:

```bash
docker-compose down
```

Este comando irá parar e remover os containers, bem como a rede criada pelo Docker Compose.  Ele também remove os volumes associados aos containers, então tenha cuidado se você tiver dados importantes que precisam ser persistidos.

## Arquivos Docker

Este projeto inclui diferentes arquivos Docker para facilitar a construção e execução da aplicação em ambientes distintos. Os arquivos Docker estão localizados em `src/main/docker/` e cada um serve para um tipo específico de build e execução.

- **Dockerfile.jvm**: Usado para rodar a aplicação com a JVM (Java Virtual Machine). Ideal para ambientes de desenvolvimento e teste.
- **Dockerfile.legacy-jar**: Para rodar builds legados em JAR (Java ARchive), em versões mais antigas do Quarkus.
- **Dockerfile.native**: Usado para construir um executável nativo utilizando GraalVM, proporcionando um desempenho melhorado em ambientes de produção.
- **Dockerfile.native-micro**: Otimizado para microserviços com um executável nativo e uma imagem base mínima, ideal para produção em containers.

Para construir a aplicação utilizando um dos arquivos Docker, basta escolher o arquivo desejado e rodar o comando `docker build` na raiz do projeto.

Exemplo para construir usando o `Dockerfile.jvm`:

```bash
docker build -f src/main/docker/Dockerfile.jvm -t quarkus-social-api .
```

Para mais detalhes sobre como usar cada arquivo Docker, consulte a documentação interna do projeto ou os comentários dentro dos próprios arquivos Docker.

## Autor

Este projeto foi desenvolvido por Lucas Santos Silva, Desenvolvedor Full Stack, graduado pela Escola Técnica do Estado de São Paulo (ETEC) nos cursos de Informática (Suporte) e Informática para Internet.

## Licença

Este projeto está licenciado sob a [Licença MIT](./LICENSE).