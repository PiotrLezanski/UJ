import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class BellmanAdj {
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        graph.addVertex('A'); // 0s
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addVertex('G'); // 6
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 2);
        graph.addEdge(3, 0, 9);
        graph.addEdge(3, 1, 8);
        graph.addEdge(4, 5, -1);
        graph.addEdge(6, 5, 3);
        graph.addEdge(5, 2, 5);
        
        graph.addEdge(2, 6, 7); // makes cicle

        graph.prepareAdjMat();

        graph.BellmanFord(0);

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

    public void BellmanFord(int src) {
        int[] dist = new int[curr_verts];
        for(int i=0; i<curr_verts; ++i) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }

        for (int k = 0; k < curr_verts - 1; k++) {
            for (int i = 0; i < curr_verts; i++) {
                for (int j = 0; j < curr_verts; j++) {
                    if( adjMat[i][j] != Integer.MAX_VALUE && dist[i] + adjMat[i][j] < dist[j] ) {
                        dist[j] = dist[i] + adjMat[i][j];
                    }
                }
            }
        }
        
        for(int i=0; i<curr_verts; ++i) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();

    }

    public void addVertex(char l) {
        vertexList[curr_verts++] = new Vertex(l);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        // adjMat[end][start] = 1; // in case of directed graph
    }
    
    public void prepareAdjMat() {
        for(int i=0; i<curr_verts; ++i) {
            for(int j=0; j<curr_verts; ++j) {
                if( i == j ) {
                    adjMat[i][j] = 0;
                }
                else if( adjMat[i][j] == 0 ) {
                    adjMat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }
}


