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
import model.Categoria;
import model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    
    private final Conexion conexion;

    public ProductoDao() {
        this.conexion = new Conexion();
    }

   
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO producto (nombre, precio, cantidad, id_categoria) VALUES (?, ?, ?, ?)";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    
    public List<Producto> listar() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.id_categoria, c.nombre AS categoria_nombre " +
                     "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria";
        
        try (Connection cn = conexion.conectar();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                
                // Construcción del objeto Categoria interno
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("categoria_nombre"));
                
                p.setCategoria(cat);
                listaProductos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        
        return listaProductos;
    }

    
    public Producto buscarPorId(int id) {
        Producto p = null;
        String sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.id_categoria, c.nombre AS categoria_nombre " +
                     "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id WHERE p.id_producto = ?";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Producto();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setCantidad(rs.getInt("cantidad"));
                    
                    Categoria cat = new Categoria();
                    cat.setIdCategoria(rs.getInt("id_categoria"));
                    cat.setNombre(rs.getString("categoria_nombre"));
                    
                    p.setCategoria(cat);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por ID: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        
        return p;
    }

  
    public boolean actualizar(Producto p) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, cantidad = ?, id_categoria = ? WHERE id_producto = ?";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getCategoria().getIdCategoria());
            ps.setInt(5, p.getIdProducto());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }

    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }
    
    public List<Producto> listarPorCategoria(int idCategoria) {
        List<Producto> listaProductosPorCategoria = new ArrayList<>();
        String sql;
        
        if (idCategoria == -1) {
            sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.id_categoria, c.nombre AS categoria_nombre " +
                  "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria";
        } else {

            sql = "SELECT p.id_producto, p.nombre, p.precio, p.cantidad, p.id_categoria, c.nombre AS categoria_nombre " +
                  "FROM producto p INNER JOIN categoria c ON p.id_categoria = c.id_categoria " +
                  "WHERE p.id_categoria = ?";
        }
        
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            if (idCategoria != -1) {
                ps.setInt(1, idCategoria);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setCantidad(rs.getInt("cantidad"));
                    
                    Categoria cat = new Categoria();
                    cat.setIdCategoria(rs.getInt("id_categoria"));
                    cat.setNombre(rs.getString("categoria_nombre"));
                    
                    p.setCategoria(cat);
                    listaProductosPorCategoria.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos por categoría: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        
        return listaProductosPorCategoria;
    }
    
    public boolean restarStock(int idProducto, int cantidadVendida) {
        String sql = "UPDATE producto SET cantidad = cantidad - ? WHERE id_producto = ?";
        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            
            ps.setInt(1, cantidadVendida);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al restar stock: " + e.getMessage());
            return false;
        } finally {
            conexion.desconectar();
        }
    }
}