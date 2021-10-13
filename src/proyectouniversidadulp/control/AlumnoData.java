package proyectouniversidadulp.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;

public class AlumnoData {

    private Connection con;

    public AlumnoData(Conexion conexion) {
        try {
            this.con = conexion.getConexion();
            System.out.println("Conectado");
        } catch (SQLException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno(legajo, nombre, fechaNac, activo) VALUES (?,?,?,?)";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setInt(1, alumno.getLegajo());
                ps.setString(2, alumno.getNombre());
                ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
                ps.setBoolean(4, alumno.isActivo());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (id_alumno)
                if (rs.next()) {
                    System.out.println("Alumno " + alumno.getNombre() + ", cargado correctamente.");
                    alumno.setId_alumno(rs.getInt(1));
                    //alumno.setId_alumno(rs.getInt("id_alumno"));
                    System.out.println("id del alumno: " + alumno.getId_alumno());
                    
                }
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar \n" + ex);
        }
    }
    
    public void borrarAlumno(int id) {
        String sql = "DELETE FROM alumno WHERE id_alumno=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void activarAlumno (int id) { 
        String sql = "UPDATE alumno SET activo=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al activar \n" + ex);
        }
    }
    
    public void desactivarAlumno (int id) { //NUEVO METODO PARA BORRADO LOGICO
        String sql = "UPDATE alumno SET activo=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al desactivar \n" + ex);
        }
    }

    public void actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET legajo=?, nombre=?, fechaNac=? WHERE id_alumno=?";

        try {
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  //Prepara la sentencia para SQL
                ps.setInt(1, alumno.getLegajo());
                ps.setString(2, alumno.getNombre());
                ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
                //ps.setBoolean(4, alumno.isActivo()); SACO EL CAMPO ACTIVO
                ps.setInt(4, alumno.getId_alumno());

                ps.executeUpdate(); //NO PONER PARAMETROS
                ResultSet rs = ps.getGeneratedKeys(); //Recupero el ID (id_alumno)
                System.out.println("Alumno " + alumno.getNombre() + ", actualizado correctamente.");

                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar \n" + ex);
        }
    }

    public Alumno buscarAlumno(int id_alumno) {
        Alumno a = null;

        String sql = "SELECT * FROM alumno WHERE id_alumno=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ps.execute();

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alumno();
                a.setId_alumno(rs.getInt(1));
                a.setLegajo(rs.getInt(2));
                a.setNombre(rs.getString(3));
                a.setFechaNac(rs.getDate(4).toLocalDate()); //Convierte un Date a LocalDate                                     
                a.setActivo(rs.getBoolean(5));

            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }

        return a;
    }

    public List<Alumno> obtenerAlumnos() {
        List<Alumno> alumnos = new ArrayList<>();
        Alumno a = null;

        String sql = "SELECT * FROM alumno";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            //ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setId_alumno(rs.getInt(1));
                a.setLegajo(rs.getInt(2));
                a.setNombre(rs.getString(3));
                a.setFechaNac(rs.getDate(4).toLocalDate()); //Convierte un Date a LocalDate                                     
                a.setActivo(rs.getBoolean(5));
                alumnos.add(a);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return alumnos;
    }
    
    public List<Alumno> obtenerAlumnosActivos() {
        List<Alumno> alumnos = new ArrayList<>();
        Alumno a = null;

        String sql = "SELECT * FROM alumno WHERE activo=true";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            //ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = new Alumno();
                a.setId_alumno(rs.getInt(1));
                a.setLegajo(rs.getInt(2));
                a.setNombre(rs.getString(3));
                a.setFechaNac(rs.getDate(4).toLocalDate()); //Convierte un Date a LocalDate                                     
                a.setActivo(rs.getBoolean(5));
                alumnos.add(a);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar");

        }        
        return alumnos;
    }
    
    

}
