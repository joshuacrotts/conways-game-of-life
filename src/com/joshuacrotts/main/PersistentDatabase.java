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
public class PersistentDatabase implements Database {
  
  public boolean save(int[][] grid) {
    return false;
  }
  
  public int[][] load() {
    int[][] g = new int[1][1];
    return g;
  }
}
