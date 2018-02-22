#xyinc

##Requisitos para execução
JDK 8
Maven
MySql5

##Configurações Necessárias.
1 - criar um banco de dados com o nome xy_db
2 - configurar usuário e senha e porta, se necessário, no arquivo application.properties. (Atualmente o usuário está "root" e sem senha)

spring.datasource.username=root
spring.datasource.password=
spring.datasource.url=jdbc\:mysql\://localhost/xy_db
  	

##O container web Tomcat é embarcado devido ao SpringBoot e executa juntemente com a aplicação, sem* necessidade de mais configurações.

* o Tomcat irá executar na porta 8080, se necessário alteração por algum motivo, deve ser alterado o parâmetro abaixo no arquivo application.properties

server.port=8080

##Execução da Aplicação
- Iniciar a execução do banco de dados
- Em Alguma IDE:
	Executar o arquivo Boot.java, existente no pacote br.com.xyinc
- Via Linha de comando
	Acessar o diretório xyinc/target e executar o comando abaixo
		java -jar xyinc-1.0.0-SNAPSHOT.jar	

##Execução dos Testes
- Iniciar a execução do banco de dados
- Em alguma IDE
	Executar o arquivo Testes.java, existente no pacote br.com.xyinc

OBS: durante a execução dos testes, a base será populada com os dados informados para teste.


##URL's (Seriviços) Disponívels
http://localhost:8080/poi/list
traz a lista de todos os Pontos de interesse cadastrados

http://localhost:8080/poi/add
adiciona um registro de ponto de interesse, via HTTP POST
é necessário enviar como parâmetro o objeto no formato JSON
Exemplo:
{	"nome": "Lanchonete",
    "x": 27,
    "y": 12
}


http://localhost:8080/poi/addGet/{name}/{x}/{y}
adiciona um registro de ponto de interesse, via HTTP GET
Os parâmetros são passados na própria URL
Exemplo:
http://localhost:8080/poi/addGet/Lanchonete/27/12


http://localhost:8080/poi/buscaProximos/{x}/{y}/{s}
Busca os pontos de interesse próximos.
Os parâmetros são passados na própria URL.
Nesta chamada, o cálculo da distancia é feito diretamente no banco de dados, reduzindo o tráfego de informações na rede.
Exemplo:
http://localhost:8080/poi/buscaProximos/20/10/10

http://localhost:8080/poi/buscaProximosForEach/{x}/{y}/{s}
Busca os pontos de interesse próximos.
Os parâmetros são passados na própria URL
Nesta chamada, o cálculo da distancia é feito em um método Java, reduzindo o processamento de dados no Banco de dados.
Exemplo:
http://localhost:8080/poi/buscaProximosForEach/20/10/10
