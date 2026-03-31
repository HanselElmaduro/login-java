import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrincipalFrame extends JFrame {

    private final JTable tabla;
    private final DefaultTableModel modelo;
    private final JButton btnEditar;
    private final JButton btnEliminar;
    private final JButton btnCerrar;

    public PrincipalFrame() {
        setTitle("Usuarios registrados");
        setSize(640, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // Tabla
        modelo = new DefaultTableModel(new Object[] {"Nombre", "Telefono", "Correo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        btnEditar = new JButton("Editar usuario");
        btnEliminar = new JButton("Eliminar usuario");
        btnCerrar = new JButton("Cerrar sesion");

        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        btnEditar.addActionListener(e -> editarUsuarioSeleccionado());
        btnEliminar.addActionListener(e -> eliminarUsuarioSeleccionado());
        btnCerrar.addActionListener(e -> {
            this.dispose();
            new LoginApp().setVisible(true);
        });

        cargarUsuarios();
    }

    public void cargarUsuarios() {
        modelo.setRowCount(0);

        for (Usuario u : Datos.listaUsuarios) {
            modelo.addRow(new Object[]{
                u.getNombre() + " " + u.getApellido(),
                u.getTelefono(),
                u.getCorreo()
            });
        }
    }

    private void editarUsuarioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar");
            return;
        }

        Usuario original = Datos.listaUsuarios.get(fila);

        JTextField txtUsuario = new JTextField(original.getUsuario());
        JTextField txtNombre = new JTextField(original.getNombre());
        JTextField txtApellido = new JTextField(original.getApellido());
        JTextField txtTelefono = new JTextField(original.getTelefono());
        JTextField txtCorreo = new JTextField(original.getCorreo());
        JPasswordField txtContrasena = new JPasswordField(original.getContrasena());
        txtContrasena.setEchoChar('*');

        JPanel panel = new JPanel(new GridLayout(6, 2, 6, 6));
        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Telefono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);
        panel.add(new JLabel("Contrasena:"));
        panel.add(txtContrasena);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Editar usuario",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        String nuevoUsuario = txtUsuario.getText().trim();
        String nuevoNombre = txtNombre.getText().trim();
        String nuevoApellido = txtApellido.getText().trim();
        String nuevoTelefono = txtTelefono.getText().trim();
        String nuevoCorreo = txtCorreo.getText().trim();
        String nuevaContrasena = new String(txtContrasena.getPassword()).trim();

        if (nuevoUsuario.isEmpty()
                || nuevoNombre.isEmpty()
                || nuevoApellido.isEmpty()
                || nuevoTelefono.isEmpty()
                || nuevoCorreo.isEmpty()
                || nuevaContrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        for (Usuario u : Datos.listaUsuarios) {
            if (u != original && u.getUsuario().equalsIgnoreCase(nuevoUsuario)) {
                JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe");
                return;
            }
        }

        original.setUsuario(nuevoUsuario);
        original.setNombre(nuevoNombre);
        original.setApellido(nuevoApellido);
        original.setTelefono(nuevoTelefono);
        original.setCorreo(nuevoCorreo);
        original.setContrasena(nuevaContrasena);

        cargarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuario editado correctamente");
    }

    private void eliminarUsuarioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Desea eliminar el usuario seleccionado?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Datos.listaUsuarios.remove(fila);
            cargarUsuarios();
            JOptionPane.showMessageDialog(this, "Usuario eliminado");
        }
    }
}