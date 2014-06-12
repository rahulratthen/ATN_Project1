
import java.util.List;

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
    double [][]capacity;
    Graph graph;
    double optimalCost, density;
    
    public MainClass(int kval)
    {
        inputs = new InputGenerator(kval);
        graph = new Graph();
        optimalCost = 0;
        density = 0;
        capacity = new double[inputs.nodes][inputs.nodes];
        
        for(int i=0; i<inputs.nodes; i++)
        {
            for(int j=0;j<inputs.nodes; j++)
            {
                capacity[i][j] = 0;
            }
        }
        
    }
    
    public void createGraph()
    {
        //adding all the vertices to the graph
        graph = new Graph();
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
    
    public void calculateCapacity()
    {
        for (int i = 0; i < inputs.nodes; i++) 
        {
            createGraph();
            //System.out.println("*************************+"+i);
            Dijkstra ds = new Dijkstra();
            ds.computePaths(graph.vertices.get(i));
            for (Vertex v : graph.vertices)
            {
                List<Vertex> path = ds.getShortestPathTo(v);
                for(int k=0;k<path.size()-1;k++)
                {
                    capacity[Integer.parseInt(path.get(k).name)][Integer.parseInt(path.get(k+1).name)] += inputs.bij[i][Integer.parseInt(v.name)]; 
                    optimalCost += inputs.bij[i][Integer.parseInt(v.name)] * inputs.aij[Integer.parseInt(path.get(k).name)][Integer.parseInt(path.get(k+1).name)];
                }
            }
        }
        
    }
    
    public void printCapacityCost()
    {
        int i, j,cost = 0;
        for (i = 0; i < inputs.nodes; i++) 
        {
            for (j = 0; j < inputs.nodes; j++) 
            {
                cost += capacity[i][j];
            }
            
        }
        System.out.println("Capacity cost: "+cost);
    }
    
    public int printCost() 
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
    
    public void calculateDensity()
    {
        int nonZeroCapacity = 0;
        for(int i=0;i<inputs.nodes;i++)
        {
            for(int j=0;j<inputs.nodes;j++)
            {
                if(capacity[i][j]!=0)
                    nonZeroCapacity++;
            }
        }
        density = (double)nonZeroCapacity/(inputs.nodes*(inputs.nodes-1));
    }
    
    public static void main(String args[])
    {
        for(int k=3;k<=15;k++){
        MainClass m = new MainClass(k);
        m.calculateCapacity();
        System.out.println("k : "+k);
        System.out.println("Cost : "+m.printCost());
        //m.printCapacityCost();
        System.out.println("Optimal cost: "+m.optimalCost);
        m.calculateDensity();
        System.out.println("Density :"+m.density);
    
        }
    }
}
