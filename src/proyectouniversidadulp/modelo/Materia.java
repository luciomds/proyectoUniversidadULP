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
public class Materia {
    private int id_materia;
    private String nombre;
    private int cuatrimestre;
    private boolean activo;

    public Materia() {
    }

    public Materia(int id_materia, String nombre, int cuatrimestre) {
        this.id_materia = id_materia;
        this.nombre = nombre;
        this.cuatrimestre = cuatrimestre;
        this.activo=true;
    }

    public Materia(String nombre, int cuatrimestre, boolean activo) {
        
        this.nombre = nombre;
        this.cuatrimestre = cuatrimestre;
        this.activo = activo;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Materia{" + "id_materia=" + id_materia + ", nombre=" + nombre + ", cuatrimestre=" + cuatrimestre + ", activo=" + activo + '}';
    }
    
    
    
    
}
