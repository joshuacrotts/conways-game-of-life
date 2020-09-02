/**
 * @file DatabaseTranslator.java
 * @author Joshua Crotts
 * @date September 1 2020
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
package com.joshuacrotts.database;

import com.joshuacrotts.main.GridPanel;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DatabaseTranslator {

  private static final PersistentDatabase pdb = new PersistentDatabase();

  /**
   *
   * @param gridPanel
   * @throws IOException
   */
  public static void saveGame(GridPanel gridPanel) throws IOException {
    final JFileChooser saveChooser = new JFileChooser();
    saveChooser.setDialogType(JFileChooser.SAVE_DIALOG);
    saveChooser.setFileFilter(new FileNameExtensionFilter("Data file", "dat"));
    saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    if (saveChooser.showSaveDialog(gridPanel) == JFileChooser.APPROVE_OPTION) {
      pdb.save(saveChooser.getSelectedFile().getCanonicalPath(), gridPanel);
    }

  }

  /**
   *
   * @param gridPanel
   * @throws IOException
   */
  public static void loadGame(GridPanel gridPanel) throws IOException {
    final JFileChooser loadChooser = new JFileChooser();
    loadChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    loadChooser.setDialogType(JFileChooser.OPEN_DIALOG);
    loadChooser.setFileFilter(new FileNameExtensionFilter("Data file", "dat"));
    
    if (loadChooser.showOpenDialog(gridPanel) == JFileChooser.APPROVE_OPTION) {
      pdb.load(loadChooser.getSelectedFile().getCanonicalPath(), gridPanel);
    }
  }
}
