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

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia(nombre, cuatrimestre, activo) VALUES (?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getCuatrimestre());                                
                ps.setBoolean(3, materia.isActivo());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (id_alumno)
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
}
