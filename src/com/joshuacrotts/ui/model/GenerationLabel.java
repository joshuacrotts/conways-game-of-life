/**
 * @file GenerationLabel.java
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
package com.joshuacrotts.ui.model;

import com.joshuacrotts.main.GameOfLife;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GenerationLabel extends JLabel {

  private final GameOfLife gameOfLife;

  public GenerationLabel(GameOfLife gameOfLife) {
    super("Generation: 0", SwingConstants.CENTER);
    super.setOpaque(true);
    
    this.gameOfLife = gameOfLife;
  }

  /**
   * Updates the text for the generation label.
   */
  public void updateGeneration() {
    super.setText("Generation: " + this.gameOfLife.getParentPanel()
            .getGridPanel().getGeneration());
  }
}
