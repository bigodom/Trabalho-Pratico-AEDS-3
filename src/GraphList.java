import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphList {

    private int countNodes;
    private int countEdges;
    private ArrayList<ArrayList<Edge>> adjList;
    private ArrayList<Edge> edgeList;
    private static final int INF = 999999;

    public GraphList(int countNodes) {
        this.countNodes = countNodes;
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
        edgeList = new ArrayList<>();
    }

    public GraphList(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        // Create and fill adjList with read edges
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
        edgeList = new ArrayList<>();
        // Adds one edge at time
        for (int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();
    }

    public void addEdge(int source, int sink, int weight) {
        if (source < 0 || source > this.countNodes - 1
                || sink < 0 || sink > this.countNodes - 1 || weight <= 0) {
            System.err.println("Invalid edge: " + source + sink + weight);
            return;
        }
        adjList.get(source).add(new Edge(source, sink, weight));
        edgeList.add(new Edge(source, sink, weight));
        this.countEdges++;
    }

    public void addEdgeUnoriented(int source, int sink, int weight) {
        addEdge(source, sink, weight);
        addEdge(sink, source, weight);
    }

    public int degree(int u) {
        if (u < 0 || u > this.countNodes - 1)
            System.err.println("Invalid node: " + u);
        return this.adjList.get(u).size();
    }

    public int highestDegree() {
        int highest = 0;
        for (int u = 0; u < this.adjList.size(); ++u) {
            int degreeNodeU = this.degree(u);
            if (highest < degreeNodeU)
                highest = degreeNodeU;
        }
        return highest;
    }

    public int lowestDegree() {
        int lowest = this.countNodes;
        for (int u = 0; u < this.adjList.size(); ++u) {
            int degreeNodeU = this.degree(u);
            if (lowest > degreeNodeU)
                lowest = degreeNodeU;
        }
        return lowest;
    }

    public GraphList complement() {
        GraphList g2 = new GraphList(this.countNodes);
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int v = 0; v < this.countNodes; ++v) {
                boolean addEdgeUV = true;
                for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                    int v2 = this.adjList.get(u).get(idx).getSink();
                    if (v2 == v) { 
                        addEdgeUV = false;
                        break;
                    }
                }
                if (addEdgeUV && u != v) {
                    g2.addEdge(u, v, 1); 
                }
            }
        }
        return g2;
    }

    public float density() {
        return (float) this.countEdges / (this.countNodes * (this.countNodes - 1));
    }

    public boolean subgraph(GraphList g2) {
        if (g2.countNodes > this.countNodes || g2.countEdges > this.countEdges)
            return false;
        for (int u = 0; u < g2.adjList.size(); ++u) {
            boolean foundEdge = false;
            for (int idx = 0; idx < g2.adjList.get(u).size(); ++idx) {
                int v = g2.adjList.get(u).get(idx).getSink();
                
                for (int idx2 = 0; idx2 < this.adjList.get(u).size(); ++idx2) {
                    int v2 = this.adjList.get(u).get(idx2).getSink();
                    if (v == v2) { 
                        foundEdge = true;
                        break;
                    }
                }
                if (!foundEdge)
                    return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> bfs(int s) {
       
        return new ArrayList<>();
    }

    public ArrayList<Integer> dfs(int s) {
      
        return new ArrayList<>();
    }

    public boolean connected() {
        
        return false;
    }

    public boolean isOriented() {
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                boolean hasEdgeVU = false;
                for (int idx2 = 0; idx2 < this.adjList.get(v).size(); ++idx2) {
                    int u2 = this.adjList.get(v).get(idx2).getSink();
                    if (u == u2) {
                        hasEdgeVU = true;
                        break;
                    }
                }
                if (!hasEdgeVU) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Integer> dfsRec(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<>();
        dfsRecAux(s, desc, R);
        return R;
    }

    public void dfsRecAux(int u, int[] desc, ArrayList<Integer> R) {
        desc[u] = 1;
        R.add(u);
        for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
            int v = this.adjList.get(u).get(idx).getSink();
            if (desc[v] == 0) {
                dfsRecAux(v, desc, R);
            }
        }
    }

    public ArrayList<Edge> kruskal() {
        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
        int[] F = new int[this.countNodes];
       
        for (int u = 0; u < this.countNodes; ++u)
            F[u] = u;
        edgeList.sort(null);
        for (int idx = 0; idx < edgeList.size(); ++idx) {
            int u = edgeList.get(idx).getSource();
            int v = edgeList.get(idx).getSink();
            if (F[u] != F[v]) { 
                T.add(edgeList.get(idx));
               
                if (T.size() == countNodes - 1)
                    break;
                // union(u, v)
                int k = F[v];
                for (int i = 0; i < F.length; ++i) {
                    if (F[i] == k) {
                        F[i] = F[u];
                    }
                }
            }
        }
        return T;
    }

    public ArrayList<Edge> prim() {
        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
        int s = 0;
        int[] dist = new int[this.countNodes];
        int[] parent = new int[this.countNodes];
      
        ArrayList<Integer> Q = new ArrayList<Integer>(this.countNodes);
        for (int u = 0; u < this.countNodes; ++u) {
            dist[u] = INF;
            parent[u] = -1;
            Q.add(u);
        }
        dist[s] = 0;
        while (Q.size() != 0) {
            int u = -1;
            int min = INF;
            for (int idx = 0; idx < Q.size(); ++idx) {
                int i = Q.get(idx);
                if (dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }
            // Node u is gonna be connected
            Q.remove((Integer) u);
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                int w = this.adjList.get(u).get(idx).getWeight();
                if (Q.contains(v) && w < dist[v]) {
                    dist[v] = w;
                    parent[v] = u;
                }
            }
        }
        
        for (int u = 0; u < parent.length; ++u) {
            if (parent[u] != -1) {
                T.add(new Edge(u, parent[u], 1));
            }
        }
        return T;
    }

    public int getCountNodes() {
        return countNodes;
    }

    public void setCountNodes(int countNodes) {
        this.countNodes = countNodes;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public void setCountEdges(int countEdges) {
        this.countEdges = countEdges;
    }

    public ArrayList<ArrayList<Edge>> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<ArrayList<Edge>> adjList) {
        this.adjList = adjList;
    }

    public String toString() {
        String str = "";
        for (int u = 0; u < this.adjList.size(); ++u) {
            str += u + ": ";
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                str += this.adjList.get(u).get(idx) + "\t";
            }
            str += "\n";
        }
        return str;
    }
    
    public Edge existEdge(int source, int sink){
        for (Edge edge : edgeList) {
            if (edge.getSource() == source && edge.getSink() == sink){
                return edge;
            }
        }
        return null;
    }

    public int shortestDist(int[] dist, ArrayList<Integer> q){
        int shortest = dist[q.get(0)];
        int u = q.get(0);

        for (int i = 0; i < this.countNodes; i++) {
            if(q.contains(i)){
                if(dist[i] < shortest){
                    shortest = dist[i];
                    u = i;
                }
            }
        }
        return u;
    }
    public void dijkstra(int s, int t){
        long start = System.currentTimeMillis();
        int[] pred = new int[this.countNodes];
        int[] dist = new int[this.countNodes];

        for (int i = 0; i < this.countNodes; i++) {
            pred[i] = -1;
            dist[i] = INF;
        }

        dist[s] = 0;
        ArrayList<Integer> q = new ArrayList<Integer>();
        for (int i = 0; i < this.adjList.size(); i++) {
            q.add(i);
        }

        while(q.size()!=0){
            Integer u = shortestDist(dist,q);
            q.remove(u);

            for (int i = 0; i < this.adjList.get(u).size(); i++) {
                if(dist[this.adjList.get(u).get(i).getSink()] > dist[u] + this.adjList.get(u).get(i).getWeight()){
                    dist[this.adjList.get(u).get(i).getSink()] = dist[u] + this.adjList.get(u).get(i).getWeight();
                    pred[this.adjList.get(u).get(i).getSink()] = u;
                }
            }
        }

        
        System.out.printf("Dist??ncia entre %d e %d: %d   ", s,t,dist[t]);
        ArrayList<Integer> caminho = new ArrayList<Integer>();
        int aux = t;
        caminho.add(t);
        while(aux != s){
            aux = pred[aux];
            caminho.add(0,aux);
        }
        System.out.println("Caminho: " + caminho);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nTempo de execu????o: " + elapsed + " ms");
    }

    public void bellmanFord(int s, int t){
        long start = System.currentTimeMillis();
        int[] pred = new int[this.countNodes];
        int[] dist = new int[this.countNodes];

        for (int i = 0; i < this.countNodes; i++) {
            dist[i] = INF;
            pred[i] = -1;
        }
        dist[s] = 0;

        for (int i = 0; i < this.countNodes; i++) {
            for (Edge edge : edgeList) {
                if (dist[edge.getSink()] > dist[edge.getSource()] + edge.getWeight()) {
                    dist[edge.getSink()] = dist[edge.getSource()] + edge.getWeight();
                    pred[edge.getSink()] = edge.getSource();
                }
            }
        }

        
        System.out.printf("Dist??ncia entre %d e %d: %d   ", s,t,dist[t]);
        ArrayList<Integer> caminho = new ArrayList<Integer>();
        int aux = t;
        caminho.add(t);
        while(aux != s){
            aux = pred[aux];
            caminho.add(0,aux);
        }
        System.out.println("Caminho: " + caminho);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nTempo de execu????o: " + elapsed + " ms");
    }
    void bellmanFordMelhorado(int s, int t) {
        long start = System.currentTimeMillis();
        int[] dist = new int[this.countNodes];
        int[] pred = new int[this.countNodes];
        boolean trocou;

        for (int i = 0; i < this.countNodes; i++) {
            dist[i] = INF;
            pred[i] = -1;
        }
        dist[s] = 0;

        for (int i = 0; i < this.countNodes; i++) {
            trocou = false;
            for (Edge edge : edgeList) {
                if (dist[edge.getSink()] > dist[edge.getSource()] + edge.getWeight()) {
                    dist[edge.getSink()] = dist[edge.getSource()] + edge.getWeight();
                    pred[edge.getSink()] = edge.getSource();
                    trocou = true;
                }
            }
            if(!trocou){
                break;
            }
        }

        
        System.out.printf("Dist??ncia entre %d e %d: %d   ", s,t,dist[t]);
        ArrayList<Integer> caminho = new ArrayList<Integer>();
        int aux = t;
        caminho.add(t);
        while(aux != s){
            aux = pred[aux];
            caminho.add(0, aux);
        }
        System.out.println("Caminho: " + caminho);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nTempo de execu????o: " + elapsed + " ms");
    }

}
