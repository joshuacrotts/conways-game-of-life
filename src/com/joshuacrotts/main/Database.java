/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joshuacrotts.main;

/**
 *
 * @author Joshua
 */
public interface Database {
  public boolean save(int[][] grid);

  public int[][] load();
}
