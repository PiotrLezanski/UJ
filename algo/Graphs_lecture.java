import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        
        System.out.println("A -> B -> C");
        System.out.println("|");
        System.out.println("D -> E");

        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addEdge(0, 1);    
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.BFS(0);
        System.out.println();

        graph.clearVisitations();

        graph.DFS(0);
        System.out.println();

        graph.clearVisitations();

        graph.DFS_rec(0);
        System.out.println();

    }
}

class Vertex {
    char label;
    boolean wasVisited;
    Vertex(char l) {
        label = l;
        wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTS = 20;
    private static Vertex[] vertexList;
    private static int[][] adjMat;
    private static int curr_verts;

    Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        curr_verts = 0;
        for(int i=0; i<MAX_VERTS; ++i) {
            for(int j=0; j<MAX_VERTS; ++j) {
                adjMat[i][j] = 0;
            }
        }
    }

    public void addVertex(char l) {
        vertexList[curr_verts++] = new Vertex(l);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        // adjMat[end][start] = 1; // in case of directed graph
    }
    
    public void DFS(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        int curr;
        int tmp;
        while( !stack.isEmpty() ) {
            curr = stack.pop();
            vertexList[curr].wasVisited = true;
            System.out.print(vertexList[curr].label + " ");

            while( (tmp = findUnvisitedN(curr)) != -1 ) {
                vertexList[tmp].wasVisited = true;
                stack.push(tmp);
            }
        }
    }

    public void BFS(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int curr;
        int tmp;
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            vertexList[curr].wasVisited = true;
            System.out.print(vertexList[curr].label + " ");

            while( (tmp = findUnvisitedN(curr)) != -1 ) {
                vertexList[tmp].wasVisited = true;
                queue.add(tmp);
            }
        }
    }

    public void DFS_rec(int s) {
        System.out.print(vertexList[s].label + " ");
        vertexList[s].wasVisited = true;
        int tmp;
        while( (tmp = findUnvisitedN(s)) != -1 ) {
            DFS_rec(tmp);
        }
    }

    public static int findUnvisitedN(int x) {
        for(int i=0; i<curr_verts; ++i) {
            if( adjMat[x][i] == 1 && !vertexList[i].wasVisited ) {
                return i;
            }
        }
        return -1;
    }

    public void clearVisitations() {
        for(int i=0; i<curr_verts; i++) {
            vertexList[i].wasVisited = false;
        }
    }
}


