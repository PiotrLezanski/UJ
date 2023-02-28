import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Graph {
    private ArrayList<ArrayList<Integer>> graph;
    private int verts;

    Graph(int verts) {
        this.verts = verts;
        graph = new ArrayList<>(verts);
        for(int i=0; i<verts; ++i) {
            graph.add(new ArrayList<Integer>());
        }
    }

    void addEdge(int s, int e) {
        graph.get(s).add(e);
        graph.get(e).add(s);
    }

    void removeEdge(int s, int e) {
        removeItem(graph.get(s), e);
        removeItem(graph.get(e), s);
    }

    void printEulerPath() {
        int startVert = 0;
        for(int i=0; i<verts; ++i) {
            if( graph.get(i).size() % 2 == 1 ) {
                startVert = i;
                break;
            }
        }

        printEulerPathUtil(startVert);
        System.out.println();
    }

    void printEulerPathUtil(int u) {
        for(int i=0; i<graph.get(u).size(); ++i) {
            int v = graph.get(u).get(i);
            if( isValidEdge(u, v) ) {                
                System.out.print(u + "-" + v + " ");
                removeEdge(u, v);
                printEulerPathUtil(v);
            }
        }
    }

    private void removeItem(ArrayList<Integer> list, int u) {
        for(int i=0; i<list.size(); ++i) {
            if( list.get(i) == u ) {
                list.remove(i);
            }
        }
    }

    boolean isValidEdge(int u, int v) {
        if( graph.get(u).size() == 1 ) 
            return true;

        boolean[] isVisited = new boolean[verts];
        int count1 = countReachableV(u, isVisited);
        //System.out.println(u + " " + v);
        removeEdge(u, v);
        isVisited = new boolean[verts];
        int count2 = countReachableV(u, isVisited);
        addEdge(u, v);

        return (count1 != count2) ? false : true;
    }

    int countReachableV(int u, boolean[] isVisited) {
        isVisited[u] = true;
        int count = 1;
        for(int adj : graph.get(u)) {
            if( !isVisited[adj] ) {
                count += countReachableV(adj, isVisited);
            }
        }
        return count;
    }
}

public class Test {
    public static void main(String[] args) {
       
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.printEulerPath();
 
        Graph g2 = new Graph(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        g2.printEulerPath();
 
        Graph g3 = new Graph(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(3, 2);
        g3.addEdge(3, 1);
        g3.addEdge(2, 4);
        g3.printEulerPath();

    }
}
