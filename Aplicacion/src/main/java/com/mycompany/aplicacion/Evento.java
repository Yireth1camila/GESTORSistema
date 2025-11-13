/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;


import java.time.LocalDateTime;

public class Evento {
    private final String id;
    private final String nombre;
    private final String artista;
    private final LocalDateTime fechaHora;
    private final Nivel nivel;//agregacion

    public Evento(String id, String nombre, String artista, LocalDateTime fechaHora, Nivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.fechaHora = fechaHora;
        this.nivel = nivel;//agregacion
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getArtista() { return artista; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Nivel getNivel() { return nivel; }

    @Override
    public String toString() {
        return id + " | " + nombre + " - " + artista + " | " + fechaHora + " | Nivel: " +
                nivel + " (" + nivel.getColor() + ") → $" + nivel.getPrecio();
    }
}
