package drones.dominio;

import java.io.Serializable;

/**
 * Posicion del deposito
 *
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Posicion implements Serializable {

  /**
   * Area de la posicion
   */
  private int area;

  /**
   * Fila de la posicion
   */
  private int fila;

  /**
   * Columna de la posicion
   */
  private int columna;

  /**
   * Constructor de la clase Posicion
   *
   * @param area    Area de la posicion
   * @param fila    Fila de la posicion
   * @param columna Columna de la posicion
   */
  public Posicion(int area, int fila, int columna) {
    this.area = area;
    this.fila = fila;
    this.columna = columna;
  }

  /**
   * Devuelve el area de la posicion
   * 
   * @return Area de la posicion
   */
  public int getArea() {
    return this.area;
  }

  /**
   * Devuelve el codigo human-friendly del area dada
   * 
   * @param area Area a convertir
   * @return Codigo del area
   */
  public static char areaCode(int area) {
    return (char) (area + 65);
  }

  /**
   * Devuelve la fila de la posicion
   * 
   * @return Fila de la posicion
   */
  public int getFila() {
    return this.fila;
  }

  /**
   * Devuelve la columna de la posicion
   * 
   * @return Columna de la posicion
   */
  public int getColumna() {
    return this.columna;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || !(object instanceof Posicion)) {
      return false;
    } else {
      Posicion posicion = (Posicion) object;
      return (posicion.getArea() == this.area)
          && (posicion.getFila() == this.fila)
          && (posicion.getColumna() == this.columna);
    }
  }

  @Override
  public String toString() {
    return String.valueOf(Posicion.areaCode(this.area)) + this.fila + this.columna;
  }
}
