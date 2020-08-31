package com.joshuacrotts.main;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author joshuacrotts
 */
public class UIButtonPanel extends JPanel {

  private final GameOfLife gameOfLife;

  private final PauseButton pauseButton;
  private final ResumeButton resumeButton;
  private final FramerateChanger framerateSlider;

  private static final int ROWS = 1;
  private static final int COLS = 3;

  public UIButtonPanel(GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
    this.pauseButton = new PauseButton(this.gameOfLife);
    this.resumeButton = new ResumeButton(this.gameOfLife);
    this.framerateSlider = new FramerateChanger(this.gameOfLife);

    super.setLayout(new GridLayout(ROWS, COLS)); // set JPanel's layout
    super.add(this.pauseButton, 0, 0);
    super.add(this.resumeButton, 0, 1);
    super.add(this.framerateSlider, 0, 2);
  }

  /**
   *
   */
  public void updateButtons() {
    this.pauseButton.setEnabled( ! this.gameOfLife.isPaused());
    this.resumeButton.setEnabled( this.gameOfLife.isPaused());
  }

  public PauseButton getPauseButton() {
    return this.pauseButton;
  }

  public ResumeButton getResumeButton() {
    return this.resumeButton;
  }
}
