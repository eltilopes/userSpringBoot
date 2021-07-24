"# userSpringBoot" 

Implementado Spring Security JWT : JwtAuthenticationFilter , AuthenticationController , ...

Foi realizada a integração do Redis, porem a Regra de negocio nao foi aplicada.
Acredito que o que deveria ser feito era guardar o token na sessão do Redis e quando fosse realizada alguma chamada a endpoin,
pegar essa informação de la! Fiquei na duvida seisso ja estava acontecendo. Implementado >>
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

*** "O projeto so sobe se tiver com o Redis no ar"

https://www.javainuse.com/spring/springboot_session_redis
https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#reference(coniguração atraves de class. Nao necessita de infos no aplication.properties)
https://stackoverflow.com/questions/50668751/how-to-use-spring-boot-authentication-with-redis-session
