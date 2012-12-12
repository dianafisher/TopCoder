package com.dianafisher.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Graph_AdjacencyLists {
    // Represents a graph as a collection of lists
    private int V; // node count
    private int E; // edge count
    private ArrayList<Object> nodes;
    private ArrayList<ArrayList<Integer>> adjacencyLists;

    // Create an empty graph with zero vertices and zero edges
    public Graph_AdjacencyLists() {
        adjacencyLists = new ArrayList<ArrayList<Integer>>();
        nodes = new ArrayList<Object>();
    }

    public void addNode(Object v) {
        nodes.add(v);
        ArrayList<Integer> list = new ArrayList<Integer>();
        adjacencyLists.add(list);

        // increase node count
        V++;
    }

    public void addEdge(Object v, Object w) {
        if (!nodes.contains(v)) addNode(v);
        if (!nodes.contains(w)) addNode(w);

        // append w to v's list
        int vIndex = nodes.indexOf(v);
        int wIndex = nodes.indexOf(w);

        ArrayList<Integer> vList = adjacencyLists.get(vIndex);
        vList.add(wIndex);

        E++;
    }

    private void printNodes() {
        for (Object o : nodes) {
            System.out.println(o);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
        int count = adjacencyLists.size();
        for (int i = 0; i < count; i++) {
            s.append(nodes.get(i)).append(": ");
            ArrayList<Integer> list = adjacencyLists.get(i);
            for (Integer n : list) {
                s.append(nodes.get(n)).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Graph_AdjacencyLists g = new Graph_AdjacencyLists();
        g.addEdge('F', 'B');
        g.addEdge('B', 'A');
        g.addEdge('B', 'D');
        g.addEdge('D', 'C');
        g.addEdge('D', 'E');
        g.addEdge('F', 'G');
        g.addEdge('G', 'I');
        g.addEdge('I', 'H');

//        g.printNodes();
        System.out.println("g = " + g);
        System.out.println("breadth first traversal:");
        g.bfs();
        System.out.println();
        System.out.println("depth first traversal:");
        g.dfs();
    }

    private void bfs() {
        // open list (queue FIFO)
        LinkedList<Integer> openList = new LinkedList<Integer>();
        int current;
        openList.add(0);
        while(openList.size() > 0) {
            current = openList.get(0);
            System.out.println(nodes.get(current));
            openList.remove(0);
            ArrayList<Integer> adj = adjacencyLists.get(current);
            // add neighbors to open list
            for (int neighbor : adj){
                openList.add(neighbor);
            }
        }
    }

    private void dfs() {
        // open list (LIFO stack)
        Stack<Integer> openList = new Stack<Integer>();
        int current;
        openList.push(0);
        while (openList.size() > 0) {
            current = openList.pop();
            System.out.println(nodes.get(current));
            ArrayList<Integer> adj = adjacencyLists.get(current);
            // add neighbors to open list
            for (int neighbor : adj) {
                openList.push(neighbor);
            }
        }

    }
}
