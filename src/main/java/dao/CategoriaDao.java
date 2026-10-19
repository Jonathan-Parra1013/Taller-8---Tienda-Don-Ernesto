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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    
    private Conexion conexion;

    public CategoriaDao() {
        this.conexion = new Conexion();
    }
    
    public List<Categoria> listar() {
        List<Categoria> listaCategorias = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre FROM categoria ORDER BY CASE WHEN nombre = 'Ninguno' THEN 0 ELSE 1 END, nombre ASC";
        
        try (Connection cn = conexion.conectar();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria")); 
                categoria.setNombre(rs.getString("nombre"));
                listaCategorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar categorías: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
        
        return listaCategorias;
    }
}