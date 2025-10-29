1. 1. Oque é o Jantar do Filósofos?: É um problema clássico da ciência da computadação prosposto por Edsger Dijkstra em 1965. Ele ilustra desafios fundamentais da sincronização de sistemas concorrentes.

2. Problemas Abordados:
3. .Deadlocks(Impasse): Quando todos os processos ficam travados esperando recursos um dos outros;
4. .Starvation(Inanição): Ocorre quando um processo nunca consegue acesso aos recursos necessários;
5. .Condições de Corrida(Race Conditions): Quando threads competem por recursos compartilhados sem coordenação adequada.

6. O Cenário:
7. Cinco filósofos sentados em uma mesa circular. Entre cada par de filósofos há um garfo (total: 5 garfos). Cada filósofo alterna entre pensar e comer. Para comer, precisa de dois garfos(esquerdo e direito). O desafio é coordenar o acesso aos garfos sem ocorrer deadlock ou starvation.

Análise Detalhada do Código
1. Classe Jantar.java - Ponto de Entrada
<img width="663" height="411" alt="image" src="https://github.com/user-attachments/assets/0115f293-dfbd-4052-9e81-46dfc2477486" />
 .Loop que cria 5 filosofos (threads)
->Cada filósofo recebe:
.Um nome "Filosofo_0", "Filosofo_1", etc.
.A referência da mesa (recurso compartilhado)
.Seu número identificador(0 a 4)   
