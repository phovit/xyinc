<b>#xyinc</b>

Autor: Paulo Henrique de Oliveira

<b>##Requisitos para execução:</b>

JDK 8, Maven

##O container web Tomcat é embarcado devido ao SpringBoot e executa juntemente com a aplicação, sem* necessidade de mais configurações.

* o Tomcat irá executar na porta 8080, se necessário alteração por algum motivo, deve ser alterado o parâmetro abaixo no arquivo application.properties
server.port=8080


##<b>URL's (Serviços) Disponívels</b>

| Método | Path | Descrição |
| ------ | ------ | ------:
| GET | /poi | retorna a lista de todos os Pontos de interesse cadastrados 
| GET | /poi/{id}| retorna um Ponto de interesse com base no Id informado 
| GET | /poi/proximidade/{x}/{y}/{s} | Busca os pontos de interesse próximos, com base na cordenada e distância informadas |
| POST | /poi | Insere um ponto de interesse, o qual deverá ser enviado no formado JSON no body da requisição. Retorna a url de acesso ao registro criado através do campo "location" da resposta HTTP 


<b>Execução da Aplicação:</b>

No diretório raiz do projeto:
- Compilar o projeto: mvn clean install
- Executar o projeto: mvn spring-boot:run
após compilado, existem também duas outras formas de se executar o projeto:
- Em Alguma IDE:
	Executar o arquivo Boot.java, existente no pacote br.com.xyinc
- Via Linha de comando
	Acessar o diretório xyinc/target e executar o comando abaixo
		java -jar xyinc-1.0.0-SNAPSHOT.jar	

<b>Execução dos testes:</b>
- Em alguma IDE
	Executar o arquivo Testes.java, existente no pacote br.com.xyinc
