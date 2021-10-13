package proyectouniversidadulp.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;
import proyectouniversidadulp.modelo.Inscripcion;

public class InscripcionData {
    private Connection con;
    
    public InscripcionData(Conexion conexion) {
        try {
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }
    
    public void guardarInscripcion(Inscripcion ins) {
        String sql = "INSERT INTO inscripcion (id_alumno, id_materia, nota) VALUES (?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setInt(1, ins.getId_alumno().getId_alumno());
                ps.setInt(2, ins.getId_materia().getId_materia());
                ps.setDouble(3, ins.getNota());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    //System.out.println("Alumno " + alumno.getNombre() + ", cargado correctamente.");
                    ins.setId_inscripcion(rs.getInt(1));
                    //alumno.setId_alumno(rs.getInt("id_alumno"));
                    System.out.println("id de la materia: " + ins.getId_inscripcion());
                    
                }
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar \n" + ex);
        }
    }

}
