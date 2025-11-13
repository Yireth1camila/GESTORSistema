/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;



public enum Nivel {
    VIP(100.0, "Dorado"),
    PREMIUM(70.0, "Plata"),
    GENERAL(40.0, "Azul");

    private final double precio;
    private final String color;

    Nivel(double precio, String color) {
        this.precio = precio;
        this.color = color;
    }

    public double getPrecio() { return precio; }
    public String getColor() { return color; }

    @Override
    public String toString() {
        return this.name();
    }
}
