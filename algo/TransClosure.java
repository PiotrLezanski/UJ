import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Test {
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

        graph.transitiveClosure();

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
    
    public void transitiveClosure() {
        int[][] reach = new int[curr_verts][curr_verts];
        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                reach[i][j] = adjMat[i][j];
            }
        }

        for(int k=0; k<curr_verts; ++k) {
            for(int i=0; i<curr_verts; ++i) {
                for(int j=0; j<curr_verts; ++j) {
                    if( reach[i][j] == 0 ) {
                        reach[i][j] = (reach[i][k] == 1 && reach[k][j] == 1) ? 1 : 0;
                    }
                }
            }
        }

        printArr(reach);
    }

    public void printArr(int[][] arr) {
        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void addVertex(char l) {
        vertexList[curr_verts++] = new Vertex(l);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        // adjMat[end][start] = 1; // in case of undirected graph
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


