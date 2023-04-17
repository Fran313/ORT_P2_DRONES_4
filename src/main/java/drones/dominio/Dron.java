/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drones.dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Dron para controlar el inventario de mercaderia
 *
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Dron implements Serializable {

  /**
   * Identificiacion del dron
   */
  private String identificacion;

  /**
   * Modelo del dron
   */
  private String modelo;

  /**
   * Tipo de camara del dron
   */
  private int camara;

  /**
   * Vuelos realizados por el dron
   */
  ArrayList<Vuelo> vuelos = new ArrayList<>();

  /**
   * Constructor de la clase Dron
   *
   * @param identificacion Identificacion del dron
   * @param modelo         Modelo del dron
   * @param camara         Tipo de camara del dron
   */
  public Dron(String identificacion, String modelo, int camara) {
    this.identificacion = identificacion;
    this.modelo = modelo;
    this.camara = camara;
  }

  /**
   * Devuelve la identificacion del dron
   *
   * @return Identificacion del dron
   */
  public String getIdentificacion() {
    return this.identificacion;
  }

  /**
   * Devuelve el modelo del dron
   *
   * @return Modelo del dron
   */
  public String getModelo() {
    return this.modelo;
  }

  /**
   * Devuelve el tipo de camara del dron
   *
   * @return Tipo de camara del dron
   */
  public int getCamara() {
    return this.camara;
  }

  /**
   * Devuelve los vuelos realizados por el dron
   *
   * @return Vuelos realizados por el dron
   */
  public ArrayList<Vuelo> getVuelos() {
    return this.vuelos;
  }

  /**
   * Agrega un vuelo a los vuelos del dron
   *
   * @param vuelo Vuelo del dron
   */
  public void agregarVuelo(Vuelo vuelo) {
    this.vuelos.add(vuelo);
  }

  @Override
  public String toString() {
    return this.identificacion;
  }
}
