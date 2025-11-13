/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;


import java.time.LocalDateTime;
import java.util.*;

public class AlmacenDatos {
    public static final Map<String, Usuario> usuarios = new HashMap<>();//agregacion
    public static final Map<String, Administrador> administradores = new HashMap<>();//agregacion
    public static final Map<String, Vendedor> vendedores = new HashMap<>();//agregacion
    public static final Map<String, Evento> eventos = new HashMap<>();//agregacion
    public static final List<Venta> ventas = new ArrayList<>();//agregacion, porque el administrador es el unico que accedes a la lista de ventas

    static {
        administradores.put("admin", new Administrador("admin", "1234", "admin@correo.com"));
        usuarios.put("cliente1", new Usuario("cliente1", "pass1", "cliente@correo.com"));

        vendedores.put("V01", new Vendedor("V01", "Camila"));
        vendedores.put("V02", new Vendedor("V02", "Pedro"));

        eventos.put("E1", new Evento("E1", "Concierto Dorado", "The Gold Band",
                LocalDateTime.now().plusDays(5), Nivel.VIP));
        eventos.put("E2", new Evento("E2", "Noche de Plata", "Pop Deluxe",
                LocalDateTime.now().plusDays(10), Nivel.PREMIUM));
        eventos.put("E3", new Evento("E3", "Festival Azul", "Various",
                LocalDateTime.now().plusDays(15), Nivel.GENERAL));
    }

    public static void agregarUsuario(Usuario u) { usuarios.put(u.getNombreUsuario(), u); }
    public static void agregarAdministrador(Administrador a) { administradores.put(a.getNombreUsuario(), a); }
    public static void agregarVenta(Venta v) { ventas.add(v); }
}
