# Proteto Autorizo
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/pibelisario/autorizo/blob/master/LICENSE) 

# Sobre o projeto

Essa aplicação surgiu com objetivo de fazer um controle de compras dos associados na empresa a qual trabalho.
 
A aplicação funciona da seguinte maneira: caso o associado esteja liberado para realizar uma compra, o responsável pelo sistema que no caso é alguma farmácia conveniada irá lançar a compra para o mesmo, caso esteja bloqueado ele irá informar ao associado que não é possível realizar a compra e que é necessário entrar em contato com a matriz para verificar o motivo. 

As informações referentes sobre o autorizo ou não para a compra são importadas mês a mês do banco de dados da matriz. Ao final de cada mês é gerado um relatório com todos os associados que fizeram compra e em qual farmácia foi realizada a compra, após isso e feito o lançamento no sistema interno da empresa para desconto em folha do associado.

## Layout web

### Tela de login

Cada farmácia conveniada possui um login especifico para acesso ao sistema. É necessário esse controle de acesso, pois como mencionado ao final de cada mês e gerado um relatório com todos os lançamentos feito pela farmácia.

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

