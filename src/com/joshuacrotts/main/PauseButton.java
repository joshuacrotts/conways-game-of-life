package com.joshuacrotts.main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class PauseButton extends JButton implements ActionListener {

  private final GameOfLife gameOfLife;
  
  public PauseButton(GameOfLife gol) {
    super("PAUSE");
    this.gameOfLife = gol;
    
    super.addActionListener(this);
    super.setEnabled(false);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    this.gameOfLife.setPaused(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
