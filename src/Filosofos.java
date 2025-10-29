public class Filosofos extends Thread {
    //Estende thread, tornando cada filosofo uma thread independente

    final static int TEMPO_MAXIMO = 100; //Tempo máximo em milissegundos
    Mesa mesa;
    int filosofo; //id do filosofo de 0 a 4

    //Construtor que inicializa o filósofo
    //Super(nome): passa o nome para a classe Thread
    //Armazena a referência da mesa e o ID
    public Filosofos(String nome, Mesa mesaDeJantar, int fil) {
        super(nome);
        mesa = mesaDeJantar;
        filosofo = fil;
    }

    public void run() {
        int tempo = 0;
        while (true) { //Loop infinito - filosofo nunca para

            //1. Pensar
            //Gera um tempo aleatorio de 0 a 100ms
            //Chama pensar() que faz a thread dormir
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            pensar(tempo);

            //2. Tenta pegar dois garfos (pode esperar aqui)
            //Esse metodo e sincronizado na classe Mesa para evitar condicoes corridas
            mesa.pegarGarfos(filosofo);

            //3. Come por tempo aleatorio
            //Come por esse periodo
            tempo = (int) (Math.random() * TEMPO_MAXIMO);
            comer(tempo);

            //4. Devolve os garfos
            //Notifica outros filosofos que os garfos estão disponíveis
            mesa.returningGarfos(filosofo);
        }
    }

    //Métodos auxiliares, que pausa o thread por tempo milissegundos
    //Simula o filósofo pensando
    //Captura a exceção se a thread for interrompida
    public void pensar(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O filosofo pensou em demasia");
        }
    }

    public void comer(int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O filosofo comeu em demasia");
        }
    }
}