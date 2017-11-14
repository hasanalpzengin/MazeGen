/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegeneration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author hasalp
 */
public class MazeGeneration extends JFrame {

    static int width = 700;
    static int height = 400;
    static int grid = 20;
    Operator op = new Operator();
    ArrayList<Cells> cells = op.createPath(op.createCells());
    
    public MazeGeneration(){
        this.setSize(width+100, height+100);
        this.setTitle("Maze Generation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.WHITE);
        this.setOpacity(1f);
    }
    
    public static void main(String[] args) {
        new MazeGeneration().setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        op.drawCells(cells , g);
    }
    
    
}
