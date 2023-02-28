import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class AllPairShortestPath {
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

        System.out.println(graph.average_length());

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
    
    float average_length() {
        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                if( adjMat[i][j] == 0 ) {
                    adjMat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int k=0; k<curr_verts; ++k) {
            for(int i=0; i<curr_verts; ++i) {
                for(int j=0; j<curr_verts; ++j) {
                    if( adjMat[i][k] + adjMat[k][j] < adjMat[i][j] ) {
                        adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
                    }
                }
            }
        }

        int sum = 0;
        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                if( i != j ) {
                    sum += adjMat[i][j];
                }
            }
        }
        return sum/((curr_verts * curr_verts) - curr_verts);
    }
}


