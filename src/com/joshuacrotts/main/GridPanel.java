/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author joshuacrotts
 */
public class GridPanel extends JPanel implements MouseListener {

  private final GameOfLife gameOfLife;

  private final int GRID_SIZE = 16;
  private final double RANDOMIZE_FACTOR = 0.90;

  private int[][] readGrid;
  private int[][] writeGrid;

  private int generations;

  public GridPanel(GameOfLife life) {
    this.gameOfLife = life;

    // Check to make sure the GRID SIZE is a multiple of the dimension.
    this.readGrid = new int[life.getScreenWidth() / GRID_SIZE][life.getScreenHeight() / GRID_SIZE];
    this.writeGrid = new int[life.getScreenWidth() / GRID_SIZE][life.getScreenHeight() / GRID_SIZE];

    super.addMouseListener(this);
    this.randomize();
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
  }

  /**
   *
   * @param g
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        g2.setColor(this.readGrid[i][j] == 1 ? Color.RED : Color.WHITE);
        g2.fillRect(i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE, GRID_SIZE);
      }
    }

    this.drawGrid(g2);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.gameOfLife.isPaused()) {
      int iTile = e.getX() / GRID_SIZE;
      int jTile = e.getY() / GRID_SIZE;

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
   * 
   * @param g2 
   */
  private void drawGrid(Graphics2D g2) {
    g2.setColor(Color.GRAY);

    // Vertical lines.
    for (int i = 0; i <= this.gameOfLife.getScreenWidth(); i += GRID_SIZE) {
      g2.drawLine(i, 0, i, this.gameOfLife.getScreenHeight());
    }

    // Horizontal lines.
    for (int i = 0; i <= this.gameOfLife.getScreenHeight(); i += GRID_SIZE) {
      g2.drawLine(0, i, this.gameOfLife.getScreenWidth(), i);
    }
  }

  /**
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

    int left, right, top, bottom, bLeft, bRight, tLeft, tRight;

    // Handle the special cases first.
    if (isTopLeft) {
      tLeft = readGrid[i + 1][j] + readGrid[i + 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(tLeft, i, j);
    } else if (isTopRight) {
      tRight = readGrid[i + 1][j] + readGrid[i][j - 1] + readGrid[i + 1][j - 1];
      this.applyToCell(tRight, i, j);
    } else if (isBottomLeft) {
      bLeft = readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(bLeft, i, j);
    } else if (isBottomRight) {
      bRight = readGrid[i][j - 1] + readGrid[i - 1][j - 1] + readGrid[i - 1][j];
      this.applyToCell(bRight, i, j);
    }

    if (isTopLeft || isTopRight || isBottomLeft || isBottomRight) {
      return;
    }

    // Now handle the more intermediary cases (5 cells)
    if (isLeft) {
      left = readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1] + readGrid[i + 1][j + 1] + readGrid[i + 1][j];
      this.applyToCell(left, i, j);
    } else if (isRight) {
      right = readGrid[i - 1][j] + readGrid[i - 1][j - 1] + readGrid[i][j - 1] + readGrid[i + 1][j - 1] + readGrid[i + 1][j];
      this.applyToCell(right, i, j);
    } else if (isTop) {
      top = readGrid[i][j - 1] + readGrid[i + 1][j - 1] + readGrid[i + 1][j] + readGrid[i + 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(top, i, j);
    } else if (isBottom) {
      bottom = readGrid[i][j - 1] + readGrid[i - 1][j - 1] + readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1];
      this.applyToCell(bottom, i, j);
    }

    if (isTop || isLeft || isRight || isBottom) {
      return;
    }

    // Now, handle all the remaining ones.
    int sum = readGrid[i - 1][j - 1] + readGrid[i - 1][j] + readGrid[i - 1][j + 1] + readGrid[i][j + 1] + readGrid[i + 1][j + 1] + readGrid[i + 1][j] + readGrid[i + 1][j - 1] + readGrid[i][j - 1];
    this.applyToCell(sum, i, j);
  }

  /**
   * 
   * @param sum
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
   *
   */
  private void randomize() {
    for (int i = 0; i < this.readGrid.length; i ++) {
      for (int j = 0; j < this.readGrid[0].length; j ++) {
        readGrid[i][j] = Math.random() > RANDOMIZE_FACTOR ? 1 : 0;
      }
    }
  }

  /**
   * Clones the provided array
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
}
