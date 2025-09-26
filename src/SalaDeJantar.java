public class SalaDeJantar {

    private final Estado[] estados;
    private final int numeroDeFilosofos;

    public SalaDeJantar(int numeroDeFilosofos) {
        this.estados = new Estado[numeroDeFilosofos];
        this.numeroDeFilosofos = numeroDeFilosofos;

        for(int i = 0; i < numeroDeFilosofos; i++) {
            estados[i] = Estado.PENSANDO;
        }

    }

    public synchronized void pegarGarfos(int id) throws InterruptedException{
        estados[id] = Estado.FAMINTO;
        System.out.println("Filósofo" + id + " está FAMINTO");

        //Espera enquanto os vizinhos estiverem comendo:

        while (vizinhoEsquerdaComendo(id) || vizinhoDireitaComendo(id)){
            wait();
        }
        estados[id] = Estado.COMENDO;
    }
    public synchronized void largarGarfos(int id){
        estados[id] = Estado.PENSANDO;
        System.out.println("Filósofo" + id + " terminou de comer e está pensando novamente");

        //notifica todas as outras threads em espera, para que possam reaviliar sua condição
        notifyAll();
    }

    private boolean vizinhoEsquerdaComendo(int id){
        return estados[getVizinhoEsquerda(id)] == Estado.COMENDO;
    }
    private boolean vizinhoDireitaComendo(int id){
        return estados [getVizinhoDireita(id)] == Estado.COMENDO;
    }
    private int getVizinhoEsquerda(int id){
        return (id + numeroDeFilosofos - 1) % numeroDeFilosofos;
    }
    private int getVizinhoDireita(int id){
        return (id + 1) % numeroDeFilosofos;
    }

}
