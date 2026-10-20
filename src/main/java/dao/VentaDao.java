/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author jonap
 */
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VentaDao {
    private Conexion conexion = new Conexion();

    
    public boolean registrarVenta(int idProducto, int idCategoria, String nombre, int cantidad, double precioTotal) {
        String sql = "INSERT INTO ventas (id_producto, id_categoria, nombre, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, idProducto);
            ps.setInt(2, idCategoria);
            ps.setString(3, nombre);
            ps.setInt(4, cantidad);
            ps.setDouble(5, precioTotal); // el precio calculado
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar la venta: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }
    
    public List<Object[]> listarVentas() {
        List<Object[]> listaVentas = new ArrayList<>();
        String sql = "SELECT nombre, cantidad, precio FROM ventas";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getInt("cantidad");
                fila[2] = rs.getDouble("precio");
                listaVentas.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        
        return listaVentas;
    }
}
