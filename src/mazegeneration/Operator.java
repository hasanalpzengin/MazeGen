/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegeneration;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author hasalp
 */
public class Operator {
    
    public ArrayList<Cells> createCells(){
        int width = MazeGeneration.width;
        int height = MazeGeneration.height;
        int grid = MazeGeneration.grid;
        int index = 0;
        ArrayList<Cells> cells = new ArrayList<>();
        for(int j = 0; j<Math.floor(height/grid); j++){
            for(int i = 0; i<Math.floor(width/grid); i++){
                cells.add(new Cells(i,j));
                cells.get(index).index=index;
                index++;
            }
        }
        
        return cells;
    }
    
    public void drawCells(ArrayList<Cells> cells,Graphics g){
        int grid = MazeGeneration.grid;
        int paddingtop = 80;
        int paddingleft = 50;
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawString("Backtracking Algorithm Maze Generator", MazeGeneration.width/2, paddingtop-20);
        
        for(int i=0; i<cells.size(); i++){
            //draw up line
            if(cells.get(i).walls[0]){
                g2d.drawLine(cells.get(i).coordinate_i*grid+paddingleft, cells.get(i).coordinate_j*grid+paddingtop, cells.get(i).coordinate_i*grid+grid+paddingleft, cells.get(i).coordinate_j*grid+paddingtop);
            }
            //draw down line
            if(cells.get(i).walls[2]){
                g2d.drawLine(cells.get(i).coordinate_i*grid+paddingleft, cells.get(i).coordinate_j*grid+grid+paddingtop, cells.get(i).coordinate_i*grid+grid+paddingleft, cells.get(i).coordinate_j*grid+grid+paddingtop);
            }
            //draw left line
            if(cells.get(i).walls[3]){
                g2d.drawLine(cells.get(i).coordinate_i*grid+paddingleft, cells.get(i).coordinate_j*grid+paddingtop, cells.get(i).coordinate_i*grid+paddingleft, cells.get(i).coordinate_j*grid+grid+paddingtop);
            }
            //draw right line
            if(cells.get(i).walls[1]){
                g2d.drawLine(cells.get(i).coordinate_i*grid+paddingleft+grid, cells.get(i).coordinate_j*grid+paddingtop, cells.get(i).coordinate_i*grid+grid+paddingleft, cells.get(i).coordinate_j*grid+grid+paddingtop);
            }
        }
    }
    
    public ArrayList<Cells> createPath(ArrayList<Cells> cells){
        Cells current = cells.get(0);
        Stack<Cells> stack = new Stack<>();
        System.out.println("joined");
        
        int move = 1;
        
        while(move<cells.size()){
            current.visited=true;
            Cells next = current.checkNeighbors(cells);
            if(next != null){
                move++;
                int difference_i = current.coordinate_i-next.coordinate_i;
                int difference_j = current.coordinate_j-next.coordinate_j;
                if(difference_i==-1){
                    current.walls[1]=false;
                    next.walls[3]=false;
                }else if(difference_j==-1){
                    current.walls[2]=false;
                    next.walls[0]=false;
                }else if(difference_i==1){
                    current.walls[3]=false;
                    next.walls[1]=false;
                }else{
                    current.walls[0]=false;
                    next.walls[2]=false;
                }
                //sync to main array
                cells.set(next.index, next);
                cells.set(current.index, current);
                //push current cell to stack
                stack.push(current);
                //go to next cell
                current = next;
            }else if(stack.size()>0){
                current = stack.pop();
            }
        }
        
        //remove walls at start and end cell
        cells.get(0).walls[3]=false;
        cells.get(cells.size()-1).walls[1]=false;
        
        return cells;
    }
    
}
