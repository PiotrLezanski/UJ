import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class minSpanTree {
    public static void main(String[] args) {
        
        // Graph graph = new Graph();
        // graph.addVertex('A'); // 0
        // graph.addVertex('B'); // 1
        // graph.addVertex('C'); // 2
        // graph.addVertex('D'); // 3
        // graph.addVertex('E'); // 4
        // graph.addVertex('F'); // 5
        // graph.addVertex('G'); // 6
        // graph.addVertex('H'); // 7
        // graph.addVertex('I'); // 8
        // graph.addEdge(0, 1);
        // graph.addEdge(1, 2);
        // graph.addEdge(2, 3);
        // graph.addEdge(3, 4);
        // graph.addEdge(4, 5);
        // graph.addEdge(5, 6);
        // graph.addEdge(6, 8);
        // graph.addEdge(6, 7);
        // graph.addEdge(8, 7);
        // graph.addEdge(2, 8);
        // graph.addEdge(7, 1);
        // graph.addEdge(5, 2);
        // graph.addEdge(5, 3);
        // graph.addEdge(0, 7);

        Graph graph = new Graph();
        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addVertex('G'); // 6
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 0);
        graph.addEdge(3, 1);
        graph.addEdge(4, 5);
        graph.addEdge(6, 5);
        graph.addEdge(5, 2);
        
        graph.addEdge(2, 6); // makes cicle

        graph.MST(0);
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
    
    public void MST(int s) {
        vertexList[s].wasVisited = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        int u;
        int v;
        while( !stack.isEmpty() ) {
            u = stack.peek();
            v = findUnvisitedN(u);
            if( v == -1 ) {
                stack.pop();
            }
            else {
                vertexList[v].wasVisited = true;
                vertexList[u].wasVisited = true;
                stack.push(v);
                displayEdge(u, v);
            }
        }
    }

    public static int findUnvisitedN(int s) {
        for(int i=0; i<curr_verts; ++i) {
            if( adjMat[s][i] == 1 && vertexList[i].wasVisited == false ) {
                return i;
            }
        }
        return -1;
    }

    public void addVertex(char l) {
        vertexList[curr_verts++] = new Vertex(l);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1; // in case of undirected graph
    }

    public static void displayEdge(int x, int y) {
        System.out.print("(" + x + ", " + y + ") ");
    }

    public void clearVisitations() {
        for(int i=0; i<curr_verts; i++) {
            vertexList[i].wasVisited = false;
        }
    }
}


