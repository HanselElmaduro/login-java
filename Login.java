import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnIniciarSesion;
    private final JButton btnRegistrarse;

    public Login() {
        setTitle("Login de Usuarios");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(18);
        panelUsuario.add(txtUsuario);

        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelContrasena.add(new JLabel("Contrasena:"));
        txtContrasena = new JPasswordField(16);
        txtContrasena.setEchoChar('*');
        panelContrasena.add(txtContrasena);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnIniciarSesion = new JButton("Iniciar sesion");
        btnRegistrarse = new JButton("Registrarse");
        btnIniciarSesion.addActionListener(this);
        btnRegistrarse.addActionListener(this);
        panelBotones.add(btnIniciarSesion);
        panelBotones.add(btnRegistrarse);

        add(panelUsuario);
        add(panelContrasena);
        add(panelBotones);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIniciarSesion) {
            String usuario = txtUsuario.getText().trim();
            String contrasena = new String(txtContrasena.getPassword()).trim();
            // Validar campos vacíos
if (usuario.isEmpty() || contrasena.isEmpty()) {
    JOptionPane.showMessageDialog(null,
        "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
    return;
}

// Buscar usuario
boolean encontrado = false;

for (Usuario u : Datos.listaUsuarios) {
    if (u.usuario.equals(usuario) && u.contraseña.equals(contrasena)) {
        encontrado = true;
        break;
    }
}

// Resultado
if (encontrado) {
    this.dispose(); // cierra login
    new PrincipalFrame().setVisible(true);
}
} else {
    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
}

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe ingresar su usuario y contrasena, si no esta registrado debe registrarse"
                );
            } else {
                JOptionPane.showMessageDialog(this, "Login de prueba correcto: funciona.");
            }
        } else if (e.getSource() == btnRegistrarse) {
            this.dispose();
            new RegistroFrame().setVisible(true);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Login ventana = new Login();
            ventana.setVisible(true);
        });
    }
}
