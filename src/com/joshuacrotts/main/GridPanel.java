/**
 * @file GridPanel.java
 * @author Joshua Crotts
 * @date August 30 2020
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
package com.joshuacrotts.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements MouseListener {

  // Parent game panel.
  private final GameOfLife gameOfLife;

  // Randomization factor (how populated the grid is upon startup.
  private final double RANDOMIZE_FACTOR = 0.90;

  private final int DEFAULT_GRID_SIZE = 6;

  private int[][] readGrid;
  private int[][] writeGrid;
  private int gridSize;

  private int generations;

  public GridPanel(GameOfLife life) {
    this.gameOfLife = life;
    this.gridSize = this.DEFAULT_GRID_SIZE;

    super.addMouseListener(this);
  }

  /**
   *
   */
  public void updateGame() {
    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        this.applyRule(i, j);
      }
    }

    // After every generation, we need to replace the 
    // grid we currently see with the one we just updated.
    this.readGrid = cloneArray(this.writeGrid);
    this.generations ++;
  }

  /**
   *
   * @param g
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    this.drawGrid(g2);
    this.drawGridOutline(g2);
  }

  /**
   * Ensures that we input a grid size that doesn't leak outside the panel size.
   * This should be a multiple of the window size.
   *
   * @param gridSize
   * @throws IllegalArgumentException if the number isn't a multiple of the
   * window size.
   */
  public void validateGridSize(int gridSize) throws IllegalArgumentException {
    try {
      if (this.gameOfLife.getScreenWidth() % gridSize != 0
              || this.gameOfLife.getScreenHeight() % gridSize != 0) {
        this.gameOfLife.stopGame();
        throw new IllegalArgumentException("Error, your grid size " + gridSize
                + " should be a multiple of the panel size: " + this.getWidth()
                + ", " + this.getHeight());
      }
    } catch (IllegalArgumentException ex) {
      Logger.getLogger(GridPanel.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(1);
    }

    // Set the grid size then instantiate the arrays.
    this.gridSize = gridSize;

    this.readGrid = new int[this.gameOfLife.getScreenWidth() / this.gridSize][this.gameOfLife.getScreenHeight() / this.gridSize];
    this.writeGrid = new int[this.gameOfLife.getScreenWidth() / this.gridSize][this.gameOfLife.getScreenHeight() / this.gridSize];
  }

  /**
   * Randomly assigns alive or dead cells to the grid.
   */
  public void randomizeGrid() {
    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        readGrid[i][j] = Math.random() > RANDOMIZE_FACTOR ? 1 : 0;
      }
    }

    this.writeGrid = cloneArray(this.readGrid);
  }

  /**
   * Removes all alive cells from the grid.
   */
  public void clearGrid() {
    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        readGrid[i][j] = 0;
      }
    }

    this.writeGrid = cloneArray(this.readGrid);
  }

  /**
   * Clones the provided array.
   *
   * @param src
   * @return a new clone of the provided array
   */
  public static int[][] cloneArray(int[][] src) {
    int length = src.length;
    int[][] target = new int[length][src[0].length];
    for (int i = 0; i < length; i ++) {
      System.arraycopy(src[i], 0, target[i], 0, src[i].length);
    }
    return target;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.gameOfLife.isPaused()) {
      int iTile = e.getX() / this.gridSize;
      int jTile = e.getY() / this.gridSize;

      if (this.readGrid[iTile][jTile] == 1) {
        this.readGrid[iTile][jTile] = 0;
      } else {
        this.readGrid[iTile][jTile] = 1;
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent _e) {
  }

  @Override
  public void mouseReleased(MouseEvent _e) {
  }

  @Override
  public void mouseEntered(MouseEvent _e) {
  }

  @Override
  public void mouseExited(MouseEvent _e) {
  }

  /**
   * Draws and colors the cells in the grid.
   *
   * @param g2
   */
  private void drawGrid(Graphics2D g2) {
    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        g2.setColor(this.readGrid[i][j] == 1 ? Color.RED : Color.WHITE);
        g2.fillRect(i * this.gridSize, j * this.gridSize, this.gridSize, this.gridSize);
      }
    }
  }

  /**
   * Draws the gray grid on the background.
   *
   * @param g2
   */
  private void drawGridOutline(Graphics2D g2) {
    g2.setColor(Color.GRAY);
    // Vertical lines.
    for (int i = 0; i <= super.getWidth(); i += this.gridSize) {
      g2.drawLine(i, 0, i, super.getHeight());
    }

    // Horizontal lines.
    for (int i = 0; i <= super.getHeight(); i += this.gridSize) {
      g2.drawLine(0, i, super.getWidth(), i);
    }
  }

  /**
   * Applies the rules to any generic cell.
   *
   * @param i
   * @param j
   */
  private void applyRule(int i, int j) {
    boolean isLeft = j == 0;
    boolean isRight = j == this.readGrid.length - 1;
    boolean isTop = i == 0;
    boolean isBottom = i == this.readGrid[0].length - 1;

    boolean isTopLeft = isLeft && isTop;
    boolean isTopRight = isTop && isRight;
    boolean isBottomLeft = isLeft && isBottom;
    boolean isBottomRight = isBottom && isRight;

    if (this.applyToCornerCells(i, j, isTopLeft, isTopRight, isBottomLeft, isBottomRight)) {
      return;
    }

    if (this.applyToEdgeCells(i, j, isLeft, isRight, isTop, isBottom)) {
      return;
    }

    // Now, handle all the remaining ones.
    int sum = readGrid[i - 1][j - 1] + readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1] + readGrid[i + 1][j + 1] + readGrid[i + 1][j] + readGrid[i + 1][j - 1] + readGrid[i][j - 1];
    this.applyToCell(sum, i, j);
  }

  /**
   * Updates a cell based on its number of neighbors
   *
   * @param sum number of neighbors.
   * @param i
   * @param j
   */
  private void applyToCell(int sum, int i, int j) {
    if (this.readGrid[i][j] == 1) {
      if (sum < 2) {
        this.writeGrid[i][j] = 0;
      } else if (sum <= 3) {
        this.writeGrid[i][j] = 1;
      } else if (sum > 3) {
        this.writeGrid[i][j] = 0;
      }
    } else {
      if (sum == 3) {
        this.writeGrid[i][j] = 1;
      }
    }
  }

  /**
   * Applies the rules to a corner cell.
   *
   * @param i
   * @param j
   * @param isTopLeft
   * @param isTopRight
   * @param isBottomLeft
   * @param isBottomRight
   * @return true if we update a corner cell, false otherwise.
   */
  private boolean applyToCornerCells(int i, int j, boolean isTopLeft, boolean isTopRight,
          boolean isBottomLeft, boolean isBottomRight) {
    if (isTopLeft) {
      int tLeft = readGrid[i + 1][j] + readGrid[i + 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(tLeft, i, j);
    } else if (isTopRight) {
      int tRight = readGrid[i + 1][j] + readGrid[i][j - 1] + readGrid[i + 1][j - 1];
      this.applyToCell(tRight, i, j);
    } else if (isBottomLeft) {
      int bLeft = readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(bLeft, i, j);
    } else if (isBottomRight) {
      int bRight = readGrid[i][j - 1] + readGrid[i - 1][j - 1] + readGrid[i - 1][j];
      this.applyToCell(bRight, i, j);
    }

    return isTopLeft || isTopRight || isBottomLeft || isBottomRight;
  }

  /**
   * Applies the rules to an edge cell.
   *
   * @param i
   * @param j
   * @param isLeft
   * @param isRight
   * @param isTop
   * @param isBottom
   * @return true if we updated an edge cell, false otherwise.
   */
  private boolean applyToEdgeCells(int i, int j, boolean isLeft, boolean isRight,
          boolean isTop, boolean isBottom) {
    // Now handle the more intermediary cases (5 cells)
    if (isLeft) {
      int left = readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1] + readGrid[i + 1][j + 1] + readGrid[i + 1][j];
      this.applyToCell(left, i, j);
    } else if (isRight) {
      int right = readGrid[i - 1][j] + readGrid[i - 1][j - 1] + readGrid[i][j - 1] + readGrid[i + 1][j - 1] + readGrid[i + 1][j];
      this.applyToCell(right, i, j);
    } else if (isTop) {
      int top = readGrid[i][j - 1] + readGrid[i + 1][j - 1] + readGrid[i + 1][j] + readGrid[i + 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(top, i, j);
    } else if (isBottom) {
      int bottom = readGrid[i][j - 1] + readGrid[i - 1][j - 1] + readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(bottom, i, j);
    }
    return isLeft || isRight || isTop || isBottom;
  }

  public int getGeneration() {
    return this.generations;
  }

  public int[][] getGrid() {
    return this.readGrid;
  }

  public void setGrid(int[][] grid) {
    this.readGrid = grid;
    this.writeGrid = cloneArray(this.readGrid);
  }

  public void setGridSize(int gridSize) {
    this.gridSize = gridSize;
  }

  public int getGridSize() {
    return this.gridSize;
  }
}
