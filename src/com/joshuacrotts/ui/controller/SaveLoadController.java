/**
 * @file SaveLoadController.java
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
package com.joshuacrotts.ui.controller;

import com.joshuacrotts.main.GameOfLife;
import com.joshuacrotts.ui.model.LoadButton;
import com.joshuacrotts.ui.model.SaveButton;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;

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
