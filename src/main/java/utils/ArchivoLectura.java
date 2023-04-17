/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * @author Franc
 */
public class ArchivoLectura {

  private Scanner in;

  private String linea;

  public ArchivoLectura(Path fileName) {
    try {
      // Should be: in = new Scanner(Paths.get(fileName));
      in = new Scanner(fileName);
    } catch (IOException e) {
      System.err.print("Error de apertura");
      System.exit(1);
    }
  }

  public boolean hayMasLineas() {
    boolean hay = false;
    linea = null;

    if (in.hasNext()) {
      linea = in.nextLine();
      hay = true;
    }
    return hay;
  }

  public String linea() {
    return linea;
  }

  public void cerrar() {
    in.close();
  }
}
