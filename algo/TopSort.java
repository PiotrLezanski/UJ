import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class TopSort {
    public static void main(String[] args) {

        Graph graph = new Graph();
        // graph.addVertex('A'); // 0
        // graph.addVertex('B'); // 1
        // graph.addVertex('C'); // 2
        // graph.addVertex('D'); // 3
        // graph.addVertex('E'); // 4
        // graph.addVertex('F'); // 5
        // graph.addVertex('G'); // 6
        // graph.addEdge(0, 1);
        // graph.addEdge(1, 2);
        // graph.addEdge(3, 0);
        // graph.addEdge(3, 1);
        // graph.addEdge(4, 5);
        // graph.addEdge(6, 5);
        // graph.addEdge(5, 2);

        graph.addVertex('A'); // 0
        graph.addVertex('B'); // 1
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);

        graph.topSort();
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
    
    public void topSort() {
        Stack<Vertex> stack = new Stack<>();
        for(int i=0; i<curr_verts; ++i) {
            if( !vertexList[i].wasVisited ) {
                DFS(i, stack);
            }
        }

        while( !stack.isEmpty() ) {
            System.out.print(stack.pop().label + " ");
        }
    }

    public static void DFS(int s, Stack<Vertex> stack) {
        vertexList[s].wasVisited = true;
        int tmp;
        while( (tmp = findUnvisitedN(s)) != -1 ) {
            DFS(tmp, stack);
        }
        stack.push(vertexList[s]);
    }

    public void addVertex(char l) {
        vertexList[curr_verts++] = new Vertex(l);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        // adjMat[end][start] = 1; // in case of undirected graph
    }

    public static int findUnvisitedN(int s) {
        for(int i=0; i<curr_verts; ++i) {
            if( adjMat[s][i] == 1 && vertexList[i].wasVisited == false ) {
                return i;
            }
        }
        return -1;
    }
}


