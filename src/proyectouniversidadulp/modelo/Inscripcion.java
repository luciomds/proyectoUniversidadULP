/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectouniversidadulp.modelo;

/**
 *
 * @author Usuario
 */
public class Inscripcion {
    private int id_inscripcion;
    private Alumno id_alumno;
    private Materia id_materia;
    private double nota;

    public Inscripcion() {
    }    
    
    public Inscripcion(Alumno id_alumno, Materia id_materia) {
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;        
    }

    public Inscripcion(int id_inscripcion, Alumno id_alumno, Materia id_materia, double nota) {
        this.id_inscripcion = id_inscripcion;
        this.id_alumno = id_alumno;
        this.id_materia = id_materia;
        this.nota = nota;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Alumno getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Alumno id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Materia getId_materia() {
        return id_materia;
    }

    public void setId_materia(Materia id_materia) {
        this.id_materia = id_materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
    
    
    
}
