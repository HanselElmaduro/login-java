import javax.swing.*;
import java.awt.event.*;

public class RegistroFrame extends JFrame {

    JTextField txtUsuario, txtNombre, txtApellido, txtTelefono, txtCorreo;
    JPasswordField txtPass, txtConfirmPass;
    JButton btnRegistrar;

    public RegistroFrame() {
        setTitle("Registro de Usuario");
        setSize(350, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        // Campos
        addLabel("Usuario:", 20, 20);
        txtUsuario = addTextField(150, 20);

        addLabel("Nombre:", 20, 60);
        txtNombre = addTextField(150, 60);

        addLabel("Apellido:", 20, 100);
        txtApellido = addTextField(150, 100);

        addLabel("Telefono:", 20, 140);
        txtTelefono = addTextField(150, 140);

        addLabel("Correo:", 20, 180);
        txtCorreo = addTextField(150, 180);

        addLabel("Contrasena:", 20, 220);
        txtPass = new JPasswordField();
        txtPass.setBounds(150, 220, 150, 25);
        add(txtPass);

        addLabel("Confirmar:", 20, 260);
        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(150, 260, 150, 25);
        add(txtConfirmPass);

        // Botón
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(100, 310, 120, 30);
        add(btnRegistrar);

        // Acción botón
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }

    // Función para registrar
    private void registrarUsuario() {

        String usuario = txtUsuario.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String pass = new String(txtPass.getPassword());
        String confirm = new String(txtConfirmPass.getPassword());

        // Validar campos vacíos
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Usuario");
            return;
        }
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Nombre");
            return;
        }
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Apellido");
            return;
        }
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Telefono");
            return;
        }
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Correo");
            return;
        }
        if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Contrasena");
            return;
        }
        if (confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Confirmar contrasena");
            return;
        }

        // Validar contraseñas
        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Las contrasenas no coinciden");
            return;
        }

        // Validar usuario repetido
        for (Usuario u : Datos.listaUsuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario)) {
                JOptionPane.showMessageDialog(this, "El usuario ya esta registrado");
                return;
            }
        }

        // Guardar usuario
        Usuario nuevo = new Usuario(usuario, nombre, apellido, telefono, correo, pass);
        Datos.listaUsuarios.add(nuevo);

        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");

        // Cerrar registro y volver al login
        this.dispose();
        new LoginApp().setVisible(true);
    }

    // Métodos para crear componentes rápido
    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 25);
        add(label);
    }

    private JTextField addTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 150, 25);
        add(txt);
        return txt;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroFrame().setVisible(true));
    }
}
