/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author joshuacrotts
 */
public class FramerateChanger extends JSlider implements ChangeListener {

  private final GameOfLife gameOfLife;
  
  public FramerateChanger(GameOfLife gol) {
    this.gameOfLife = gol;
    super.setMinimum(GameOfLife.FRAMERATE_MINIMUM);
    super.setMaximum(GameOfLife.FRAMERATE_MAXIMUM);
    super.setValue(this.gameOfLife.getFramerate());
    super.setPaintTrack(true);
    super.setPaintTicks(true);
    super.setPaintLabels(true);
    super.setMajorTickSpacing(500);
    super.setMinorTickSpacing(100);
    super.addChangeListener(this);
  }
  
  @Override
  public void stateChanged(ChangeEvent _e) {
    this.gameOfLife.changeTimerSpeed(super.getValue());
  }
}
