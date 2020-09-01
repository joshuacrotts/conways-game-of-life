/**
 * @file PauseButton.java
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
package com.joshuacrotts.ui;

import com.joshuacrotts.main.DatabaseTranslator;
import com.joshuacrotts.main.GameOfLife;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class SaveButton extends JButton implements ActionListener {

  private final GameOfLife gameOfLife;
  
  public SaveButton(GameOfLife gol) {
    super("SAVE");
    this.gameOfLife = gol;
    
    super.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    try {
      DatabaseTranslator.saveGame(this.gameOfLife.getParentPanel().getGridPanel());
    } catch (IOException ex1) {
      Logger.getLogger(SaveButton.class.getName()).log(Level.SEVERE, null, ex1);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
