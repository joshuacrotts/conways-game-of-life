/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author joshuacrotts
 */
public class ParentPanel extends JPanel {

  private final GameOfLife gameOfLife;
  private final GridPanel gridPanel;
  private final UIButtonPanel uiPanel;

  public ParentPanel(GameOfLife gol) {
    this.gameOfLife = gol;
    this.gridPanel = new GridPanel(this.gameOfLife);
    this.uiPanel = new UIButtonPanel(this.gameOfLife);

    super.setLayout(new BorderLayout());
    super.add(this.gridPanel, BorderLayout.CENTER);
    super.add(this.uiPanel, BorderLayout.NORTH);
  }

  public void updateMainPanel() {
    // If the game is not paused, we can update
    // the simulation.
    if ( ! this.gameOfLife.isPaused()) {
      this.gridPanel.updateGame();
    }
    
    // We always want to update the button statuses.
    this.uiPanel.updateButtons();
  }

  /**
   *
   * @param g
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

}
