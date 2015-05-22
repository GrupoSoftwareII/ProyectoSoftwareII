/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Modulo 2
 */
public class Estudiante {
    private String codigo;
    private String grupo;
    private String prueba;
    private int puntos;
    private String nombre;
    private String apellido;
    private String documento;

    public Estudiante() {
    }

    public Estudiante(String codigo, String grupo, int puntos, String nombre, String apellido, String documento) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.puntos = puntos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
}
