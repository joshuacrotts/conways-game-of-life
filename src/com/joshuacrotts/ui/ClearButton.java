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
public class ClearButton extends JButton implements ActionListener {

  private final GameOfLife gameOfLife;
  
  public ClearButton(GameOfLife gol) {
    super("CLEAR");
    this.gameOfLife = gol;
    
    super.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    this.gameOfLife.getParentPanel().getGridPanel().clearGrid();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}