/**
 * @file PersistentDatabase.java
 * @author Joshua Crotts
 * @date September 1 2020
 * @version 1.0
 *
 * @section LICENSE
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * @section DESCRIPTION
 *
 */
package com.joshuacrotts.database;

import com.joshuacrotts.main.GridPanel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistentDatabase implements Database {

  /**
   *
   * @param fileName
   * @param gridPanel
   * @return
   * @throws IOException
   */
  @Override
  public boolean save(String fileName, GridPanel gridPanel) throws IOException {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(fileName));
      int[][] grid = gridPanel.getGrid();

      // Write the row, column, and grid size totals.
      writer.write(grid.length + "");
      writer.newLine();
      writer.write(grid[0].length + "");
      writer.newLine();
      writer.write(gridPanel.getGridSize() + "");
      writer.newLine();

      // Read the bits into the StringBuilder and then at the end we'll
      // write it to the file at once.
      StringBuilder s = new StringBuilder(grid.length * grid[0].length);

      for (int i = 0; i < grid.length; i ++) {
        for (int j = 0; j < grid[0].length; j ++) {
          s.append(grid[i][j]);
        }
      }

      writer.write(s.toString());
    } catch (IOException ex) {
      Logger.getLogger(PersistentDatabase.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      writer.close();
    }

    return false;
  }

  /**
   *
   * @param fileName
   * @param gridPanel
   * @return
   * @throws IOException
   */
  @Override
  public boolean load(String fileName, GridPanel gridPanel) throws IOException {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(fileName));

      // Read in the row and column size first.
      int rows = Integer.parseInt(reader.readLine());
      int cols = Integer.parseInt(reader.readLine());
      int gridSize = Integer.parseInt(reader.readLine());

      int[][] grid = new int[rows][cols];

      // Now read in the chars one by one into the array.
      for (int i = 0; i < rows; i ++) {
        for (int j = 0; j < cols; j ++) {
          grid[i][j] = reader.read() - '0';
        }
      }

      gridPanel.setGridSize(gridSize);
      gridPanel.setGrid(grid);

      return true;
    } catch (IOException ex) {
      Logger.getLogger(PersistentDatabase.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      reader.close();
    }

    return false;
  }
}
