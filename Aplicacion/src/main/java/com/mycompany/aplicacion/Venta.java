/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;

import java.time.LocalDateTime;

public class Venta {
    private final String id;
    private final String nombreCliente;
    private final Evento evento;//asociacion
    private final Vendedor vendedor;//asociacion una venta se asocia con un vendedor que la realiza
    private final int cantidad;
    private final double precioTotal;
    private final LocalDateTime fechaHora;

    public Venta(String id, String nombreCliente, Evento evento, Vendedor vendedor, int cantidad) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.evento = evento;//la asociacion se forma aqui,ya que venta no crea el evento solo una referencia existente..
        this.vendedor = vendedor;//la asociacion se forma aqui , venta solo usa al vendedor..
        this.cantidad = cantidad;
        this.precioTotal = evento.getNivel().getPrecio() * cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getNombreCliente() { return nombreCliente; }
    public Evento getEvento() { return evento; }
    public Vendedor getVendedor() { return vendedor; }
    public int getCantidad() { return cantidad; }
    public double getPrecioTotal() { return precioTotal; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return "Venta[" + id + "] Cliente: " + nombreCliente +
                " | Evento: " + evento.getNombre() +
                " | Nivel: " + evento.getNivel() +
                " | Precio Total: $" + precioTotal +
                " | Cantidad: " + cantidad +
                " | Vendedor: " + vendedor.getNombre() +
                " | Fecha: " + fechaHora;
    }

    String getClienteNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
