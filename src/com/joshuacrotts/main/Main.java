/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import javax.swing.SwingUtilities;

/**
 *
 * @author joshuacrotts
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      GameOfLife game = new GameOfLife();
      game.startGame();
    });
  }
}
