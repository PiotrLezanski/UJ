import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class shortestBFS {
    public static void main(String[] args) {
        
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
        
        // graph.addEdge(2, 6); // makes cicle

        System.out.println(graph.findShortest(0, 3));

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

class Node {
    int index;
    int distance;
    Node() {
        index = 0;
        distance = 0;
    }
    Node(int i, int d) {
        index = i;
        distance = d;
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
    
    public int findShortest(int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        Node curr;
        int tmp;
        while( !queue.isEmpty() ) {
            curr = queue.poll();
            vertexList[curr.index].wasVisited = true;
            if( curr.index == end ) return curr.distance;
            while( (tmp = findUnvisitedN(curr.index)) != -1 ) {
                vertexList[tmp].wasVisited = true;
                queue.add(new Node(tmp, curr.distance + 1));
            }
        }
        return -1;
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


