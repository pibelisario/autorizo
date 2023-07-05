# Proteto Autorizo
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/pibelisario/autorizo/blob/master/LICENSE) 

# Sobre o projeto

Eu tive a ideia de fazer a aplicação "Controle" a partir da necessidade da empresa a qual trabalho teve de fazer o monitoramento e controle de acesso de pessoas
as dependências da empresa. Com essa aplicação é possível registrar as informações pessoais, data, horário e local em que a pessoa esteve na empresa. 
Está aplicação atualmente esta em uso na empresa e tem sido de grande ajuda para o controle de acesso de pessoas. 

Para facilitar para o usuário do sistema eu importei do banco de dados todos os associados e dependentes de outro sistema da empresa, assim sendo necessário 
cadastrar novas pessoas somente em caso dele não ser associado e ser a primeira vez que ele vem à empresa.

## Layout web

### Tela para adicionar entrada

Nessa tela primeiramente o usuário do sistema deve buscar a pessoa que irá adentrar à empresa, a pesquisa pode ser feita por Rg, Nome e CPF. Caso seja a primeira vez da pessoa será necessário realizar o cadastro da mesma (verificar na tela de cadastro) e posteriormente buscar o cadastro e realizar a entrada, escolhendo o departamento em que ela irá.

OBS: Nenhuma informação de pessoas reais foram utilizadas nas telas.

![web 0](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20Adicionar%20Entrada.png?raw=true) 

### Tela de visualização das últimas entradas

Nessa tela é possível verificar as últimas entradas feitas na empresa (tela possui paginação),
nela e possível verificar informações pessoais, data/hora de entrada e o local em que a pessoa foi na empresa. Também e possível verificar os detalhes de cada pessoa que entrou clicando na opção detalhes (verificar na tela detalhes) ou até mesmo excluir alguma entrada.

![web 1](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20de%20Entradas.png?raw=true) 

### Tela detalhes do cadastro

Tela para visualizar informações pessoais sobre associado, dependente ou outros.

![web 2](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20Detalhes.png?raw=true)

### Tela cadastro novo

Tela de cadastro

![web 3](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20de%20Cadastro.png?raw=true)

### Tela buscar cadastro

Tela para buscar cadastro e caso necessário editar ou excluir

![web 4](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20Buscar%20Cadastro.png?raw=true)

### Tela buscar entradas

Nessa tela e possível fazer a busca de entradas entre as datas escolhidas.

![web 5](https://github.com/pibelisario/controle/blob/dev1/assets/Tela%20Buscar%20Entradas.png?raw=true)


# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
  
## Front end
- thymeleaf
- Bootstrap
- HTML
- CSS
- JS
  
## Implantação em produção
- Back end: AWS
- Banco de dados: MySQL

<!--
# Como executar o projeto

## Back end
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/devsuperior/sds1-wmazoni

# entrar na pasta do projeto back end
cd backend

# executar o projeto
./mvnw spring-boot:run
```

## Front end web
Pré-requisitos: npm / yarn

```bash
# clonar repositório
git clone https://github.com/devsuperior/sds1-wmazoni

# entrar na pasta do projeto front end web
cd front-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```
-->

# Autor

Paulo Inácio Belisario de Noronha

https://www.linkedin.com/in/paulo-in%C3%A1cio-belisario-de-noronha-392946b6/

