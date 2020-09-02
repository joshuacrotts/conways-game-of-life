/**
 * @file GridSizePrompt.java
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
import javax.swing.JOptionPane;

public class GridSizePrompt {

  /**
   * 
   * @param gameOfLife
   * @return 
   */
  public static int promptWindowSize(GameOfLife gameOfLife) {
    return Integer.parseInt(JOptionPane.showInputDialog(gameOfLife.getFrame(),
            "Enter a window width. Your height will automatically adjust."));
  }

  /**
   * 
   * @param gameOfLife
   * @return 
   */
  public static int promptGridSize(GameOfLife gameOfLife) {
    //TreeSet<Integer> possibleGridSizes = gameOfLife.getParentPanel().getGridPanel().getPossibleGridSizes();

    return Integer.parseInt(JOptionPane.showInputDialog(gameOfLife.getFrame(),
            "Enter a cell size: "));
  }
}
