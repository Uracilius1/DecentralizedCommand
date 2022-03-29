/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;
import main.GamePanel;
/**
 *
 * @author Mabiyev
 */
public class main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Decentralized command");
        
        
        GamePanel gp = new GamePanel();
        window.add(gp);
        
        window.pack();
        gp.startGameThread();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
}
