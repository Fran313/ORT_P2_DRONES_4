/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package drones.interfaz;

import drones.dominio.Articulo;
import drones.dominio.Funcionario;
import drones.dominio.Posicion;
import drones.dominio.Sistema;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * @author Nicolas Russo
 * @author Francisco Suarez
 */
public class IngresoEgresoCarga extends javax.swing.JFrame {

  private Sistema sistema;
  private int areaSeleccionada;
  JButton[][] buttons;

  /**
   * Creates new form IngresoEgresoCarga
   *
   * @param s
   */
  public IngresoEgresoCarga(Sistema s) {
    this.sistema = s;
    this.areaSeleccionada = 0;
    this.buttons = new JButton[12][10];

    initComponents();

    for (int y = 0; y < 12; y++) {
      for (int x = 0; x < 10; x++) {
        JButton nuevo = new JButton(" ");
        nuevo.setMargin(new Insets(-5, -5, -5, -5));
        nuevo.setBackground(Color.WHITE);
        nuevo.setForeground(Color.BLACK);
        nuevo.setText((y + 1) + ":" + (x + 1));

        this.buttons[y][x] = nuevo;

        pnlButtonsGrid.add(nuevo);
      }
    }

    hydrate();
  }

  private void hydrate() {
    CardLayout cl = (CardLayout) (pnlRight.getLayout());

    lblArea.setText("Area " + Posicion.areaCode(areaSeleccionada));

    // Mostramos el filler en el panel de la derecha antes de actualizar
    // los botones
    cl.show(pnlRight, "filler");

    lstFuncionarios.setListData(
        sistema.getFuncionarios().stream().map(f -> f.toString()).toArray(String[]::new));

    lstArticulos.setListData(
        sistema.getArticulos().stream().map(f -> f.toString()).toArray(String[]::new));

    // Botones sin carga
    for (int y = 0; y < buttons.length; y++) {
      JButton[] row = buttons[y];
      for (int x = 0; x < row.length; x++) {
        JButton button = row[x];

        if (button.getModel().isRollover()) {
          button.setBackground(Color.RED);
          button.setForeground(Color.WHITE);
        } else {
          button.setBackground(Color.WHITE);
          button.setForeground(Color.BLACK);
        }

        removeAllActionListeners(button);
        final int _x = x;
        final int _y = y;

        button.addActionListener(
            new ActionListener() {
              public void actionPerformed(ActionEvent e) {

                hydrate();

                cl.show(pnlRight, "ingreso");

                removeAllActionListeners(btnIngresar);
                btnIngresar.addActionListener(
                    new ActionListener() {
                      public void actionPerformed(ActionEvent e) {
                        Funcionario f = null;
                        Articulo a = null;
                        int cantidad = 0;
                        int codigo = 0;

                        if (lstFuncionarios.isSelectionEmpty()) {
                          showError("Ingrese un funcionario.");
                        } else if (lstArticulos.isSelectionEmpty()) {
                          showError("Ingrese un artículo.");
                        } else if (!Pattern.matches("[0-9]+", fieldIngresoCantidad.getText())) {
                          showError("Ingrese la cantidad.");
                        } else if (!Pattern.matches("[0-9]+", fieldIngresoCodigo.getText())) {
                          showError("Ingrese la codigo.");
                        } else {
                          f = sistema.getFuncionarios().get(lstFuncionarios.getSelectedIndex());
                          a = sistema.getArticulos().get(lstArticulos.getSelectedIndex());
                          cantidad = Integer.parseInt(fieldIngresoCantidad.getText());
                          codigo = Integer.parseInt(fieldIngresoCodigo.getText());
                        }
                        Posicion posicion = new Posicion(areaSeleccionada, _x, _y);

                        sistema.agregarCarga(codigo, a, f, cantidad, posicion);
                        hydrate();
                        showSuccess("Carga ingresada correctamente.");
                      }
                    });
              }
            });
      }
    }

    // Botones con carga
    sistema.getCargas().stream()
        .filter(c -> c.getPosicion().getArea() == areaSeleccionada)
        .forEach(
            c -> {
              Posicion p = c.getPosicion();

              JButton b = this.buttons[p.getFila()][p.getColumna()];

              removeAllActionListeners(b);
              b.addActionListener(
                  new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                      hydrate();

                      cl.show(pnlRight, "egreso");

                      lblCodigoContent.setText("" + c.getCodigo());
                      lblArticuloContent.setText(c.getArticulo().toString());
                      lblCantidadContent.setText("" + c.getCantidad());
                      lblFuncionarioContent.setText(c.getFuncionario().toString());

                      btnEgresar.addActionListener(
                          new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                              sistema.eliminarCarga(c);
                              hydrate();
                              showSuccess("Carga eliminada correctamente");
                            }
                          });
                    }
                  });
            });
  }

  public void showSuccess(String message) {
    JOptionPane.showMessageDialog(null, message, "Éxito", JOptionPane.INFORMATION_MESSAGE);
  }

  private void removeAllActionListeners(JButton b) {
    for (ActionListener al : b.getActionListeners()) {
      b.removeActionListener(al);
    }
  }

  private void setAreaSeleccionada(int area) {
    if (area >= 0 && area < 5) {
      this.areaSeleccionada = area;
      hydrate();
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    pnlLeft = new javax.swing.JPanel();
    lblArea = new javax.swing.JLabel();
    pnlGrid = new javax.swing.JPanel();
    pnlX = new javax.swing.JPanel();
    filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
        new java.awt.Dimension(32767, 32767));
    lblX1 = new javax.swing.JLabel();
    lblX2 = new javax.swing.JLabel();
    lblX3 = new javax.swing.JLabel();
    lblX4 = new javax.swing.JLabel();
    lblX5 = new javax.swing.JLabel();
    lblX6 = new javax.swing.JLabel();
    lblX7 = new javax.swing.JLabel();
    lblX8 = new javax.swing.JLabel();
    lblX9 = new javax.swing.JLabel();
    lblX10 = new javax.swing.JLabel();
    pnlY = new javax.swing.JPanel();
    lblY1 = new javax.swing.JLabel();
    lblY2 = new javax.swing.JLabel();
    lblY3 = new javax.swing.JLabel();
    lblY4 = new javax.swing.JLabel();
    lblY5 = new javax.swing.JLabel();
    lblY6 = new javax.swing.JLabel();
    lblY7 = new javax.swing.JLabel();
    lblY8 = new javax.swing.JLabel();
    lblY9 = new javax.swing.JLabel();
    lblY10 = new javax.swing.JLabel();
    lblY11 = new javax.swing.JLabel();
    lblY12 = new javax.swing.JLabel();
    pnlButtonsGrid = new javax.swing.JPanel();
    pnlButtonsChangeArea = new javax.swing.JPanel();
    btnPreviousArea = new javax.swing.JButton();
    btnNextArea = new javax.swing.JButton();
    pnlRight = new javax.swing.JPanel();
    pnlIngreso = new javax.swing.JPanel();
    lblIngreso = new javax.swing.JLabel();
    pnlIngresoContent = new javax.swing.JPanel();
    pnlFuncionarios = new javax.swing.JPanel();
    lblFuncionarios = new javax.swing.JLabel();
    scrollFuncionarios = new javax.swing.JScrollPane();
    lstFuncionarios = new javax.swing.JList<>();
    pnlArticulos = new javax.swing.JPanel();
    lblArticulos = new javax.swing.JLabel();
    scrollArticulos = new javax.swing.JScrollPane();
    lstArticulos = new javax.swing.JList<>();
    pnlCantidadCodigo = new javax.swing.JPanel();
    pnlCantidad = new javax.swing.JPanel();
    lblIngresoCantidad = new javax.swing.JLabel();
    fieldIngresoCantidad = new javax.swing.JTextField();
    pnlCodigo = new javax.swing.JPanel();
    lblIngresoCodigo = new javax.swing.JLabel();
    fieldIngresoCodigo = new javax.swing.JTextField();
    btnIngresar = new javax.swing.JButton();
    pnlEgreso = new javax.swing.JPanel();
    lblEgreso = new javax.swing.JLabel();
    pnlDatos = new javax.swing.JPanel();
    lblCodigo = new javax.swing.JLabel();
    lblCodigoContent = new javax.swing.JLabel();
    lblArticulo = new javax.swing.JLabel();
    lblArticuloContent = new javax.swing.JLabel();
    lblCantidad = new javax.swing.JLabel();
    lblCantidadContent = new javax.swing.JLabel();
    lblFuncionario = new javax.swing.JLabel();
    lblFuncionarioContent = new javax.swing.JLabel();
    btnEgresar = new javax.swing.JButton();
    fillerRight = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0),
        new java.awt.Dimension(32767, 32767));

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Ingreso/Egreso Carga");
    setMinimumSize(new java.awt.Dimension(1200, 500));
    setPreferredSize(new java.awt.Dimension(1200, 500));
    setResizable(false);
    getContentPane().setLayout(new java.awt.GridLayout(1, 2));

    pnlLeft.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    pnlLeft.setLayout(new java.awt.BorderLayout());

    lblArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblArea.setText("Area: A");
    pnlLeft.add(lblArea, java.awt.BorderLayout.NORTH);

    pnlGrid.setLayout(new java.awt.BorderLayout());

    pnlX.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 10, 0));
    pnlX.setLayout(new java.awt.GridLayout(1, 11));
    pnlX.add(filler1);

    lblX1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX1.setText("1");
    pnlX.add(lblX1);

    lblX2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX2.setText("2");
    pnlX.add(lblX2);

    lblX3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX3.setText("3");
    pnlX.add(lblX3);

    lblX4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX4.setText("4");
    pnlX.add(lblX4);

    lblX5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX5.setText("5");
    pnlX.add(lblX5);

    lblX6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX6.setText("6");
    pnlX.add(lblX6);

    lblX7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX7.setText("7");
    pnlX.add(lblX7);

    lblX8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX8.setText("8");
    pnlX.add(lblX8);

    lblX9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX9.setText("9");
    pnlX.add(lblX9);

    lblX10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblX10.setText("10");
    pnlX.add(lblX10);

    pnlGrid.add(pnlX, java.awt.BorderLayout.NORTH);

    pnlY.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
    pnlY.setLayout(new java.awt.GridLayout(12, 1));

    lblY1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY1.setText("1");
    pnlY.add(lblY1);

    lblY2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY2.setText("2");
    pnlY.add(lblY2);

    lblY3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY3.setText("3");
    pnlY.add(lblY3);

    lblY4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY4.setText("4");
    pnlY.add(lblY4);

    lblY5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY5.setText("5");
    pnlY.add(lblY5);

    lblY6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY6.setText("6");
    pnlY.add(lblY6);

    lblY7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY7.setText("7");
    pnlY.add(lblY7);

    lblY8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY8.setText("8");
    pnlY.add(lblY8);

    lblY9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY9.setText("9");
    pnlY.add(lblY9);

    lblY10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY10.setText("10");
    pnlY.add(lblY10);

    lblY11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY11.setText("11");
    pnlY.add(lblY11);

    lblY12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblY12.setText("12");
    pnlY.add(lblY12);

    pnlGrid.add(pnlY, java.awt.BorderLayout.WEST);

    pnlButtonsGrid.setBackground(new java.awt.Color(255, 255, 153));
    pnlButtonsGrid.setLayout(new java.awt.GridLayout(12, 10));
    pnlGrid.add(pnlButtonsGrid, java.awt.BorderLayout.CENTER);

    pnlLeft.add(pnlGrid, java.awt.BorderLayout.CENTER);

    pnlButtonsChangeArea.setLayout(new java.awt.GridLayout(1, 2));

    btnPreviousArea.setText("<<<");
    btnPreviousArea.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnPreviousAreaActionPerformed(evt);
      }
    });
    pnlButtonsChangeArea.add(btnPreviousArea);

    btnNextArea.setText(">>>");
    btnNextArea.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextAreaActionPerformed(evt);
      }
    });
    pnlButtonsChangeArea.add(btnNextArea);

    pnlLeft.add(pnlButtonsChangeArea, java.awt.BorderLayout.SOUTH);

    getContentPane().add(pnlLeft);

    pnlRight.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
    pnlRight.setToolTipText("");
    pnlRight.setLayout(new java.awt.CardLayout());

    pnlIngreso.setBackground(new java.awt.Color(51, 255, 51));
    pnlIngreso.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    pnlIngreso.setName(""); // NOI18N
    pnlIngreso.setLayout(new java.awt.BorderLayout());

    lblIngreso.setForeground(new java.awt.Color(0, 0, 0));
    lblIngreso.setText("Ingreso");
    pnlIngreso.add(lblIngreso, java.awt.BorderLayout.NORTH);

    pnlIngresoContent.setLayout(new java.awt.GridLayout(1, 3));

    pnlFuncionarios.setBackground(new java.awt.Color(51, 255, 51));
    pnlFuncionarios.setLayout(new java.awt.BorderLayout());

    lblFuncionarios.setBackground(new java.awt.Color(0, 0, 0));
    lblFuncionarios.setForeground(new java.awt.Color(0, 0, 0));
    lblFuncionarios.setText("Funcionarios");
    pnlFuncionarios.add(lblFuncionarios, java.awt.BorderLayout.NORTH);

    lstFuncionarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    scrollFuncionarios.setViewportView(lstFuncionarios);

    pnlFuncionarios.add(scrollFuncionarios, java.awt.BorderLayout.CENTER);

    pnlIngresoContent.add(pnlFuncionarios);

    pnlArticulos.setBackground(new java.awt.Color(51, 255, 51));
    pnlArticulos.setLayout(new java.awt.BorderLayout());

    lblArticulos.setForeground(new java.awt.Color(0, 0, 0));
    lblArticulos.setText("Artículos");
    pnlArticulos.add(lblArticulos, java.awt.BorderLayout.NORTH);

    lstArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    scrollArticulos.setViewportView(lstArticulos);

    pnlArticulos.add(scrollArticulos, java.awt.BorderLayout.CENTER);

    pnlIngresoContent.add(pnlArticulos);

    pnlCantidadCodigo.setBackground(new java.awt.Color(51, 255, 51));
    pnlCantidadCodigo.setLayout(new java.awt.GridLayout(3, 1));

    pnlCantidad.setBackground(new java.awt.Color(51, 255, 51));
    pnlCantidad.setLayout(new java.awt.GridLayout(2, 1));

    lblIngresoCantidad.setForeground(new java.awt.Color(0, 0, 0));
    lblIngresoCantidad.setText("Cantidad");
    pnlCantidad.add(lblIngresoCantidad);

    pnlCantidad.add(fieldIngresoCantidad);

    pnlCantidadCodigo.add(pnlCantidad);

    pnlCodigo.setBackground(new java.awt.Color(51, 255, 51));
    pnlCodigo.setLayout(new java.awt.GridLayout(2, 1));

    lblIngresoCodigo.setForeground(new java.awt.Color(0, 0, 0));
    lblIngresoCodigo.setText("Código");
    pnlCodigo.add(lblIngresoCodigo);
    pnlCodigo.add(fieldIngresoCodigo);

    pnlCantidadCodigo.add(pnlCodigo);

    btnIngresar.setText("Ingresar");
    pnlCantidadCodigo.add(btnIngresar);

    pnlIngresoContent.add(pnlCantidadCodigo);

    pnlIngreso.add(pnlIngresoContent, java.awt.BorderLayout.CENTER);

    pnlRight.add(pnlIngreso, "ingreso");

    pnlEgreso.setBackground(new java.awt.Color(51, 153, 255));
    pnlEgreso.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    pnlEgreso.setName(""); // NOI18N
    pnlEgreso.setLayout(new java.awt.BorderLayout());

    lblEgreso.setForeground(new java.awt.Color(0, 0, 0));
    lblEgreso.setText("Egreso");
    lblEgreso.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlEgreso.add(lblEgreso, java.awt.BorderLayout.NORTH);

    pnlDatos.setBackground(new java.awt.Color(51, 153, 255));
    pnlDatos.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    pnlDatos.setLayout(new java.awt.GridLayout(4, 2));

    lblCodigo.setForeground(new java.awt.Color(0, 0, 0));
    lblCodigo.setLabelFor(lblCodigoContent);
    lblCodigo.setText("Código");
    lblCodigo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblCodigo);

    lblCodigoContent.setForeground(new java.awt.Color(0, 0, 0));
    lblCodigoContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblCodigoContent);

    lblArticulo.setForeground(new java.awt.Color(0, 0, 0));
    lblArticulo.setLabelFor(lblArticuloContent);
    lblArticulo.setText("Artículo");
    lblArticulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblArticulo);

    lblArticuloContent.setForeground(new java.awt.Color(0, 0, 0));
    lblArticuloContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblArticuloContent);

    lblCantidad.setForeground(new java.awt.Color(0, 0, 0));
    lblCantidad.setLabelFor(lblCantidadContent);
    lblCantidad.setText("Cantidad");
    lblCantidad.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblCantidad);

    lblCantidadContent.setForeground(new java.awt.Color(0, 0, 0));
    lblCantidadContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblCantidadContent);

    lblFuncionario.setForeground(new java.awt.Color(0, 0, 0));
    lblFuncionario.setLabelFor(lblFuncionarioContent);
    lblFuncionario.setText("Funcionario");
    lblFuncionario.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblFuncionario);

    lblFuncionarioContent.setForeground(new java.awt.Color(0, 0, 0));
    lblFuncionarioContent.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    pnlDatos.add(lblFuncionarioContent);

    pnlEgreso.add(pnlDatos, java.awt.BorderLayout.CENTER);

    btnEgresar.setText("Egresar");
    btnEgresar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnEgresarActionPerformed(evt);
      }
    });
    pnlEgreso.add(btnEgresar, java.awt.BorderLayout.SOUTH);

    pnlRight.add(pnlEgreso, "egreso");
    pnlRight.add(fillerRight, "filler");

    getContentPane().add(pnlRight);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnPreviousAreaActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_PreviousAreaButtonActionPerformed
    if (this.areaSeleccionada > 0) {
      setAreaSeleccionada(this.areaSeleccionada - 1);
    }
  } // GEN-LAST:event_PreviousAreaButtonActionPerformed

  private void btnNextAreaActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_NextAreaButtonActionPerformed
    if (this.areaSeleccionada < 5) {
      setAreaSeleccionada(areaSeleccionada + 1);
    }
  } // GEN-LAST:event_NextAreaButtonActionPerformed

  private void btnEgresarActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_btnEgresarActionPerformed
  } // GEN-LAST:event_btnEgresarActionPerformed

  public void showError(String msg) {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }
  // /**
  // * @param args the command line arguments
  // */
  // public static void main(String args[]) {
  // /* Set the Nimbus look and feel */
  // //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code
  // (optional) ">
  // /* If Nimbus (introduced in Java SE 6) is not available, stay with the
  // default look and
  // feel.
  // * For details see
  // http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
  // */
  // try {
  // for (javax.swing.UIManager.LookAndFeelInfo info :
  // javax.swing.UIManager.getInstalledLookAndFeels()) {
  // if ("Nimbus".equals(info.getName())) {
  // javax.swing.UIManager.setLookAndFeel(info.getClassName());
  // break;
  // }
  // }
  // } catch (ClassNotFoundException ex) {
  //
  // java.util.logging.Logger.getLogger(IngresoEgresoCarga.class.getName()).log(java.util.logging.Level.SEVERE,
  // null, ex);
  // } catch (InstantiationException ex) {
  //
  // java.util.logging.Logger.getLogger(IngresoEgresoCarga.class.getName()).log(java.util.logging.Level.SEVERE,
  // null, ex);
  // } catch (IllegalAccessException ex) {
  //
  // java.util.logging.Logger.getLogger(IngresoEgresoCarga.class.getName()).log(java.util.logging.Level.SEVERE,
  // null, ex);
  // } catch (javax.swing.UnsupportedLookAndFeelException ex) {
  //
  // java.util.logging.Logger.getLogger(IngresoEgresoCarga.class.getName()).log(java.util.logging.Level.SEVERE,
  // null, ex);
  // }
  // //</editor-fold>
  //
  // /* Create and display the form */
  // java.awt.EventQueue.invokeLater(new Runnable() {
  // public void run() {
  // new IngresoEgresoCarga().setVisible(true);
  // }
  // });
  // }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnEgresar;
  private javax.swing.JButton btnIngresar;
  private javax.swing.JButton btnNextArea;
  private javax.swing.JButton btnPreviousArea;
  private javax.swing.JTextField fieldIngresoCantidad;
  private javax.swing.JTextField fieldIngresoCodigo;
  private javax.swing.Box.Filler filler1;
  private javax.swing.Box.Filler fillerRight;
  private javax.swing.JLabel lblArea;
  private javax.swing.JLabel lblArticulo;
  private javax.swing.JLabel lblArticuloContent;
  private javax.swing.JLabel lblArticulos;
  private javax.swing.JLabel lblCantidad;
  private javax.swing.JLabel lblCantidadContent;
  private javax.swing.JLabel lblCodigo;
  private javax.swing.JLabel lblCodigoContent;
  private javax.swing.JLabel lblEgreso;
  private javax.swing.JLabel lblFuncionario;
  private javax.swing.JLabel lblFuncionarioContent;
  private javax.swing.JLabel lblFuncionarios;
  private javax.swing.JLabel lblIngreso;
  private javax.swing.JLabel lblIngresoCantidad;
  private javax.swing.JLabel lblIngresoCodigo;
  private javax.swing.JLabel lblX1;
  private javax.swing.JLabel lblX10;
  private javax.swing.JLabel lblX2;
  private javax.swing.JLabel lblX3;
  private javax.swing.JLabel lblX4;
  private javax.swing.JLabel lblX5;
  private javax.swing.JLabel lblX6;
  private javax.swing.JLabel lblX7;
  private javax.swing.JLabel lblX8;
  private javax.swing.JLabel lblX9;
  private javax.swing.JLabel lblY1;
  private javax.swing.JLabel lblY10;
  private javax.swing.JLabel lblY11;
  private javax.swing.JLabel lblY12;
  private javax.swing.JLabel lblY2;
  private javax.swing.JLabel lblY3;
  private javax.swing.JLabel lblY4;
  private javax.swing.JLabel lblY5;
  private javax.swing.JLabel lblY6;
  private javax.swing.JLabel lblY7;
  private javax.swing.JLabel lblY8;
  private javax.swing.JLabel lblY9;
  private javax.swing.JList<String> lstArticulos;
  private javax.swing.JList<String> lstFuncionarios;
  private javax.swing.JPanel pnlArticulos;
  private javax.swing.JPanel pnlButtonsChangeArea;
  private javax.swing.JPanel pnlButtonsGrid;
  private javax.swing.JPanel pnlCantidad;
  private javax.swing.JPanel pnlCantidadCodigo;
  private javax.swing.JPanel pnlCodigo;
  private javax.swing.JPanel pnlDatos;
  private javax.swing.JPanel pnlEgreso;
  private javax.swing.JPanel pnlFuncionarios;
  private javax.swing.JPanel pnlGrid;
  private javax.swing.JPanel pnlIngreso;
  private javax.swing.JPanel pnlIngresoContent;
  private javax.swing.JPanel pnlLeft;
  private javax.swing.JPanel pnlRight;
  private javax.swing.JPanel pnlX;
  private javax.swing.JPanel pnlY;
  private javax.swing.JScrollPane scrollArticulos;
  private javax.swing.JScrollPane scrollFuncionarios;
  // End of variables declaration//GEN-END:variables
}
