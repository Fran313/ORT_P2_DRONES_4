package dominio;

import drones.dominio.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CargaTest {
  static Carga cargaBase;

  @BeforeAll
  public static void setup() {
    int codigo = 1891131;
    Articulo a1 = new Articulo("papas fritas", "mejores que la mugre de Mc");
    Funcionario f1 = new Funcionario("Nico", 19, 1891);
    int cantidad1 = 3;
    Posicion p1 = new Posicion(1, 1, 1);

    cargaBase = new Carga(codigo, a1, f1, cantidad1, p1);
  }

  @Test
  void itShouldDetectTwoCargasWithSameCodeAsEqual() {
    Articulo a2 = new Articulo("galletitas", "pepitos");
    Funcionario f2 = new Funcionario("Fran", 19, 2873);
    int cantidad2 = 4;
    Posicion p2 = new Posicion(2, 2, 2);

    Carga cargaIgual = new Carga(cargaBase.getCodigo(), a2, f2, cantidad2, p2);
    assertEquals(cargaBase, cargaIgual);
  }

  @Test
  void itShouldDetectTwoCargasWithDifferentCodeAsDifferent() {
    Carga cargaDistinta =
        new Carga(
            749329,
            cargaBase.getArticulo(),
            cargaBase.getFuncionario(),
            cargaBase.getCantidad(),
            cargaBase.getPosicion());
    assertNotEquals(cargaBase,cargaDistinta);
  }
}
