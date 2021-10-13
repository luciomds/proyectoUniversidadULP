/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;
import proyectouniversidadulp.modelo.Materia;

/**
 *
 * @author daniel
 */
public class MateriaData {
    
    private Connection con;
    
    public MateriaData(Conexion conexion) {
        try {
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    
    
    public void actualizarMateria(Materia materia){
        
        String query = "UPDATE materia SET nombre = ?, anio = ?, activo = ? WHERE idMateria = ?";
        
        try{
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(4, materia.getId_materia());
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getCuatrimestre());
            ps.setBoolean(3, materia.isActivo());
            
            ps.executeUpdate();
            
            System.out.println("Materia actualizada correctamente.");
            
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al actualizar.");
        }
    }
    
}
