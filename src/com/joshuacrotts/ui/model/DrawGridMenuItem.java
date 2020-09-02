/**
 * @file DrawGridButton.java
 * @author Joshua Crotts
 * @date September 2 2020
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
package com.joshuacrotts.ui.model;

import com.joshuacrotts.main.GridPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

public class DrawGridMenuItem extends JCheckBoxMenuItem implements ActionListener {

  private final GridPanel gridPanel;

  public DrawGridMenuItem(GridPanel gridPanel) {
    super("Draw Grid");
    this.gridPanel = gridPanel;
    
    super.setOpaque(true);
    super.setBorderPainted(false);
    super.addActionListener(this);
    super.setSelected(true);
  }

  @Override
  public void actionPerformed(ActionEvent ex) {
    this.gridPanel.setDrawGrid(!this.gridPanel.shouldDrawGrid());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
