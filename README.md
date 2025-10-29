1. Oque é o Jantar do Filósofos?: É um problema clássico da ciência da computadação prosposto por Edsger Dijkstra em 1965. Ele ilustra desafios fundamentais da sincronização de sistemas concorrentes.

2. Problemas Abordados:
3. .Deadlocks(Impasse): Quando todos os processos ficam travados esperando recursos um dos outros;
4. .Starvation(Inanição): Ocorre quando um processo nunca consegue acesso aos recursos necessários;
5. .Condições de Corrida(Race Conditions): Quando threads competem por recursos compartilhados sem coordenação adequada.

6. O Cenário:
7. Cinco filósofos sentados em uma mesa circular. Entre cada par de filósofos há um garfo (total: 5 garfos). Cada filósofo alterna entre pensar e comer. Para comer, precisa de dois garfos(esquerdo e direito). O desafio é coordenar o acesso aos garfos sem ocorrer deadlock ou starvation.

Análise Detalhada do Código

1. Classe Jantar.java - Ponto de Entrada]


<img width="663" height="411" alt="image" src="https://github.com/user-attachments/assets/0115f293-dfbd-4052-9e81-46dfc2477486" />
 

 .Loop que cria 5 filosofos (threads)

->Cada filósofo recebe:

.Um nome "Filosofo_0", "Filosofo_1", etc.

.A referência da mesa (recurso compartilhado)

.Seu número identificador(0 a 4)   

.start() inicia a execução da thread (chama o método run())

2. Classe Filosofos.java - Representa Cada Filósofo

<img width="337" height="80" alt="image" src="https://github.com/user-attachments/assets/43442823-ba06-4c6a-b29d-8625abe4563a" />

Estende Thread, tornando cada filosofo um thread independente.


<img width="384" height="76" alt="image" src="https://github.com/user-attachments/assets/eadedda1-ce6b-4062-b883-a2d07f1fa5c2" />


TEMPO_MAXIMO: Tempo máximo (em milissegundos) para pensar ou comer;
mesa: referência à mesa compartilhada; 
filosofo: id de cada thread (0 a 4);


<img width="508" height="138" alt="image" src="https://github.com/user-attachments/assets/e4de005c-f1dd-4483-95be-94d55c3158a4" />


Construtor que inicializa o filosofo 
super(nome): passa o nome para a classe Thread
Armazena a referência da mesa e o id

<img width="289" height="122" alt="image" src="https://github.com/user-attachments/assets/aa0757f7-f09d-4f07-9700-ad5eaafb81e7" />


Método run() é executado quando a thread inicia 
Loop infinito: o filosofo nunca para de pensar ou comer



<img width="428" height="63" alt="image" src="https://github.com/user-attachments/assets/adb84785-8fd3-4dd5-a8cf-62fa3f0b4d1b" />



Gera um tempo aleatório entre 0 e 100ms
Chama pensar() que faz thread dormir (simula o filósofo pensando)




<img width="338" height="65" alt="image" src="https://github.com/user-attachments/assets/c2a54ef6-020d-418d-9aa3-fe9eac5f8af5" />


CRUCIAL: Tenta pegar dois garfos (esquerdo e direito).
Este método é sincronizado na classe Mesa para evitar condições de corrida.
Se os garfos não estiverem disponíveis, a thread espera



<img width="467" height="91" alt="image" src="https://github.com/user-attachments/assets/11454a37-565b-4975-89d0-7380e61fe4c0" />


Gera novo tempo aleatório 
A thread consome o recurso necessário por esse breve tempo 



<img width="324" height="49" alt="image" src="https://github.com/user-attachments/assets/ac805ce5-0a0b-4b33-8797-47b8ee712500" />



Devovle os garfos para a mesa
Notifica outros filósofos que os garfos estão disponíveis. 


<img width="543" height="256" alt="image" src="https://github.com/user-attachments/assets/6fffcc2f-0565-45de-b172-ed923ad3abce" />


Métodos que pausa a thread por tempo milisessgundos
Simula o filófo pensando 
Captura a exceção se a thread for interrompida



<img width="529" height="250" alt="image" src="https://github.com/user-attachments/assets/ea064501-5d82-4d40-be90-5d54422df5cc" />



Similar ao pensar, mas simula o ato de pensar


3. Classe Mesa.java - Gerencia Recursos e Sincronização

   <img width="226" height="103" alt="image" src="https://github.com/user-attachments/assets/2773f4ae-8c7d-449f-8630-270144b959aa" />


   Classe central que gerencia todos os recursos compartilhados

   
















