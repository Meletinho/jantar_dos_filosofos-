public class Mesa {

    //constantes que definem os estados possíveis e configurações
    final static int PENSANDO = 1;
    final static int COMENDO = 2;
    final static int FOME = 3;
    final static int PRIMEIRO_FILOSOFO = 0;
    final static int NR_FILOSOFO = 5;
    final static int ULTIMO_FILOSOFO = NR_FILOSOFO - 1;

    //estrutura de dados compartilhadas

    boolean[] garfos = new boolean[NR_FILOSOFO];
    //garfos[]: true = livre, false = ocupado

    int[] filosofos = new int[NR_FILOSOFO];
    //filosofos[]: estado atual de cada filosofo

    int[] tentativas = new int[NR_FILOSOFO];
    //tentativas[]: contador de tentativas frustradas de comer

    int[] contador = new int[5];
    //contador[] = quantas vezes cada filósofo comeu

    //Inicializa todos os garfos como livres e filósofos como PENSANDO
    public Mesa() {
        for (int i = 0; i < 5; i++) {
            garfos[i] = true;
            filosofos[i] = PENSANDO;
            tentativas[i] = 0;
        }
    }

    //Coração da solução do deadlock

    //Metodo critico - pegarGarfos
    //Synchronized: garante que uma thread execute por vez
    //As outras threads ficam bloqueadas esperando na entrada
    public synchronized void pegarGarfos(int filosofo) {

        //Marca o filosofo em questão que está com fome
        filosofos[filosofo] = FOME;

        //Verificação crítica:
        //Enquanto algum vizinho estiver COMENDO, ESPERA
        while (filosofos[aEsquerda(filosofo)] == COMENDO ||
                filosofos[aDireita(filosofo)] == COMENDO) {
            //Não posso comer agora, algum vizinho está comendo
            try {
                //incrementa o contador de tentativas a cada espera
                tentativas[filosofo]++;
                wait(); //Libera o lock e coloca o thread em espera até ser notificado
            } catch (InterruptedException e) {
                e.getLocalizedMessage();
            }
        }

        //Zera tentativas(conseguiu comer)
        //Marca ambos os garfos como ocupados
        //Muda de estado para comendo
        tentativas[filosofo] = 0;
        garfos[garfoEsquerdo(filosofo)] = false;  //Ocupa garfo esquerdo
        garfos[garfoDireito(filosofo)] = false;   //Ocupa garfo direito
        filosofos[filosofo] = COMENDO;            //Atualiza estado

        //Imprime informações de debug
        imprimeEstadosFilosofos();
        imprimeGarfos();
        imprimeTentativas();
        contador();
    }

    //Metodo sincronizado returningGarfos();
    //Marca ambos os garfos como livres novamente
    public synchronized void returningGarfos(int filosofo) {
        garfos[garfoEsquerdo(filosofo)] = true;
        garfos[garfoDireito(filosofo)] = true;

        if (filosofos[aEsquerda(filosofo)] == FOME ||
                filosofos[aDireita(filosofo)] == FOME) {
            notifyAll();  // Acorda TODAS as threads em wait()
        }

        //filosofos voltam a pensar
        filosofos[filosofo] = PENSANDO;

        //Imprime as informações de debug
        imprimeEstadosFilosofos();
        imprimeGarfos();
        imprimeTentativas();
    }

    //Métodos de navegação (Mesa Circular)

    //Mesa Circular: filosofo 0 e filosofo 4 são vizinhos

    public int aDireita(int filosofo) {
        int direito;
        if (filosofo == ULTIMO_FILOSOFO) {
            direito = PRIMEIRO_FILOSOFO;  // Filósofo 4 → 0
        } else {
            direito = filosofo + 1;        // 0→1, 1→2, 2→3, 3→4
        }
        return direito;
    }

    public int aEsquerda(int filosofo) {
        int esquerdo;
        if (filosofo == PRIMEIRO_FILOSOFO) {
            esquerdo = ULTIMO_FILOSOFO;    // Filósofo 0 → 4
        } else {
            esquerdo = filosofo - 1;       // 4→3, 3→2, 2→1, 1→0
        }
        return esquerdo;
    }

    //Método de mapeamento dos garfos

    public int garfoEsquerdo(int filosofo) {
        return filosofo; //Garfo esquerdo = mesmo indice do filosofo
    }

    public int garfoDireito(int filosofo) {
        int garfoDireito;
        if (filosofo == ULTIMO_FILOSOFO) {
            garfoDireito = 0; //Filósofo 4 usa o garfo 0 a direita
        } else {
            garfoDireito = filosofo + 1; //Próximo garfo
        }
        return garfoDireito;
    }

    //Filosofo 0: garfos 0 (esquerdo) e 1 (direito)
    //Filosofo 1: garfos 1 (esquerdo) e 2 (direito)
    //Filosofo 2: garfos 2 (esquerdo) e 3 (direito)
    //Filosofo 3: garfos 3 (esquerdo) e 4 (direito)
    //Filosofo 4: garfos 4 (esquerdo) e 0 (direito) <- circular

    //METODOS MONITORAMENTO

    //Filósofos = [ PENSANDO COMENDO FOME COMENDO PENSANDO ]
    public void imprimeEstadosFilosofos() {
        String texto = "*";
        System.out.print("Filósofos = [ ");

        for (int i = 0; i < NR_FILOSOFO; i++) {
            switch (filosofos[i]) {
                case PENSANDO:
                    texto = "PENSANDO";
                    break;
                case FOME:
                    texto = "FOME";
                    break;
                case COMENDO:
                    texto = "COMENDO";
                    break;
            }
            System.out.print(texto + " ");
        }
        System.out.println("]");
    }

    //Mostra se cada garfo está livre ou ocupado
    //Garfos = [ LIVRE OCUPADO OCUPADO LIVRE OCUPADO ]
    public void imprimeGarfos() {
        String garfo = "*";
        System.out.print("Garfos = [ ");
        for (int i = 0; i < NR_FILOSOFO; i++) {
            if (garfos[i]) {
                garfo = "LIVRE";
            } else {
                garfo = "OCUPADO";
            }
            System.out.print(garfo + " ");
        }
        System.out.println("]");
    }

    public void imprimeTentativas() {
        System.out.print("Tentou comer = [ ");
        for (int i = 0; i < NR_FILOSOFO; i++) {
            System.out.print(tentativas[i] + " ");
        }
        System.out.println("]");
    }

    //Utilidade: Detecta starvation - se algum filósofo nunca come, há um problema
    public void contador() {
        //Incrementa contador de quem está comendo
        for (int i = 0; i < 5; i++) {
            if (filosofos[i] == COMENDO) {
                contador[i]++;
            }
        }
        //Imprime estatísticas
        for (int i = 0; i < 5; i++) {
            System.out.format("O filosofo %d comeu %d vezes \n", i, contador[i]);
        }
    }
}