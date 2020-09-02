/**
 * @file FramerateChangerController.java
 * @author Joshua Crotts
 * @date September 1 2020
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
package com.joshuacrotts.ui.controller;

import com.joshuacrotts.main.GameOfLife;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FramerateChangeController extends JPanel implements ChangeListener {

  private final GameOfLife gameOfLife;

  private final JSlider slider;
  private final JLabel changeLabel;
  private final JLabel currentLabel;

  // Spacing for the intervals on the JSlider.
  private static final int MAJOR_TICK_SPACE = 500;
  private static final int MINOR_TICK_SPACE = 100;

  // Framerate max and min options for the slider.
  private static final int FRAMERATE_MINIMUM = 0;
  private static final int FRAMERATE_MAXIMUM = 1000;

  public FramerateChangeController(GameOfLife gol) {
    this.gameOfLife = gol;
    this.slider = new JSlider();
    this.changeLabel = new JLabel("Framerate (MS)");
    this.currentLabel = new JLabel("Current MS: " + this.gameOfLife.getFramerate());
    this.slider.setMinimum(FramerateChangeController.FRAMERATE_MINIMUM);
    this.slider.setMaximum(FramerateChangeController.FRAMERATE_MAXIMUM);
    this.slider.setValue(this.gameOfLife.getFramerate());
    this.slider.setMajorTickSpacing(FramerateChangeController.MAJOR_TICK_SPACE);
    this.slider.setMinorTickSpacing(FramerateChangeController.MINOR_TICK_SPACE);
    this.slider.addChangeListener(this);

    super.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    this.addChangeLabel(gbc);
    this.addCurrentLabel(gbc);
    this.addSlider(gbc);
  }

  @Override
  public void stateChanged(ChangeEvent _e) {
    this.gameOfLife.changeTimerSpeed(this.slider.getValue());
    this.currentLabel.setText("Current MS: " + this.slider.getValue());
  }

  /**
   * 
   * @param gbc 
   */
  private void addChangeLabel(GridBagConstraints gbc) {
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    super.add(this.changeLabel, gbc);
  }

  /**
   * 
   * @param gbc 
   */
  private void addSlider(GridBagConstraints gbc) {
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 2;
    super.add(this.slider, gbc);
  }

  /**
   * 
   * @param gbc 
   */
  private void addCurrentLabel(GridBagConstraints gbc) {
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    super.add(this.currentLabel, gbc);
  }
}
