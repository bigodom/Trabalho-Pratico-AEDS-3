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
        
        String mazes[] = new String[6];
        mazes[0] = "files/maze3.txt";
        mazes[1] = "files/maze10.txt";
        mazes[2] = "files/maze20.txt";
        mazes[3] = "files/maze30.txt";
        mazes[4] = "files/maze40.txt";
        mazes[5] = "files/maze50.txt";

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
                	System.out.println("digite o numero do arquivo que deseja: ");
                	System.out.println("1 - maze3.txt ");
                	System.out.println("2 - maze10.txt ");
                	System.out.println("3 - maze20.txt ");
                	System.out.println("4 - maze30.txt ");
                	System.out.println("5 - maze40.txt ");
                	System.out.println("6 - maze50.txt ");
                	int lab = sc.nextInt();

                	switch(lab) {
                	case 1:

	                    long startTimeMaze = System.currentTimeMillis();
	                    Maze labirinto = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze = System.currentTimeMillis() - startTimeMaze;
	                    System.out.println("O tempo total foi de " + totalTimeMaze / 1000 + " segundos.");
	
	                    break;
                	case 2:

	                    long startTimeMaze2 = System.currentTimeMillis();
	                    Maze labirinto2 = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze2 = System.currentTimeMillis() - startTimeMaze2;
	                    System.out.println("O tempo total foi de " + totalTimeMaze2 / 1000 + " segundos.");
	
	                    break;
                	case 3:

	                    long startTimeMaze3 = System.currentTimeMillis();
	                    Maze labirinto3 = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze3 = System.currentTimeMillis() - startTimeMaze3;
	                    System.out.println("O tempo total foi de " + totalTimeMaze3 / 1000 + " segundos.");
	
	                    break;
                	case 4:

	                    long startTimeMaze4 = System.currentTimeMillis();
	                    Maze labirinto4 = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze4 = System.currentTimeMillis() - startTimeMaze4;
	                    System.out.println("O tempo total foi de " + totalTimeMaze4 / 1000 + " segundos.");
	
	                    break;
                	case 5:

	                    long startTimeMaze5 = System.currentTimeMillis();
	                    Maze labirinto5 = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze5 = System.currentTimeMillis() - startTimeMaze5;
	                    System.out.println("O tempo total foi de " + totalTimeMaze5 / 1000 + " segundos.");
	
	                    break;
                	case 6:

	                    long startTimeMaze6 = System.currentTimeMillis();
	                    Maze labirinto6 = new Maze(mazes[lab - 1]);
	                    
	                    float totalTimeMaze6 = System.currentTimeMillis() - startTimeMaze6;
	                    System.out.println("O tempo total foi de " + totalTimeMaze6 / 1000 + " segundos.");
	
	                    break;
                	}
                	
                    break;
                case 3:
                	System.out.println("Encerrando o programa!");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        }

        sc.close();
    }
}