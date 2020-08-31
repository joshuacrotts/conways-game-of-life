/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author joshuacrotts
 */
public class GameOfLife {

  private final JFrame frame;
  private final ParentPanel parentPanel;

  private Timer updateTimer;
  private Timer renderTimer;

  private boolean running = false;
  private boolean paused = true;

  private static final int SCREEN_WIDTH = 800;
  private static final int SCREEN_HEIGHT = 800;
  private static final int RENDER_FRAMERATE = 17; // Milliseconds.
  private int framerate = 200; // Milliseconds.

  protected static final int FRAMERATE_MINIMUM = 0;
  protected static final int FRAMERATE_MAXIMUM = 1000;

  public GameOfLife() {
    this.parentPanel = new ParentPanel(this);
    this.frame = new JFrame("Conway's Game of Life");
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.frame.setResizable(false);
    this.frame.getContentPane().add(this.parentPanel);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
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
    if ( ! this.running) {
      return;
    }

    this.running = false;
    this.updateTimer.stop();
    this.renderTimer.stop();
  }

  /**
   *
   */
  private void updateLoop() {
    // We need two timers to run on different schedules;
    // the logic-update timer runs at a different speed.
    this.updateTimer = new Timer(this.framerate, (ActionEvent e) -> {
      this.parentPanel.updateMainPanel();
    });

    // The render timer should run at 60 FPS at all times.
    this.renderTimer = new Timer(GameOfLife.RENDER_FRAMERATE, (ActionEvent e) -> {
      this.parentPanel.repaint();
    });

    this.updateTimer.start();
    this.renderTimer.start();
  }

  public void changeTimerSpeed(int delay) {
    this.framerate = delay;
    this.updateTimer.setDelay(this.framerate);
  }

  public int getFramerate() {
    return this.framerate;
  }

  public int getScreenWidth() {
    return GameOfLife.SCREEN_WIDTH;
  }

  public int getScreenHeight() {
    return GameOfLife.SCREEN_HEIGHT;
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
