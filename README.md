Explorando-Marte
=======================
Projeto cliente de uma API Rest para controle de sondas espaciais. Interface via linha de comando.

Tecnologias usadas
----------------------- 
 - framework Jersey para comsumo de um serviço REST;
 - biblioteca org.json para manipulação de objetos JSON; 
 - JUnit para testes unitários;
 - JMockit para mocks e verificações nos testes unitários.
 
Design
--------
Como a aplicação é somente um cliente, preferi não criar uma camada de domínio e somente criar uma
camada de apresentação e outra de aplicação:
 - View
 
  Camada de apresentação, responsável por prover uma interface via linha de comando com usuário e 
 implementar funções que ajudem a interpretar comandos vindos do console para realizar chamadas
 á camada de aplicação
 - Application
 
  Camada responsável por implementar o cliente REST e prover uma interface de aplicação para o uso da
  camada de apresentação.


Utilização
-------------
O programa irá solicitar o preenchimento de informações via console para o processamento. Quando
houver mais de uma informação sendo solicitada, as mesmas devem ser separadas por um espaço em branco com
a exceção do comando número 4
 1. Executar a classe Main via linha de comando;
 2. Digitar conforme solicitado o ponto X e Y do ponto superior direito do planalto. Ex: 5 5;
 3. Digitar conforme o solicitado a posição X e Y da sonda e a sua direção. Ex: 1 2 N;
 4. Digitar os comandos para movimentar a sonda. Ex: LMLMLMLMR;
 5. Refaça os passos 3 e 4 quantas vezes achar necessário ou a quaquer momento digitar exit;
 6. O programa mostrará todas as sondas movimentadas em suas novas posição ordenadas pela execução dos comandos.
