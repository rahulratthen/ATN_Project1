/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahulrdhanendran
 */
public class MainClass {
    InputGenerator inputs;
    Graph graph;
    
    public MainClass(int kval)
    {
        inputs = new InputGenerator(kval);
        graph = new Graph(); 
        createGraph();
    }
    
    public void createGraph()
    {
        //adding all the vertices to the graph
        for(int i=0;i<inputs.nodes;i++)
        {
            Vertex v = new Vertex(i);
            v.adjacencies = new Edge[inputs.nodes];
            graph.vertices.add(v);
        }
        
        //add all the edges and their weights
        for(int i=0;i<inputs.nodes;i++)
        {
            for(int j=0;j<inputs.nodes;j++)
            {
                graph.vertices.get(i).adjacencies[j] = new Edge(graph.vertices.get(j),inputs.aij[i][j]);
            }
        }
        
    }
    
    public int getCost() 
    {
        int i, j,cost = 0;
        for (i = 0; i < inputs.nodes; i++) 
        {
            for (j = 0; j < inputs.nodes; j++) 
            {
                cost += inputs.aij[i][j];
            }
        }
        return cost;
    }
    
    public static void main(String args[])
    {
        MainClass m = new MainClass(3);
        System.out.println("Cost : "+m.getCost());
    }
}
