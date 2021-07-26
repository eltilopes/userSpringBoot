# userSpringBoot 

### Subir Redis e RabbitMq antes de subir o projeto 
### Deixei aqui alguns links de tutoriais que utilizei ou iria utilizar para implementar as funcionalidades solicitadas
### Existe um arquivo.json de uma documentação da api : src/main/resources/static/Insomnia_user_spring_boot_security_jwt.json 

## Spring Security JWT
Implementado : JwtAuthenticationFilter , AuthenticationController , ...

##Redis
Foi realizada a integração do Redis, no login da aplicação a session é guaradada no Redis.
Acredito que o que deveria ser feito era guardar o token na sessão do Redis e quando fosse realizada alguma chamada a endpoin,
pegar essa informação de la! Fiquei na duvida se isso ja estava acontecendo. 
Implementado >>
WebSecurityConfig / AuthenticationController / application.properties

Baixar Redis:
Go to Redis Downloads Page at github and download the Redis zip file.
    https://github.com/microsoftarchive/redis/releases/tag/win-2.8.2104

Go to the location where the downloaded contents are unzipped
    redis-server.exe --maxheap 1024M

Open a new Command Line Window
    redis-cli.exe
    monitor


spring.session.store-type=redis
spring.redis.host=localhost
spring.redis.port=6379

https://www.javainuse.com/spring/springboot_session_redis
https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#reference(coniguração atraves de class. Nao necessita de infos no aplication.properties)
https://stackoverflow.com/questions/50668751/how-to-use-spring-boot-authentication-with-redis-session

##RabbitMQ

Configurações de conexão com a instância do RabbitMQ

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672

https://www.programmersought.com/article/5692650956/ (configClass... mais arquiteturado)
https://spring.io/guides/gs/messaging-rabbitmq/ (hello)
https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5 (hello)
https://www.youtube.com/watch?v=gaLmPqrm5LI 


Criei a regra para controlar o envio de emails e postar na fila do rabbitMq.
Esxiste uma classe chamada Runner que faz esse envio teste para a fila.

Criado docker-compose.yml para a dockerização do RabbitMq

Sei que o solicitado ficou em consumir a fila de outro sistema com rabbitMq ou Kafka(na descrição do teste.pdf isso ficou confuso)
Porem por questao de tempo mesmo nao consegui implementar essa parte e o envio de email
##Envio de Email
https://www.baeldung.com/spring-email
