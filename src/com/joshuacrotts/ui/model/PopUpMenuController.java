/**
 * @file PopUpMenuController.java
 * @author Joshua Crotts
 * @date September 2 2020
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
package com.joshuacrotts.ui.model;

import com.joshuacrotts.main.GridPanel;
import java.awt.Graphics;
import javax.swing.JPopupMenu;

/**
 *
 * @author Joshua
 */
public class PopUpMenuController extends JPopupMenu {

  private final GridPanel gridPanel;

  private final DrawGridMenuItem drawGridMenuItem;

  public PopUpMenuController(GridPanel gridPanel) {
    this.gridPanel = gridPanel;
    this.drawGridMenuItem = new DrawGridMenuItem(gridPanel);
    this.gridPanel.setComponentPopupMenu(this);

    super.setInheritsPopupMenu(true);
    super.add(this.drawGridMenuItem);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
