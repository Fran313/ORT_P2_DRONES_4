/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drones.dominio;

import drones.interfaz.Inicio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sistema de manejo de cargas y drones
 *
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Sistema implements Serializable {

  /**
   * Articulos del sistema
   */
  private ArrayList<Articulo> articulos;

  /**
   * Funcionarios del sistema
   */
  private ArrayList<Funcionario> funcionarios;

  /**
   * Drones del sistema
   */
  private ArrayList<Dron> drones;

  /**
   * Cargas del sistema
   */
  private ArrayList<Carga> cargas;

  /**
   * Crea un sistema vacio
   */
  public Sistema() {
    articulos = new ArrayList<>();
    funcionarios = new ArrayList<>();
    drones = new ArrayList<>();
    cargas = new ArrayList<>();
  }

  /**
   * Devuelve las cargas del sistema
   *
   * @return Cargas del sistema
   */
  public ArrayList<Carga> getCargas() {
    return this.cargas;
  }

  /**
   * Devuelve los funcionarios del sistema
   *
   * @return Funcionarios del sistema
   */
  public ArrayList<Funcionario> getFuncionarios() {
    return this.funcionarios;
  }

  /**
   * Devuelve los articulos del sistema
   *
   * @return Articulos del sistema
   */
  public ArrayList<Articulo> getArticulos() {
    return this.articulos;
  }

  /**
   * Devuelve los drones del sistema
   *
   * @return Drones del sistema
   */
  public ArrayList<Dron> getDrones() {
    return this.drones;
  }

  /**
   * Crea un funcionario nuevo con los datos ingresados y lo agrega al sistema
   *
   * @param nombre Nombre del funcionario
   * @param edad   Edad del funcionario
   * @param numero Numero del funcionario
   * @return Funcionario agregado
   */
  public Funcionario agregarFuncionario(String nombre, int edad, int numero) {
    Funcionario funcionario = new Funcionario(nombre, edad, numero);
    funcionarios.add(funcionario);
    return funcionario;
  }

  /**
   * Crea un articulo nuevo con los datos ingresados y lo agrega al sistema
   *
   * @param nombre      Nombre del articulo
   * @param descripcion Descripcion del articulo
   * @return Articulo agregado
   */
  public Articulo agregarArticulo(String nombre, String descripcion) {
    Articulo articulo = new Articulo(nombre, descripcion);
    articulos.add(articulo);
    return articulo;
  }

  /**
   * Crea un dron nuevo con los datos ingresados y lo agrega al sistema
   *
   * @param identificacion Identificacion del dron
   * @param modelo         Modelo del dron
   * @param camara         Tipo de camara del dron
   * @return Dron agregado
   */
  public Dron agregarDron(String identificacion, String modelo, int camara) {
    Dron dron = new Dron(identificacion, modelo, camara);
    drones.add(dron);
    return dron;
  }

  /**
   * Crea una carga nuevo con los datos ingresados y la agrega al sistema
   *
   * @param codigo      Codigo de la carga
   * @param articulo    Articulo cargado
   * @param funcionario Funcionario que realizo la carga
   * @param cantidad    Cantidad del articulo en la carga
   * @param posicion    Posicion de la carga
   * @return Carga agregada
   */
  public Carga agregarCarga(
      int codigo, Articulo articulo, Funcionario funcionario, int cantidad, Posicion posicion) {
    Carga carga = new Carga(codigo, articulo, funcionario, cantidad, posicion);
    cargas.add(carga);
    return carga;
  }

  /**
   * Busca una carga ingresada dada una posición
   *
   * @param posicion Posición de la carga a buscar
   * @return Carga encontrada si existía en esa posición, de lo contrario devuelve
   *         null
   */
  public Carga buscarCarga(final Posicion posicion) {
    return this.cargas.stream()
        .filter(c -> c.getPosicion().equals(posicion))
        .findAny()
        .orElse(null);
  }

  /**
   * Busca un dron en el sistema dada una identificación
   *
   * @param identificacion Identifiación del dron a buscar
   * @return Dron encontrado si existía alguno con esa identificación, de lo
   *         contrario devuelve null
   */
  public Dron buscarDron(String identificacion) {
    return this.drones.stream()
        .filter(d -> d.getIdentificacion().equals(identificacion))
        .findAny()
        .orElse(null);
  }

  /**
   * Busca un articulo en el sistema
   *
   * @param nombre Nombre del articulo
   * @return El articulo buscado
   */
  public Articulo buscarArticulo(String nombre) {
    return this.articulos.stream()
        .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
        .findAny()
        .orElse(null);
  }

  /**
   *
   * @param numero Numero del funcionario a buscar
   * @return El funcionario encontrado
   */
  public Funcionario buscarFuncionario(int numero) {
    return this.funcionarios.stream()
        .filter(f -> f.getNumero() == numero)
        .findAny()
        .orElse(null);
  }

  /**
   * Elimina una carga del sistema
   *
   * @param carga Carga a eliminar
   */
  public void eliminarCarga(final Carga carga) {
    this.cargas.remove(carga);
  }

  /**
   * Guarda el sistema serializado en el camino especificado
   *
   * @param path Camino donde guardar el sistema
   */
  public void write(Path path) {
    try (FileOutputStream fileOut = new FileOutputStream(path.toString());){
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      out.close();
      fileOut.close();
      Logger.getLogger(Inicio.class.getName())
          .log(Level.INFO, "Sistema serializado y guardado correctamente");

    } catch (FileNotFoundException ex) {

      Logger.getLogger(Inicio.class.getName())
          .log(Level.SEVERE, "El archivo de serialización no existe", ex);

    } catch (IOException ex) {

      Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, "La serialización ha fallado", ex);
    }
  }
}
