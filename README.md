# Descrição
A API de Filmes possui o objetivo principal de organizar filmes em um catálogo que mostra informações como o nome, descrição, nota IMDb, entre outros detalhes.
Além disso, a API gerencia o relacionamento entre filmes e gêneros.

Este projeto foi criado para aplicar conhecimentos adquiridos através de diversos conteúdos na internet.
Ele inclui a implementação de relacionamentos entre classes, o que torna essa funcionalidade essencial no contexto do catálogo de filmes.

# Execução
1. Clone este repositório para a sua máquina.
2. Abra e execute o arquivo principal, que contém a classe main. O script de seed irá rodar automaticamente, preenchendo o banco de dados.
3. Após a execução, a API estará pronta para testes através do H2 Console, que pode ser acessado em http://localhost:8080/h2-console ou através do Postman.
Certifique-se de que o servidor esteja rodando e que os requisitos do sistema (como a versão do Java) sejam atendidos antes da execução.

# Análise técnica

### Endpoints
A API de Filmes possui dois endpoints principais:

__/movie:__ Responsável por gerenciar as informações dos filmes, permitindo operações de CRUD (Create, Read, Update e Delete).
__/genre:__ Encarregado de gerir os gêneros dos filmes, também com suporte às operações de CRUD.

Para visualizar detalhes e testar os endpoints, acesse o Swagger da API em http://localhost:8080/swagger-ui.html.
Lembre-se de que o servidor precisa estar em execução para acessar o Swagger.

### Relacionamento
As tabelas têm um relacionamento __ManyToMany__, portanto, cria-se uma tabela intermediária __(tb_movie_genre)__.
Nessa tabela intermediária contem duas colunas: movie_id e genre_id. É onde mostra o id do filme que está relacionado com um id de gênero.

### Design pattern
Neste projeto, foram utilizados os padrões de __arquitetura de camadas__ e __DTO (Data Transfer Object)__.

A arquitetura de camadas ajuda a organizar o código em diferentes níveis de responsabilidades, tornando-o mais fácil de entender e manter.

O uso de DTO permite transferir dados de forma mais eficiente entre a camada de apresentação e a camada de negócios, reduzindo o acoplamento e melhorando a escalabilidade.

# Tecnologias utilizadas
- Java: É a linguagem de programação utilizada no projeto. Estou estudando essa linguagem e achando incrível a sua sintaxe e facilidade de entender o que está sendo escrito. Na minha opinião, a sua forte tipagem ajuda muito.
- Spring boot: Um framework Java que facilita a criação de aplicações web, tornando mais simples o desenvolvimento.
- Swagger: Uma ferramenta para documentar APIs. É possível visualizar a estrutura e as operações da API e testar facilmente os endpoints disponíveis.
- Postman: Uma ferramenta que ajuda no desenvolvimento e teste de APIs. 
- H2 Database: Um banco de dados em memória utilizado para fins de desenvolvimento e testes.
- MySQL: Um SGBD relacional utilizado para ambientes de produção.

# Perfil dev/test
Na aplicação, há dois tipos de perfis para o banco de dados: dev e test.
No caminho src/main/resources é possível encontrar o arquivo application.properties onde será feita a mudança, se quiser. A principio está salvo como perfil de test (spring.profiles.active=test), portanto, irá rodar a aplicação no banco de dados H2. Para a mudança basta trocar "test" por "dev".

 __Atenção:__ Caso queira usar o perfil "dev", é necessária a criação de um database no MySQL com o nome de "apisistemafilmes".

# Considerações finais
Graças a esse projeto, deixei mais sólido o meu conhecimento em relacionamento entre tabelas e expandi meu conhecimento em Java e Spring Boot.
Senti que estou evoluindo no tratamento de exceções. Pesquisei bastante e consegui aplicar também esse conhecimento, verificando as exceções que eram possíveis de ocorrer e então tratando-as.
Além disso, precisei ler bastante sobre os design patterns que implementei, já que surgiram algumas dúvidas durante o desenvolvimento.

Vou ficar feliz em receber alguma mensagem com feedbacks sobre o projeto. Se você perceber que errei em alguma coisa, que poderia melhorar em determinadas partes do código, por favor me manda uma mensagem, seria ótimo pro meu aprendizado!
