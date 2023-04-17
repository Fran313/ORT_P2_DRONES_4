/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drones.dominio;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import utils.ArchivoLectura;

/**
 * Vuelo de un dron
 * 
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Vuelo implements Serializable {

  /**
   * Dron que realizó el vuelo
   */
  private Dron dron;

  /**
   * Area del vuelo
   */
  private int area;

  /**
   * Fila del vuelo
   */
  private int fila;

  /**
   * Nombre del archivo del vuelo
   */
  private String fileName;

  /**
   * Codigos de las cargas del vuelo
   */
  private ArrayList<Integer> datos;

  /**
   * Codigos tomados de la lectura manual
   */
  private int[] manual;

  /**
   * Constructor de la clase Vuelo
   *
   * @param dron     Dron que realizó vuelo
   * @param area     Area del vuelo
   * @param fila     Fila del vuelo
   * @param fileName Nombre del archivo del vuelo
   * @param datos    Codigos de las cargas del vuelo
   * @param manual   Codigos tomados manualmente
   */
  public Vuelo(
      Dron dron, int area, int fila, String fileName, ArrayList<Integer> datos, int[] manual) {
    this.dron = dron;
    this.fila = fila;
    this.area = area;
    this.fileName = fileName;
    this.datos = datos;
    this.manual = manual;
  }

  /**
   * Lee un vuelo desde un archivo y lo crea
   *
   * @param path    Path del archivo del vuelo a crear
   * @param sistema i
   * @return El vuelo tomado el archivo
   * @throws IOException si el archivo tiene formato incorrecto
   * @throws Exception si el dron especificado en el archivo no existe
   */
  public static Vuelo fromFile(Path path, Sistema sistema) throws IOException, Exception {
    // TODO: Check if ArchivoLectura receive Path or String param for constructor
    ArchivoLectura arch = new ArchivoLectura(path);
    Dron dron = null;
    Integer intArea = null;
    Integer fila = null;
    ArrayList<Integer> datos = new ArrayList<>();

    if (arch.hayMasLineas()) {
      String identificacion = arch.linea();
      dron = sistema.buscarDron(identificacion);
    }

    if (arch.hayMasLineas()) {
      String stringPos = arch.linea();
      intArea = (int) (stringPos.charAt(0) - 65);
      fila = Integer.parseInt(stringPos.substring(2, stringPos.length())) - 1;
    }

    while (arch.hayMasLineas()) {
      datos.add(Integer.valueOf(arch.linea()));
    }

    arch.cerrar();

    if (dron == null) {
      throw new Exception("El dron ingresado no existe");
    }

    if (intArea == null || fila == null) {
      throw new IOException("El archivo tiene formato incorrecto");
    }

    int[] manual = new int[10];

    for (Carga c : sistema.getCargas()) {
      if (c.getPosicion().getFila() == fila && c.getPosicion().getArea() == intArea) {
        manual[c.getPosicion().getColumna()] = c.getCodigo();
      }
    }

    Vuelo vuelo = new Vuelo(dron, intArea, fila, path.getFileName().toString(), datos, manual);
    dron.agregarVuelo(vuelo);
    return vuelo;
  }

  /**
   * Devuelve el dron que realizo el vuelo
   *
   * @return Dron que realizo el vuelo
   */
  public Dron getDron() {
    return dron;
  }

  /**
   * Devuelve la fila del vuelo
   *
   * @return Fila del vuelo
   */
  public int getFila() {
    return fila;
  }

  /**
   * Devuelve el area del vuelo
   *
   * @return Area del vuelo
   */
  public int getArea() {
    return area;
  }

  /**
   * Devuelve los datos del vuelo
   *
   * @return Datos del vuelo
   */
  public ArrayList<Integer> getDatos() {
    return datos;
  }

  /**
   * Devuelve los datos manuales de la fila del vuelo
   *
   * @return Datos manuales
   */
  public int[] getManual() {
    return manual;
  }

  /**
   * Devuelve el exito del vuelo
   *
   * @return True si el vuelo fue exitoso, false de otra forma
   */
  public Boolean getExito() {
    return datos.size() == 10;
  }

  /**
   * Devuelve la lectura completa de los datos del vuelo. Los datos con ceros
   * dónde no hubo lectura en caso de no ser exitoso
   *
   * @return Lectura completa de los datos del vuelo
   */
  public int[] getReading() {
    int[] reading = new int[10];
    for (int i = 0; i < datos.size(); i++) {
      reading[i] = datos.get(i);
    }
    return reading;
  }

  /**
   * Devuelve un vector con las coincidencias y diferencias entre la lectura
   * completa del vuelo y la lectura manual.
   *
   * @return Vector con coincidencias y diferencias.
   */
  public int[] getDiff() {
    int[] diff = new int[10];
    int[] reading = getReading();

    for (int col = 0; col < reading.length; col++) {
      int coincidencia = 0;
      if (reading[col] == manual[col]) {
        coincidencia = 1;
      }
      diff[col] = coincidencia;
    }
    return diff;
  }

  /**
   * Devuelve la cantidad de coincidencias entre la lectura completa del vuelo y
   * la lectura manual
   *
   * @return Cantidad de coincidencias
   */
  public int getCoincidencias() {
    int coincidencias = 0;
    int[] diff = getDiff();

    for (int colDiff : diff) {
      if (colDiff == 1)
        coincidencias++;
    }
    return coincidencias;
  }

  /**
   * Devuelve la cantidad de diferencias entre la lectura completa del vuelo y
   * la lectura manual
   *
   * @return Cantidad de diferencias
   */
  public int getDiferencias() {
    return 10 - getCoincidencias();
  }

  @Override
  public String toString() {
    String response;
    if (getExito()) {
      response = "Nombre de archivo: "
          + fileName
          + " - Area: "
          + area
          + " - Fila: "
          + fila
          + " - Coincidencias: "
          + getCoincidencias()
          + " - Diferencias: "
          + getDiferencias();
    } else {
      response = "Nombre de archivo: "
          + fileName
          + " - Area: "
          + area
          + " - Fila: "
          + fila
          + " - Cantidad de lineas de carga: "
          + datos.size();
    }
    return response;
  }
}
