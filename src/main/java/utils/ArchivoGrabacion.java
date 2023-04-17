/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 * @author Franc
 */
public class ArchivoGrabacion {

  private Formatter out;

  public ArchivoGrabacion(String fileName) {
    try {
      out = new Formatter(fileName);
    } catch (FileNotFoundException fnfe) {
      System.err.println("Error: No se pudo encontrar el archivo.");
      System.exit(1); // Indicates unsuccessfull termination
    }
  }

  public void grabarLinea(String linea) {
    out.format("%s%n", linea);
  }

  public void cerrar() {
    out.close();
  }
}
