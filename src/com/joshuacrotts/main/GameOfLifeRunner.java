/**
 * @file GameOfLifeRunner.java
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

import javax.swing.SwingUtilities;

public class GameOfLifeRunner {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameOfLife game = new GameOfLife();
      game.startGame();
    });
  }
}
