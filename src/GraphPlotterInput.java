
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahulrdhanendran
 */
public class GraphPlotterInput {
    public HashSet getRandomIndices(int i, int k)
    {
        HashSet<Integer> hs = new HashSet<Integer>();
        Random rand = new Random();
        do
        {
            int index = rand.nextInt(40);
            if(index==i)
                continue;
            if(!hs.contains(new Integer(index)))
                hs.add(new Integer(index));
        }while(hs.size()<k);
        return hs;
    }
    
    public static void main(String[] args) {
        GraphPlotterInput g = new GraphPlotterInput();
        int k=3;
        for(int i=0;i<40;i++)
        {
            HashSet randIndices = g.getRandomIndices(i,k);
            Iterator it = randIndices.iterator();
            while(it.hasNext())
            {
                System.out.print(i+"-"+it.next().toString()+",");
                
            }
                        
        }
    }
    
}
