
public class Main {
    public static void main(String[] args) {

        final int NUMERO_DE_FILOSOFOS = 5;
        SalaDeJantar sala = new SalaDeJantar(NUMERO_DE_FILOSOFOS);
        Filosofo[] filosofos = new Filosofo[NUMERO_DE_FILOSOFOS];

        for (int i = 0; i < NUMERO_DE_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, sala);
            Thread threadFilosofo = new Thread(filosofos[i]);
            threadFilosofo.start();
        }
    }
}