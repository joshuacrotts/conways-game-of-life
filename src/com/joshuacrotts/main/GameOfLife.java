/**
 * @file GameOfLife.java
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

import com.joshuacrotts.ui.model.GridSizePrompt;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameOfLife {

  // Parent JFrame and panel objects.
  private final JFrame frame;
  private final ParentPanel parentPanel;

  // Timers used for the update and render loops.
  private Timer updateTimer;
  private Timer renderTimer;

  // Debugging and status flags.
  private boolean running = false;
  private boolean paused = true;

  // JFrame dimensions and framerate variables.
  private static final String TITLE = "Conway's Game of Life";
  private static final int RENDER_FRAMERATE = 17; // Milliseconds.
  private static final int screenWidth = 800;
  private static final int screenHeight = 800;
  private static int framerate = 200; // Milliseconds.

  public GameOfLife() {
    // Create the parent panel with the UI controls and grid itself.
    this.parentPanel = new ParentPanel(this);

    // Initialize the JFrame with the child components.
    this.frame = new JFrame(TITLE);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setPreferredSize(new Dimension(GameOfLife.screenWidth, GameOfLife.screenHeight));
    this.frame.setResizable(false);
    this.frame.getContentPane().add(this.parentPanel);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);

    // Now, prompt for the grid size.
    int gridSize = GridSizePrompt.promptGridSize(this);

    // Check the grid size to make sure it's correct.
    this.parentPanel.getGridPanel().validateGridSize(gridSize);
    this.frame.setVisible(true);
  }

  /**
   *
   */
  public void startGame() {
    if (this.running) {
      return;
    }

    this.running = true;
    this.updateLoop();
  }

  /**
   *
   */
  public void stopGame() {
    this.running = false;

    if (this.updateTimer != null) {
      this.updateTimer.stop();
    }

    if (this.renderTimer != null) {
      this.renderTimer.stop();
    }
  }

  /**
   * This is the primary "loop" for updating the game logic and the render
   * screen with the respective Swing timers.
   */
  private void updateLoop() {
    /*
     * We need two timers to run on different schedules; the logic-update timer
     * runs at a different speed.
     */
    this.updateTimer = new Timer(GameOfLife.framerate, (ActionEvent e) -> {
      this.parentPanel.updateMainPanel();
    });

    // The render timer should run at 60 FPS at all times.
    this.renderTimer = new Timer(GameOfLife.RENDER_FRAMERATE, (ActionEvent e) -> {
      this.parentPanel.repaint();
    });

    this.updateTimer.start();
    this.renderTimer.start();
  }

  /**
   * Changes the delay in ms for the update timer to a different value. This is
   * used with the FramerateChanger JSlider object.
   *
   * @param delay in milliseconds.
   */
  public void changeTimerSpeed(int delay) {
    GameOfLife.framerate = delay;
    this.updateTimer.setDelay(GameOfLife.framerate);
  }

  public ParentPanel getParentPanel() {
    return this.parentPanel;
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public int getFramerate() {
    return GameOfLife.framerate;
  }

  public int getScreenWidth() {
    return GameOfLife.screenWidth;
  }

  public int getScreenHeight() {
    return GameOfLife.screenHeight;
  }

  public boolean isPaused() {
    return this.paused;
  }

  public void setPaused(boolean p) {
    this.paused = p;
  }

  public boolean getPaused() {
    return this.paused;
  }
}
