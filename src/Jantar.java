public class Jantar {

    //Ponto de entrada, cria a simulação


    //Instancia uma única Mesa: objeto compartilhado por todas as threads
    //Cria 5 threads de Filosófos: cada uma com ID único (0 a 4)
    //Inicia as threads: .start() chama o metodo run() de cada filósofo
    //Execução paralela: todas as threads executam simultaneamente

    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        for(int filosofo = 0; filosofo < 5 ; filosofo++){
            System.out.println("");
            new Filosofos("Filosofo_" + filosofo, mesa, filosofo).start();
        }
    }
}
