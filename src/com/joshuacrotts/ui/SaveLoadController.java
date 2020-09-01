/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.ui;

import com.joshuacrotts.main.GameOfLife;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Joshua
 */
public class SaveLoadController extends JPanel {
  
  private final GameOfLife gameOfLife;
  
  private final SaveButton saveButton;
  private final LoadButton loadButton;
  
  private static final int ROWS = 2;
  private static final int COLS = 1;
  
  private static final int SAVE_BUTTON_ROW = 0;
  private static final int LOAD_BUTTON_ROW = 1;
  
  public SaveLoadController(GameOfLife gol) {
    this.gameOfLife = gol;
    this.saveButton = new SaveButton(this.gameOfLife);
    this.loadButton = new LoadButton(this.gameOfLife);
    
    super.setLayout(new GridLayout(ROWS, COLS));
    super.add(this.saveButton, SAVE_BUTTON_ROW, 0);
    super.add(this.loadButton, LOAD_BUTTON_ROW, 0);
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
  
  public SaveButton getSaveButton() {
    return this.saveButton;
  }
  
  public LoadButton getLoadButton() {
    return this.loadButton;
  }
}
