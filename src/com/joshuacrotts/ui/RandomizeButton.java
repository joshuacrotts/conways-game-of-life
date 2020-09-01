/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.ui;

import com.joshuacrotts.main.GameOfLife;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Joshua
 */
public class RandomizeButton extends JButton implements ActionListener {

  private final GameOfLife gameOfLife;
  
  public RandomizeButton(GameOfLife gol) {
    super("RANDOMIZE GRID");
    this.gameOfLife = gol;
    
    super.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    this.gameOfLife.getParentPanel().getGridPanel().randomizeGrid();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
