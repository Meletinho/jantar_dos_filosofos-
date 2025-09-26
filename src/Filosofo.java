import java.util.Random;

public class Filosofo implements Runnable {

    private final int id;
    private final SalaDeJantar salaDeJantar;
    private Random random = new Random();

    public Filosofo(int id, SalaDeJantar salaDeJantar){
        this.id = id;
        this.salaDeJantar = salaDeJantar;
    }

    @Override
    public void run() {
        try{
            while(true){
                pensar();
                salaDeJantar.pegarGarfos(id);
                comer();
                salaDeJantar.largarGarfos(id);

            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filófo" + id + " está pensando");
        Thread.sleep(random.nextInt(3000) + 1000); //pensa de 1 a 4 segundos
    }

    private void comer() throws InterruptedException {
        System.out.println(">>> Filósofo " + id + " está COMENDO.<<<");
        Thread.sleep(random.nextInt(4000) + 2000);  //come de 4 a 6 segundos
    }

}
