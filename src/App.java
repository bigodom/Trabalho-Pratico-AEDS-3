import java.io.IOException;
import java.util.Scanner;

class App {
    public static void main(String[] args) throws IOException {
        String arquivos[] = new String[6];
        arquivos[0] = "src/toy.txt";
        arquivos[1] = "src/rg300_4730.txt";
        arquivos[2] = "src/rome99c.txt";
        arquivos[3] = "src/facebook_combined.txt";
        arquivos[4] = "src/USA-road-dt.DC.txt";
        arquivos[5] = "src/USA-road-dt.NY.txt";

        Scanner sc = new Scanner(System.in);

        int button = 0;

        while (button != 3) {
            System.out.println();
            System.out.println("Informe a tarefa:");
            System.out.println("1- Caminho Minimo");
            System.out.println("2- Labirinto");
            System.out.println("3- Sair");
            button = sc.nextInt();

            switch (button) {
                case 1:
                    System.out.println("Escolha o arquivo para testar:");
                    System.out.println("0 - toy.txt");
                    System.out.println("1 - rg300_4730.txt");
                    System.out.println("2 - rome99c.txt");
                    System.out.println("3 - facebook_combined.txt");
                    System.out.println("4 - USA-road-dt.DC.txt");
                    System.out.println("5 - USA-road-dt.NY.txt");
                    int file = sc.nextInt();
                    System.out.println("Insira a origem:");
                    int s = sc.nextInt();
                    System.out.println("Insira o destino:");
                    int d = sc.nextInt();
                    System.out.println("Qual algoritmo de busca ?");
                    System.out.println("1 - Dijkstra ?");
                    System.out.println("2 - Bellman Ford");
                    System.out.println("3 - Bellman Ford Melhorado");
                    System.out.println("4 - FloyWarshall");
                    int alg = sc.nextInt();
                 

                    switch(alg){
                    	case 1:
	                        GraphList g1 = new GraphList(arquivos[file]);
		                    System.out.println();
		                    System.out.println("[Dijkstra]");
		                    System.out.println();
		                    long startTime = System.currentTimeMillis();
		                    g1.dijkstra(s, d);
		                    float totalTime = System.currentTimeMillis() - startTime;
		                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");
		                    break ;
                    	case 2:
		                    GraphList g3 = new GraphList(arquivos[file]);
		                    System.out.println();
		                    System.out.println("[Bellman Ford]");
		                    System.out.println();
		                    startTime = System.currentTimeMillis();
		                    g3.bellmanFord(s, d);
		                    totalTime = System.currentTimeMillis() - startTime;
		                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");
		                    break; 
	                    case 3:
		                    GraphList g4 = new GraphList(arquivos[file]);
		                    System.out.println();
		                    System.out.println("[Bellman Ford Melhorado]");
		                    System.out.println();
		                    startTime = System.currentTimeMillis();
		                    g4.bellmanFordMelhorado(s, d);
		                    totalTime = System.currentTimeMillis() - startTime;
		                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");
		                    break;
	                    case 4:
		                    GraphMatrix g2 = new GraphMatrix(arquivos[file]);
		                    System.out.println();
		                    System.out.println("[Floyd Warshall]");
		                    System.out.println();
		                    startTime = System.currentTimeMillis();
		                    g2.floydWarshall(s, d);
		                    totalTime = System.currentTimeMillis() - startTime;
		                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");
		                    break;
	                	}
	                    break;
	                case 2:
	                    System.out.println("Informe o nome do arquivo:(Exemplo: files/maze/toy.txt)");
	                    String filename = sc.next();
	                    long startTimeLabirinto = System.currentTimeMillis();
	                    //Labirinto labirinto = new Labirinto(filename);
	                    float totalTimeLabirinto = System.currentTimeMillis() - startTimeLabirinto;
	                    System.out.println("O tempo total foi de " + totalTimeLabirinto / 1000 + " segundos.");
	
	                    break;
	                case 3:
	                    break;
	
	                default:
	                    System.out.println("Opcao invalida!");
            }

        }

        sc.close();
    }
}