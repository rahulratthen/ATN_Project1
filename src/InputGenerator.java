
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rahulrdhanendran
 */
public class InputGenerator {
    public int nodes;
    public double [][]bij;
    public double [][]aij;
    public int k;
    
    private InputGenerator(int param)
    {
        nodes = 40;
        k = param;
        bij = new double[nodes][nodes];
        aij = new double[nodes][nodes];
        
        for(int i=0;i<nodes;i++)
        {
            for(int j=0;j<nodes;j++)
            {
                bij[i][j] = 0;
                aij[i][j] = 0;
            }
        }
        generate();
    }
    
    public void generate(){
        //generate bij
        Random rand = new Random();

        for(int i=0;i<nodes;i++)
        {
            for(int j=0;j<nodes;j++)
            {
                bij[i][j] = rand.nextInt(4);
            }
        }
        
        //generate aij
        for(int i=0;i<nodes;i++)
        {
            HashSet randIndices = getRandomIndices();
            for(int j=0;j<nodes;j++)
            {
                if(randIndices.contains(new Integer(j)))
                    aij[i][j] = 1;
                else
                    aij[i][j] = 300;
            }
        }
        
    }
    
    
    public HashSet getRandomIndices()
    {
        HashSet<Integer> hs = new HashSet<Integer>();
        Random rand = new Random();
        do
        {
            int index = rand.nextInt(40);
            if(!hs.contains(new Integer(index)))
                hs.add(new Integer(index));
        }while(hs.size()<k);
        return hs;
    }

    public static void main(String args[])
    {
        InputGenerator ip = new InputGenerator(3);
    }
}
