package com.joshuacrotts.main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ResumeButton extends JButton implements ActionListener {

  private final GameOfLife gameOfLife;
  
  public ResumeButton(GameOfLife gol) {
    super("RESUME");
    this.gameOfLife = gol;

    super.addActionListener(this);
    super.setEnabled(true);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    this.gameOfLife.setPaused(false);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
