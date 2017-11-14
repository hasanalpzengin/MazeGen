/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author hasalp
 */
public class Cells {
    
    Integer coordinate_i;
    Integer coordinate_j;
    boolean[] walls = new boolean[4];
    boolean visited = false;
    Integer index;
    
    public Cells(int coordinate_i,int coordinate_j){
        this.coordinate_i = coordinate_i;
        this.coordinate_j = coordinate_j;  
        Arrays.fill(walls, true);
    }
    
    public Cells checkNeighbors(ArrayList<Cells> cells){
        ArrayList<Cells> neighbors = new ArrayList<>();
        
        if(this.coordinate_j>0){
            Cells top = cells.get(findindex(this.coordinate_i,this.coordinate_j-1));
            if(!top.visited){
                neighbors.add(top);
            }
        }
        if(this.coordinate_i>0){
            Cells left = cells.get(findindex(this.coordinate_i-1,this.coordinate_j));
            if(!left.visited){
                neighbors.add(left);
            }
        }
        if(this.coordinate_i<Math.floor(MazeGeneration.width/MazeGeneration.grid)-1){
            Cells right = cells.get(findindex(this.coordinate_i+1,this.coordinate_j));
            if(!right.visited){
                neighbors.add(right);
            }
        }
        if(this.coordinate_j<Math.floor(MazeGeneration.height/MazeGeneration.grid)-1){
            Cells bottom = cells.get(findindex(this.coordinate_i,this.coordinate_j+1));
            if(!bottom.visited){
                neighbors.add(bottom);
            }
        }
        
        if(neighbors.size()>0){
            Random rand = new Random();
            int randomCell = rand.nextInt(neighbors.size());
            return neighbors.get(randomCell);
        }else{
            return null;
        }
    }
    
    public int findindex(int i,int j){
        if(i<0 || j<0 || i>Math.floor(MazeGeneration.width/MazeGeneration.grid) || j>Math.floor(MazeGeneration.height/MazeGeneration.grid)){
            return -1;
        }
        
        return (int) (i+j*Math.floor(MazeGeneration.width/MazeGeneration.grid));
    }
    
}
