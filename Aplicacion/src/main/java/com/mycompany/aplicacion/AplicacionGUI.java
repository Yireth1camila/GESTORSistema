/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplicacion;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class AplicacionGUI extends JFrame {

    private Usuario usuarioActual;//asociacion con usuario,de´pende de usuario para cambiar el flujo(cliente,admin)..
    private JPanel panelPrincipal;

    public AplicacionGUI() {
        setTitle("🎟️ Sistema de Gestión de Entradas");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        setContentPane(panelPrincipal);

        mostrarPantallaLogin();
    }

    private void mostrarPantallaLogin() {
        panelPrincipal.removeAll();
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtUsuario = new JTextField(15);
        JPasswordField txtContrasena = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");
        JButton btnRegistrarCliente = new JButton("Registrar Cliente");
        JButton btnRegistrarAdmin = new JButton("Registrar Administrador");

        gbc.gridx=0; gbc.gridy=0; loginPanel.add(lblUsuario, gbc);
        gbc.gridx=1; loginPanel.add(txtUsuario, gbc);
        gbc.gridx=0; gbc.gridy=1; loginPanel.add(lblContrasena, gbc);
        gbc.gridx=1; loginPanel.add(txtContrasena, gbc);
        gbc.gridx=0; gbc.gridy=2; loginPanel.add(btnLogin, gbc);
        gbc.gridx=1; loginPanel.add(btnRegistrarCliente, gbc);
        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2; loginPanel.add(btnRegistrarAdmin, gbc);

        panelPrincipal.add(loginPanel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();

        btnLogin.addActionListener(e -> {
            String user = txtUsuario.getText().trim();
            String pass = new String(txtContrasena.getPassword());

            //dependencias
            if (AlmacenDatos.administradores.containsKey(user) &&
                    AlmacenDatos.administradores.get(user).verificarContrasena(pass)) {
                usuarioActual = AlmacenDatos.administradores.get(user);
                mostrarPanelAdministrador();
            } else if (AlmacenDatos.usuarios.containsKey(user) &&
                    AlmacenDatos.usuarios.get(user).verificarContrasena(pass)) {
                usuarioActual = AlmacenDatos.usuarios.get(user);
                mostrarPanelCliente();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });

        btnRegistrarCliente.addActionListener(e -> {
            String user = JOptionPane.showInputDialog(this, "Nombre de usuario:");
            if(user==null || user.trim().isEmpty()) return;
            String pass = JOptionPane.showInputDialog(this, "Contraseña:");
            if(pass==null || pass.trim().isEmpty()) return;
            String correo = JOptionPane.showInputDialog(this, "Correo:");
            if(correo==null || correo.trim().isEmpty()) return;

            Usuario nuevo = new Usuario(user, pass, correo);
            AlmacenDatos.agregarUsuario(nuevo);
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente");
        });

        btnRegistrarAdmin.addActionListener(e -> {
            String user = JOptionPane.showInputDialog(this, "Nombre de administrador:");
            if(user==null || user.trim().isEmpty()) return;
            String pass = JOptionPane.showInputDialog(this, "Contraseña:");
            if(pass==null || pass.trim().isEmpty()) return;
            String correo = JOptionPane.showInputDialog(this, "Correo:");
            if(correo==null || correo.trim().isEmpty()) return;

            Administrador nuevo = new Administrador(user, pass, correo);
            AlmacenDatos.administradores.put(user, nuevo);
            JOptionPane.showMessageDialog(this, "Administrador registrado correctamente");
        });
    }

    private void mostrarPanelAdministrador() {
        panelPrincipal.removeAll();

        JPanel adminPanel = new JPanel(new BorderLayout());

        // ---- Crear evento ----
        JPanel crearEventoPanel = new JPanel(new GridBagLayout());
        crearEventoPanel.setBorder(BorderFactory.createTitledBorder("Crear Evento"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNombre = new JTextField(15);
        JTextField txtArtista = new JTextField(15);
        JTextField txtFecha = new JTextField("2025-11-20T20:00",15);
        JComboBox<Nivel> comboNivel = new JComboBox<>(Nivel.values());
        JButton btnCrearEvento = new JButton("Crear Evento");

        gbc.gridx=0; gbc.gridy=0; crearEventoPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx=1; crearEventoPanel.add(txtNombre, gbc);
        gbc.gridx=0; gbc.gridy=1; crearEventoPanel.add(new JLabel("Artista:"), gbc);
        gbc.gridx=1; crearEventoPanel.add(txtArtista, gbc);
        gbc.gridx=0; gbc.gridy=2; crearEventoPanel.add(new JLabel("Fecha y Hora:"), gbc);
        gbc.gridx=1; crearEventoPanel.add(txtFecha, gbc);
        gbc.gridx=0; gbc.gridy=3; crearEventoPanel.add(new JLabel("Nivel:"), gbc);
        gbc.gridx=1; crearEventoPanel.add(comboNivel, gbc);
        gbc.gridx=0; gbc.gridy=4; gbc.gridwidth=2; crearEventoPanel.add(btnCrearEvento, gbc);

        btnCrearEvento.addActionListener(e -> {
            String id = "E" + (AlmacenDatos.eventos.size()+1);
            try {
                LocalDateTime fechaHora = LocalDateTime.parse(txtFecha.getText().trim());
                Evento ev = new Evento(id, txtNombre.getText(), txtArtista.getText(), fechaHora, (Nivel) comboNivel.getSelectedItem());
                AlmacenDatos.eventos.put(id, ev);
                JOptionPane.showMessageDialog(this, "Evento creado correctamente");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido");
            }
        });

        // ---- Ventas ----
        JPanel ventasPanel = new JPanel(new BorderLayout());
        ventasPanel.setBorder(BorderFactory.createTitledBorder("Registro de Ventas"));
        JTextArea areaVentas = new JTextArea();
        areaVentas.setEditable(false);
        actualizarAreaVentas(areaVentas,null);

        JPanel filtroPanel = new JPanel();
        JComboBox<String> comboVendedores = new JComboBox<>();
        comboVendedores.addItem("Todos");
        AlmacenDatos.vendedores.values().forEach(v -> comboVendedores.addItem(v.getId()));
        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnPDF = new JButton("Generar PDF Ventas");

        filtroPanel.add(new JLabel("Vendedor:"));
        filtroPanel.add(comboVendedores);
        filtroPanel.add(btnFiltrar);
        filtroPanel.add(btnPDF);

        btnFiltrar.addActionListener(e -> {
            String vendedorId = (String) comboVendedores.getSelectedItem();
            if("Todos".equals(vendedorId)) actualizarAreaVentas(areaVentas,null);
            else actualizarAreaVentas(areaVentas,vendedorId);
        });

        // ✅ Generar PDF de registro y boletas admin
        btnPDF.addActionListener(e -> {
            List<Venta> ventas = AlmacenDatos.ventas;
            if(ventas.isEmpty()) {
                JOptionPane.showMessageDialog(this,"No hay ventas registradas");
                return;
            }

            UtilPDF.generarRegistroVentas(ventas,"PDF_RegistroVentas/Registro_Ventas.pdf");

            for(Venta v : ventas) {
                UtilPDF.generarBoleta(v,"PDF_Ventas_Admin");
            }

            JOptionPane.showMessageDialog(this,"PDFs generados correctamente:\n- Registro_Ventas.pdf\n- Boletas en PDF_Ventas_Admin");
        });

        ventasPanel.add(filtroPanel, BorderLayout.NORTH);
        ventasPanel.add(new JScrollPane(areaVentas), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarPantallaLogin());

        adminPanel.add(crearEventoPanel, BorderLayout.NORTH);
        adminPanel.add(ventasPanel, BorderLayout.CENTER);
        adminPanel.add(btnVolver, BorderLayout.SOUTH);

        panelPrincipal.add(adminPanel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void actualizarAreaVentas(JTextArea area,String vendedorId) {
        area.setText("");
        for(Venta v : AlmacenDatos.ventas) {
            if(vendedorId==null || v.getVendedor().getId().equals(vendedorId))
                area.append(v.toString()+"\n");
        }
    }

    private void mostrarPanelCliente() {
        panelPrincipal.removeAll();
        panelPrincipal.setLayout(new BorderLayout());

        DefaultListModel<Evento> modelEventos = new DefaultListModel<>();
        AlmacenDatos.eventos.values().forEach(modelEventos::addElement);
        JList<Evento> listaEventos = new JList<>(modelEventos);
        listaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnComprar = new JButton("Comprar Entrada");
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> mostrarPantallaLogin());

        JPanel southPanel = new JPanel();
        southPanel.add(btnComprar);
        southPanel.add(btnVolver);

        panelPrincipal.add(new JScrollPane(listaEventos), BorderLayout.CENTER);
        panelPrincipal.add(southPanel, BorderLayout.SOUTH);

        panelPrincipal.revalidate();
        panelPrincipal.repaint();

        btnComprar.addActionListener(e -> {
            Evento seleccionado = listaEventos.getSelectedValue();
            if(seleccionado==null) {
                JOptionPane.showMessageDialog(this,"Selecciona un evento");
                return;
            }

            Nivel nivelSeleccionado = (Nivel) JOptionPane.showInputDialog(this,
                    "Selecciona el nivel","Nivel",
                    JOptionPane.QUESTION_MESSAGE,null,Nivel.values(),seleccionado.getNivel());

            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(this,"Cantidad de entradas:"));
            String[] idsVendedores = AlmacenDatos.vendedores.keySet().toArray(new String[0]);
            String idVendedor = (String) JOptionPane.showInputDialog(this,
                    "Selecciona vendedor","Vendedor",
                    JOptionPane.QUESTION_MESSAGE,null,idsVendedores,idsVendedores[0]);
            Vendedor vendedor = AlmacenDatos.vendedores.get(idVendedor);

            Venta venta = new Venta("V"+(AlmacenDatos.ventas.size()+1),
                    usuarioActual.getNombreUsuario(), seleccionado, vendedor, cantidad);
            AlmacenDatos.agregarVenta(venta);

            UtilPDF.generarBoleta(venta,"PDF_Boletas");
            JOptionPane.showMessageDialog(this,"Compra completada. Boleta PDF generada.");
        });
    }
}
