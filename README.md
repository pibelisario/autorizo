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

![web 0](https://github.com/pibelisario/autorizo/blob/relatorioMensal/assets/Tela%20Login.png?raw=true) 

### Tela de busca

Nessa tela é feita a busca do associado pelo CPF, após a busca é aberta uma tela abaixo com as informações do associado, caso ele esteja liberado para compra o botão "Adicionar Compra" aparecera, caso contrario irá aparecer "BLOQUEADO".

![web 1](https://github.com/pibelisario/autorizo/blob/relatorioMensal/assets/Tela%20Busca.png?raw=true) 

### Tela adiconar compra

Quando clicado no botão "Adicionar Compra" o sistema e redirecionado para esta tela. Essa tela permite que uma compra seja lançada, para tal ação é necessário digitar o valor e o cupom fiscal da compra, após isso "Lançar". Quando uma compra é lançada automaticamente o sistema já diminui o limite de compras do associado baseado no valor da compra, cada associado possui um limite mensal de compras não podendo ultrapassar o mesmo. 

Logo abaixo temos o botão "Gerar Relatório" esse relatório traz o demostrativo de compras individuais de casa associado.

![web 2](https://github.com/pibelisario/autorizo/blob/relatorioMensal/assets/Tela%20Compra.png?raw=true)

### Tela administrativa

Nessa tela onde somente o administrador tem acesso é possível gerar o relatório de compras, onde o mesmo contem todas as compras realizadas em todas as farmácias contendo informações como, nome da farmácia, dados do associado e valor da compra.

![web 3](https://github.com/pibelisario/autorizo/blob/relatorioMensal/assets/Tela%20ADM.png?raw=true)

### Relatório gerado

Aqui temos um exemplo de como o relatório e gerado e as informações contidas nele.

![web 4](https://github.com/pibelisario/autorizo/blob/relatorioMensal/assets/Relatorio.png?raw=true)


# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Spring security
  
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

