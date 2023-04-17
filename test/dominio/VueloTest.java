package dominio;

import drones.dominio.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VueloTest {

  static Sistema sistema;
  static Dron d;
  static Vuelo v;

  @BeforeAll
  public static void setup() throws IOException, Exception {
    sistema = new Sistema();
    d = sistema.agregarDron("holasoyDron1", "modeloDron2", 3);
    v = Vuelo.fromFile(Paths.get("vuelo.ejemplo.txt"), sistema);
  }

  @Test
  public void itShouldLoadSuccessVueloFromFile() {
    List<Integer> datosEsperados =
        new ArrayList<Integer>(Arrays.asList(1891, 7328947, 0, 0, 0, 0, 0, 0, 9043859, 0));

    assertEquals(d, v.getDron());
    assertEquals(0, v.getArea());
    assertEquals(0, v.getFila());
    assertEquals(datosEsperados, v.getDatos());
    assertEquals(true, v.getExito());
  }

  @Test
  public void itShouldLoadFailedVueloFromFile() throws IOException, Exception {
      Vuelo failedVuelo = Vuelo.fromFile(Paths.get("vuelo.menoscarga.txt"), sistema);
    List<Integer> datosEsperados =
        new ArrayList<Integer>(Arrays.asList(
                7389099,
                7328947,
                0,
                0,
                0,
                0,
                9043859,
                0));
    assertEquals(d, failedVuelo.getDron());
    assertEquals(0, failedVuelo.getArea());
    assertEquals(0, failedVuelo.getFila());
    assertEquals(datosEsperados, failedVuelo.getDatos());
    assertEquals(false, failedVuelo.getExito());
  }
}
