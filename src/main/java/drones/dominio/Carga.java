package drones.dominio;

import java.io.Serializable;

/**
 * Carga de mercaderia
 *
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class Carga implements Serializable {

  /**
   * Codigo de la carga
   */
  private int codigo;

  /**
   * Articulo cargado
   */
  private Articulo articulo;

  /**
   * Funcionario que realizo la carga
   */
  private Funcionario funcionario;

  /**
   * Cantidad del articulo en la carga
   */
  private int cantidad;

  /**
   * Posicion de la carga
   */
  private Posicion posicion;

  /**
   * Constructor de la clase Carga
   *
   * @param codigo      Codigo de la carga
   * @param articulo    Articulo cargado
   * @param funcionario Funcionario que realizo la carga
   * @param cantidad    Cantidad del articulo en la carga
   * @param posicion    Posicion de la carga
   */
  public Carga(
      int codigo, Articulo articulo, Funcionario funcionario, int cantidad, Posicion posicion) {
    this.codigo = codigo;
    this.articulo = articulo;
    this.funcionario = funcionario;
    this.cantidad = cantidad;
    this.posicion = posicion;
  }

  /**
   * Devuelve el codigo de la carga
   *
   * @return Codigo de la carga
   */
  public int getCodigo() {
    return this.codigo;
  }

  /**
   * Devuelve el articulo cargado
   *
   * @return Articulo cargado
   */
  public Articulo getArticulo() {
    return this.articulo;
  }

  /**
   * Devuelve el funcionario que realizo la carga
   *
   * @return Funcionario que realizo la carga
   */
  public Funcionario getFuncionario() {
    return this.funcionario;
  }

  /**
   * Devuelve la cantidad del articulo en la carga
   *
   * @return Cantidad del articulo en la carga
   */
  public int getCantidad() {
    return this.cantidad;
  }

  /**
   * Devuelve la posicion de la carga
   *
   * @return Posicion de la carga
   */
  public Posicion getPosicion() {
    return this.posicion;
  }

  @Override
  public boolean equals(Object object) {
    if ((object == null) || !(object instanceof Carga)) {
      return false;
    } else {
      Carga carga = (Carga) object;
      return (carga.codigo == this.codigo);
    }
  }
}
