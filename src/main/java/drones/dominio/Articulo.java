/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drones.dominio;

import java.io.Serializable;

/**
 * Articulo de mercadería
 *
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Articulo implements Serializable {

  /**
   * Nombre del articulo
   */
  private String nombre;

  /**
   * Descripcion del articulo
   */
  private String descripcion;

  /**
   * Constructor de la clase Articulo
   *
   * @param nombre      Nombre del articulo
   * @param descripcion Descripcion del articulo
   */
  public Articulo(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  /**
   * Devuelve el nombre del articulo
   *
   * @return Nombre del articulo
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * Devuelve la descripcion del articulo
   *
   * @return Descripcion del articulo
   */
  public String getDescripcion() {
    return this.descripcion;
  }

  @Override
  public String toString() {
    return this.nombre;
  }
}
