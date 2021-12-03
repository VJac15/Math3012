/*////////////////////////////////////////////////
// Author: Evan Douglass Date: 12/2/2021        //
//                                              //
// Class: MATH-3012     Professor: Yaofeng Su   //
//                                              //
// Desc: My version of Google's Breadth-First   //
// Search algorithm                             //
////////////////////////////////////////////////*/

import java.util.*;

public class GoogleBFS{
    public static void main(String[] args){
        

        //initializing algorithm
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        a.setAdjacent(new ArrayList<Node>(){{
            add(b);
            add(c);
            add(d);
            add(e);
        }});
        b.setAdjacent(new ArrayList<Node>(){{
            add(a);
            add(c);
            add(d);
            add(e);
        }});
        c.setAdjacent(new ArrayList<Node>(){{
            add(a);
            add(b);
            add(e);
            add(f);
        }});
        d.setAdjacent(new ArrayList<Node>(){{
            add(a);
            add(b);
            add(e);
            add(f);
        }});
        e.setAdjacent(new ArrayList<Node>(){{
            add(a);
            add(b);
            add(c);
            add(d);
        }});
        f.setAdjacent(new ArrayList<Node>(){{
            add(c);
            add(d);
        }});
        Node[] g = new Node[]{a,b,c,d,e,f};
        ArrayList<Node> q = new ArrayList<Node>(0); //creating queue
        //done initializing

        long startTime = System.nanoTime();

        runAlgorithm(a, q, g); //a was arbitrarily chosen as a starting point-- this algorithm will work with any node on the grpah
        System.out.println(getPath(f)); //f was arbitrarily chosen as a destination on this least-spanning tree

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

    private static void runAlgorithm(Node root, ArrayList<Node> queue, Node[] graph){
        for (Node node:graph){
            node.setVisited(false);
        }
        queue.add(root); //arbitrary starting point
        root.markVisited();
        while (queue.size() != 0){ //traversing graph
            ArrayList<Node> adjacentNodes = queue.get(0).getAdjacent(); //find all neighbors
            for (int i = 0; i < adjacentNodes.size(); i++){
                Node node = adjacentNodes.get(i);
                if (!node.getVisited()){ //if neighbor hasn't been visited...
                    queue.add(node); //add to queue
                    node.setPrevious(queue.get(0)); //set its previous node to be current node in queue-- allows for backtracking
                    node.markVisited(); //mark as visited
                }
            }
            queue.remove(0); //remove from queue
        }
        //System.out.println("Arigatou, minnasan!"); //bugtesting
    }

    private static String getPath(Node end){
        Node fin = end;
        String path = "";
        while (fin != null){
            path += fin.toString() + " ";
            fin = fin.getPrevious();
        }
        return "\n/////////////////////////\n" + "Path:\n" + path + "\n/////////////////////////\n";
    }
}

class Node{
    boolean beenVisited;
    ArrayList<Node> adjacent;
    String name;
    Node previous;

    public Node(boolean beenVisited, ArrayList<Node> adjacent, String name, Node previous){
        this.beenVisited = beenVisited;
        this.adjacent = adjacent;
        this.name = name;
        this.previous = previous;
    }

    public Node(String name){
        this.beenVisited = false;
        this.adjacent = new ArrayList<Node>(0);
        this.name = name;
        this.previous = null;
    }

    public void markVisited(){
        this.beenVisited = true;
    }

    public void setVisited(boolean beenVisited){
        this.beenVisited = beenVisited;
    }

    public boolean getVisited(){
        return this.beenVisited;
    }

    public void setAdjacent(ArrayList<Node> adjacent){
        this.adjacent = adjacent;
    }

    public ArrayList<Node> getAdjacent(){
        return this.adjacent;
    }

    public Node getPrevious(){
        return this.previous;
    }

    public void setPrevious(Node previous){
        this.previous = previous;
    }

    public String toString(){
        return this.name;
    }
}