package dominio;

import drones.dominio.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PosicionTest {

  static Posicion pBase;

  @BeforeAll
  public static void setup() {
    pBase = new Posicion(1, 1, 1);
  }

  @Test
  public void itShouldCreatePosicionFromNumbers() {
    assertEquals(1, pBase.getArea());
    assertEquals(1, pBase.getFila());
    assertEquals(1, pBase.getColumna());
  }

  @Test
  public void itShouldDetectTwoPositionsAsEqual() {
    Posicion pIgual = new Posicion(1, 1, 1);
      assertEquals(pBase, pIgual);
  }

  @Test
  public void itShouldDetectTwoPositionsWithDifferentAreasAsDifferent() {
    Posicion p = new Posicion(0, 1, 1);
      assertNotEquals(pBase, p);
  }

  @Test
  public void itShouldDetectTwoPositionsWithDifferentRowsAsDifferent() {
    Posicion p = new Posicion(1, 0, 1);
      assertNotEquals(pBase, p);
  }

  @Test
  public void itShouldDetectTwoPositionsWithDifferentColumnsAsDifferent() {
    Posicion p = new Posicion(1, 1, 0);
      assertNotEquals(pBase, p);
  }
}
