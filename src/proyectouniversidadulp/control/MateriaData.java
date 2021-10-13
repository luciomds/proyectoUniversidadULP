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
import java.util.ArrayList;
import java.util.List;

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
    


    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia(nombre, cuatrimestre, activo) VALUES (?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getCuatrimestre());                                
                ps.setBoolean(3, materia.isActivo());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (id_materia)
                if (rs.next()) {
                    System.out.println("Materia " + materia.getNombre() + ", cargada correctamente.");
                    materia.setId_materia(rs.getInt(1));
                    //alumno.setId_alumno(rs.getInt("id_alumno"));
                    System.out.println("id de la materia: " + materia.getId_materia());
                }
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar \n" + ex);
        }
    }
    
   public void darDeBajaMateria(int id){
       String sql = "UPDATE materia SET  activo=? WHERE idMateria = ?";
       
       try {
           PreparedStatement ps = con.prepareStatement(sql);
              ps.setBoolean(1, false);
              ps.setInt(2, id);
              
            ps.executeUpdate();
            
            System.out.println("Materia dada de baja correctamente.");
            
            ps.close();
            
        }catch(SQLException sqlE){
            System.out.println("Error al dar de baja.");
        }
    }
    
   
   
      public List<Materia> obtenerMaterias() {
        List<Materia> materias = new ArrayList<>();
        Materia m = null;

        String sql = "SELECT * FROM materia  ";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               m = new Materia();
                m.setId_materia(rs.getInt(1));
                m.setNombre(rs.getString(2));
                m.setCuatrimestre(rs.getInt(3));
                m.setActivo(rs.getBoolean(4));
               materias.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return materias;
    }

}
    


