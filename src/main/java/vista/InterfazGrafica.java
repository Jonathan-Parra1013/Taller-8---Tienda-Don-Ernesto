/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

/**
 *
 * @author jonap
 */

import dao.CategoriaDao;
import dao.ProductoDao;
import model.Categoria;
import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
public class InterfazGrafica extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfazGrafica.class.getName());
    private CategoriaDao categoriaDAO;
    private ProductoDao productoDAO;
    private int idProductoSeleccionado = -1;
    /**
     * Creates new form InterfazGrafica
     */
    public InterfazGrafica() {
        initComponents();
        
        
        categoriaDAO = new CategoriaDao();
        productoDAO = new ProductoDao();
        
        cargarCategorias();
        cargarTablaProductos();
        
        cargarFiltroCategorias();
        TablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFilaTabla();
            }
        });
        
        TablaProductos.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                try {
                    int cantidad = Integer.parseInt(table.getValueAt(row, 3).toString());
                    if (cantidad < 5) {
                        c.setBackground(new java.awt.Color(255, 200, 200)); // aqui señala en rojo si su cantidas es menor a 5 :b
                        c.setForeground(java.awt.Color.BLACK);
                    } else {
                        if (isSelected) {
                            c.setBackground(table.getSelectionBackground());
                            c.setForeground(table.getSelectionForeground());
                        } else {
                            c.setBackground(java.awt.Color.WHITE);
                            c.setForeground(java.awt.Color.BLACK);
                        }
                    }
                } catch (Exception e) {
                    c.setBackground(java.awt.Color.WHITE);
                }
                return c;
            }
        });
    }
    
    private void cargarCategorias() {
        CajaCategoria.removeAllItems();
        List<Categoria> categorias = categoriaDAO.listar();
        
        if (categorias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las categorías :c", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        for (Categoria c : categorias) {
            
                CajaCategoria.addItem(c);
        }
    }

 
    private void cargarTablaProductos() {
        DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar
        
        List<Producto> productos = productoDAO.listar();
        
        for (Producto p : productos) {
            Object[] fila = new Object[5];
            fila[0] = p.getIdProducto();
            fila[1] = p.getNombre();
            fila[2] = p.getPrecio();
            fila[3] = p.getCantidad();
            fila[4] = p.getCategoria().getNombre();
            modelo.addRow(fila);
        }
    }
    
    private void cargarFiltroCategorias() {
        CajaFiltroCategoria.removeAllItems();
        
        Categoria todas = new Categoria();
        todas.setIdCategoria(-1);
        todas.setNombre("Todas");
        CajaFiltroCategoria.addItem(todas);
        
        // aparecen las categorias de la base de datos :b
        List<Categoria> categorias = categoriaDAO.listar();
        for (Categoria c : categorias) {
            
            if (!c.getNombre().equalsIgnoreCase("Ninguno")) {
                CajaFiltroCategoria.addItem(c);
            }
        }
    }

   
    private void seleccionarFilaTabla() {
        int fila = TablaProductos.getSelectedRow();
        if (fila >= 0) {
            idProductoSeleccionado = Integer.parseInt(TablaProductos.getValueAt(fila, 0).toString());
            TextoNombre.setText(TablaProductos.getValueAt(fila, 1).toString());
            TextoPrecio.setText(TablaProductos.getValueAt(fila, 2).toString());
            TextoCantidad.setText(TablaProductos.getValueAt(fila, 3).toString());
            
            String nombreCategoria = TablaProductos.getValueAt(fila, 4).toString();
            for (int i = 0; i < CajaCategoria.getItemCount(); i++) {
                Categoria c = (Categoria) CajaCategoria.getItemAt(i);
                if (c.getNombre().equals(nombreCategoria)) {
                    CajaCategoria.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

  
    private void limpiarFormulario() {
        idProductoSeleccionado = -1;
        TextoNombre.setText("");
        TextoPrecio.setText("");
        TextoCantidad.setText("");
        if (CajaCategoria.getItemCount() > 0) {
            CajaCategoria.setSelectedIndex(0);
        }
        TablaProductos.clearSelection();
    }
    
    public void actualizarTablaExterna() {

        cargarTablaProductos();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TextoPrecio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TextoCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextoNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CajaCategoria = new javax.swing.JComboBox<>();
        BtnGuardar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CajaFiltroCategoria = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        BtnVender = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setText("TIENDA DE BARRIO DON HERNESTO - INVENTARIO :D");

        jLabel2.setText("Nombre:");
        jLabel2.setMaximumSize(new java.awt.Dimension(50, 16));

        TextoPrecio.addActionListener(this::TextoPrecioActionPerformed);

        jLabel3.setText("Precio:");
        jLabel3.setMaximumSize(new java.awt.Dimension(50, 16));

        TextoCantidad.addActionListener(this::TextoCantidadActionPerformed);

        jLabel4.setText("Cantidad:");
        jLabel4.setMaximumSize(new java.awt.Dimension(50, 16));

        TextoNombre.addActionListener(this::TextoNombreActionPerformed);

        jLabel5.setText("Categoria:");
        jLabel5.setMaximumSize(new java.awt.Dimension(50, 16));

        CajaCategoria.addActionListener(this::CajaCategoriaActionPerformed);

        BtnGuardar.setText("Guardar");
        BtnGuardar.addActionListener(this::BtnGuardarActionPerformed);

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(this::BtnActualizarActionPerformed);

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(this::BtnEliminarActionPerformed);

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(this::BtnLimpiarActionPerformed);

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "PRECIO", "CANTIDAD", "CATEGORIA"
            }
        ));
        jScrollPane1.setViewportView(TablaProductos);

        jLabel6.setText("TABLA PRODUCTOS :b");

        jLabel7.setText("FILTRO");

        CajaFiltroCategoria.addActionListener(this::CajaFiltroCategoriaActionPerformed);

        jLabel8.setText("VENTA");

        BtnVender.setText("VENDER");
        BtnVender.addActionListener(this::BtnVenderActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TextoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CajaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(BtnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnLimpiar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(TextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CajaFiltroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(188, 188, 188)
                            .addComponent(BtnVender))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CajaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CajaFiltroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnVender)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextoPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoPrecioActionPerformed

    private void TextoCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoCantidadActionPerformed

    private void TextoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextoNombreActionPerformed

    private void CajaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajaCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajaCategoriaActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        if (TextoNombre.getText().trim().isEmpty() || TextoPrecio.getText().trim().isEmpty() || TextoCantidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = TextoNombre.getText().trim();
            double precio = Double.parseDouble(TextoPrecio.getText().trim());
            int cantidad = Integer.parseInt(TextoCantidad.getText().trim());
            
            if (precio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Categoria categoriaSeleccionada = (Categoria) CajaCategoria.getSelectedItem();

            
            if (categoriaSeleccionada.getIdCategoria() == 5) { 
                JOptionPane.showMessageDialog(this, "Debe escoger una categoría válida. No se puede guardar con 'Ninguno'.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            Producto p = new Producto();
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setCantidad(cantidad);
            p.setCategoria(categoriaSeleccionada);

            if (productoDAO.insertar(p)) {
                JOptionPane.showMessageDialog(this, "Producto guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaProductos();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el producto. Verifique la conexión a la BD.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio y la cantidad deben ser valores numéricos válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        if (idProductoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para actualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (TextoNombre.getText().trim().isEmpty() || TextoPrecio.getText().trim().isEmpty() || TextoCantidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nombre = TextoNombre.getText().trim();
            double precio = Double.parseDouble(TextoPrecio.getText().trim());
            int cantidad = Integer.parseInt(TextoCantidad.getText().trim());
            
            Categoria categoriaSeleccionada = (Categoria) CajaCategoria.getSelectedItem();

            Producto p = new Producto();
            p.setIdProducto(idProductoSeleccionado);
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setCantidad(cantidad);
            p.setCategoria(categoriaSeleccionada);

            if (productoDAO.actualizar(p)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaProductos();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio y la cantidad deben ser valores numéricos válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        if (idProductoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Pide confirmación antes de borrar
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (productoDAO.eliminar(idProductoSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.", "exito", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaProductos();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto.", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void CajaFiltroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajaFiltroCategoriaActionPerformed
       if (CajaFiltroCategoria.getSelectedItem() != null) {
        
            Object selectedItem = CajaFiltroCategoria.getSelectedItem();
        
            if (selectedItem instanceof Categoria) {
                Categoria categoriaSeleccionada = (Categoria) selectedItem;
                int idCategoriaSeleccionada = categoriaSeleccionada.getIdCategoria();
            
                DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
                modelo.setRowCount(0); // se limpia la tabla
            
                List<Producto> productosFiltrados = productoDAO.listarPorCategoria(idCategoriaSeleccionada);
            
                for (Producto p : productosFiltrados) {
                    Object[] fila = new Object[5];
                    fila[0] = p.getIdProducto();
                    fila[1] = p.getNombre();
                    fila[2] = p.getPrecio();
                    fila[3] = p.getCantidad();
                    fila[4] = p.getCategoria().getNombre();
                    modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_CajaFiltroCategoriaActionPerformed

    private void BtnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVenderActionPerformed
        InterfazGraficaVentas ventas = new InterfazGraficaVentas(this);
        
        ventas.setVisible(true);
    }//GEN-LAST:event_BtnVenderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnVender;
    private javax.swing.JComboBox<Categoria> CajaCategoria;
    private javax.swing.JComboBox<Categoria> CajaFiltroCategoria;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTextField TextoCantidad;
    private javax.swing.JTextField TextoNombre;
    private javax.swing.JTextField TextoPrecio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
