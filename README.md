# diazero-processo-seletivo
## Diazero processo seletivo desenvolvedor Java
###### A imagem do container deste projeto pode ser encontrada em **[Docker hub](https://hub.docker.com/repository/docker/r3n4nm/diazero-processo-seletivo-image)**

### Projeto desenvolvido utilzando:  
- Java 11  
- Spring boot 2.5.3  
- Apache Maven 3.8.1  
- H2 1.4.199 (desenvolvimento)  
- MySQL Community Server 8.0.23 (produção)  

### Considerações  
- API desenvolvida com Ecossistema Spring (Spring Secutiry, Spring DATA JPA, Spring WEB);
- Testes unitários na camada de serviço foram implementados;  
- 2 usuários em mémoria foram criados:


| user  | password  | role  |
|---|---|---|
| admin  | admin  | admin  |
|  user | user  | user  |

### Configuração Base de dados  
 Para o desenvolvimento o banco escolhido foi o H2.  
 Edite o arquivo application.properties para modificar as configurações do seu banco.  
 #### application.properties
``` 
spring.datasource.url=jdbc:h2:mem:diazerodb  
spring.datasource.username=sa  
spring.datasource.password=  
spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  
```
 

### Regras de negócio
 - A resposta para as requições são diferentes dependendo do tipo de Role do usuário.
	Informações mais técnicas como os paramêtros: createdAt, updatedAt e closedAt, apenas são informados ao admin;
 - Foi criado um novo paramêtro para informar aos usuários o estado atual do incident, o status;
 - Um incident já fechado (status: closed) não pode ser atualizadado e nem fechado novamente;
 - Apenas o admin pode deletar um incident.
  
### Utilizando os recursos  
As requisições devem ser encaminhadas a localhost:8080.

| Info  | Method  | URI  | Content-Type |
|---|---|---|---|
| Listando incidents  | GET | /incidents  |   |
| Registrando incident  | POST  | /incidents   | application/json |
| Buscando incident por ID | GET | /incidents/{Id}  |   |
| Atualizando incident  | PUT | /incidents/update/{Id} | application/json |
| Fechando incident | PUT | /incidents/close/{Id} |   |
| Deletando incident | DELETE | /incident/{Id} |   |
  
  ## Avaliação:  
  ### Critérios para avaliação  
#### Será avaliado o conhecimento do framework spring, como você estrutrou o projeto e a clareza da solução.Lembre-se de usar inglês no processo de codificação.
1) Tech stack
spring-boot, spring-data, maven. Fique à vontade para usar qualquer outra dependência no projeto.
2) Criar uma aplicação spring-boot para cadastro de incidentes. A aplicação deverá fornecer operações
REST que possibilitem o cadastro/manutenção/remoção de incidentes.
Um incidente é composto pelos campos (coloque mais campos se achar necessário).
Incident
	- idIncident
	- name
	- description
	- createdAt
	- updatedAt
	- closedAt
3) Utilize algum banco de dados 'embeded' (h2 por ex) para persistência dos dados. Pode usar qualquer
outro banco desde que você consiga documentar os passos para inicialização da aplicação.
4) Versione seu projeto no github de forma pública e nos envie o link do projeto.
[OPCIONAL]
* Use docker e docker-compose
* Use spring-security
  
  
 
