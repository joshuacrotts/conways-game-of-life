/**
 * @file ParentPanel.java
 * @author Joshua Crotts
 * @date August 30 2020
 * @version 1.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of
 * the License, or (at your option) any later version.
 *
 * @section DESCRIPTION
 *
 */
package com.joshuacrotts.main;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ParentPanel extends JPanel {

  // Parent object.
  private final GameOfLife gameOfLife;

  // JPanels that are a part of this parent panel.
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

  /**
   *
   */
  public void updateMainPanel() {
    // If the game is not paused, we can update
    // the simulation.
    if ( ! this.gameOfLife.isPaused()) {
      this.gridPanel.updateGame();
    }

    // We always want to update the button statuses.
    this.uiPanel.updateUIElements();
  }

  /**
   *
   * @param g
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  public GridPanel getGridPanel() {
    return this.gridPanel;
  }

  public UIButtonPanel getUIButtonPanel() {
    return this.uiPanel;
  }
}
