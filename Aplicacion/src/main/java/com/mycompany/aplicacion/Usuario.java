/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;


public class Usuario {
    protected String nombreUsuario;
    protected String contrasena;
    protected String correo;

    //dependencias
    public Usuario(String nombreUsuario, String contrasena, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getCorreo() { return correo; }
    public boolean verificarContrasena(String pw) { return contrasena.equals(pw); }
}
