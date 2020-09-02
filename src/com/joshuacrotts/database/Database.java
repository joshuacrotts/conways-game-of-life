/**
 * @file Database.java
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

public interface Database {
  public boolean save(String fileName, GridPanel gridPanel) throws IOException;

  public boolean load(String fileName, GridPanel gridPanel) throws IOException;
}
