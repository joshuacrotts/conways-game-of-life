/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Joshua
 */
public class DatabaseTranslator {

  private static final PersistentDatabase pdb = new PersistentDatabase();

  /**
   * 
   * @param gridPanel
   * @throws IOException 
   */
  public static void saveGame(GridPanel gridPanel) throws IOException {
    final JFileChooser saveChooser = new JFileChooser();

    if (saveChooser.showOpenDialog(gridPanel) == JFileChooser.APPROVE_OPTION) {
      pdb.save(gridPanel.getGrid());
    }

  }

  /**
   * 
   * @param gridPanel
   * @throws IOException 
   */
  public static void loadGame(GridPanel gridPanel) throws IOException {
    final JFileChooser saveChooser = new JFileChooser();

    if (saveChooser.showOpenDialog(gridPanel) == JFileChooser.APPROVE_OPTION) {
      int[][] grid = pdb.load();
      
      gridPanel.setGrid(grid);
    }
  }
}
