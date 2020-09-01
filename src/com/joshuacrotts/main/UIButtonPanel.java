/**
 * @file UIButtonPanel.java
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
package com.joshuacrotts.main;

import com.joshuacrotts.ui.FramerateChanger;
import com.joshuacrotts.ui.GenerationLabel;
import com.joshuacrotts.ui.PauseButton;
import com.joshuacrotts.ui.ResumeButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class UIButtonPanel extends JPanel {

  private final GameOfLife gameOfLife;

  private final PauseButton pauseButton;
  private final ResumeButton resumeButton;
  private final FramerateChanger framerateSlider;
  private final GenerationLabel generationLabel;

  private static final int ROWS = 1;
  private static final int COLS = 4;

  private static final int PAUSE_BUTTON_COL = 0;
  private static final int RESUME_BUTTON_COL = 1;
  private static final int FRAMERATE_SLIDER_COL = 2;
  private static final int GENERATION_LABEL_COL = 3;

  public UIButtonPanel(GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
    this.pauseButton = new PauseButton(this.gameOfLife);
    this.resumeButton = new ResumeButton(this.gameOfLife);
    this.framerateSlider = new FramerateChanger(this.gameOfLife);
    this.generationLabel = new GenerationLabel(this.gameOfLife);

    super.setLayout(new GridLayout(ROWS, COLS)); // set JPanel's layout
    super.add(this.pauseButton, 0, PAUSE_BUTTON_COL);
    super.add(this.resumeButton, 0, RESUME_BUTTON_COL);
    super.add(this.framerateSlider, 0, FRAMERATE_SLIDER_COL);
    super.add(this.generationLabel, 0, GENERATION_LABEL_COL);

    super.setPreferredSize(new Dimension(this.gameOfLife.getScreenWidth(), 60));
  }

  /**
   * Updates the pause, resume buttons, and the generation label.
   */
  public void updateUIElements() {
    this.pauseButton.setEnabled( ! this.gameOfLife.isPaused());
    this.resumeButton.setEnabled(this.gameOfLife.isPaused());
    this.generationLabel.updateGeneration();
  }

  public PauseButton getPauseButton() {
    return this.pauseButton;
  }

  public ResumeButton getResumeButton() {
    return this.resumeButton;
  }
}
