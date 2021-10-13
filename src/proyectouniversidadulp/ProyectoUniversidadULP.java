
package proyectouniversidadulp;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import proyectouniversidadulp.control.AlumnoData;
import proyectouniversidadulp.control.MateriaData;
import proyectouniversidadulp.modelo.Alumno;
import proyectouniversidadulp.modelo.Conexion;
import proyectouniversidadulp.modelo.Materia;


public class ProyectoUniversidadULP {

    public static void main(String[] args) throws ClassNotFoundException {
        //Conexion conexion = new Conexion("jdbc:mysql://localhost/universidad","root","");
        Conexion conexion = new Conexion(); //CREANDO LA CONEXION   

        
        
       // List<Alumno> alumnos = new ArrayList<>();
        
        

        AlumnoData ad = new AlumnoData(conexion);
        MateriaData md = new MateriaData  (conexion);
        
        List<Alumno> alumnos = new ArrayList<>();
        
        Alumno a = new Alumno(2225, "Marcos Suarez", LocalDate.of(2000, Month.NOVEMBER, 28)); //CREANDO ALUMNOS
        Alumno b = new Alumno(9999, "Jose Sosa", LocalDate.of(1995, Month.MARCH, 12));
        ad.guardarAlumno(a); //AGREGANDO ALUMNOS
        ad.guardarAlumno(b);
        ad.actualizarAlumno(a);
        System.out.println("buscar alumno:"+ad.buscarAlumno(11)); //BUSCAR ALUMNOS
        alumnos=ad.obtenerAlumnos();
        System.out.println("lista:");
        for(Alumno al : alumnos){
            System.out.println(al);
        } 


        
        
        Materia m1 = new Materia(0123, "Laboratorio I", 2);
        
        md.guardarMateria(m1);
        md.darDeBajaMateria(m1.getId_materia());
          List<Materia> materias = new ArrayList<>();
        materias= md.obtenerMaterias();
         for(Materia m : materias){
            System.out.println(m);            
        
        }
         System.out.println(a.getId_alumno());
    
}
}
