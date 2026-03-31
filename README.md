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
import javax.swing.SwingUtilities;

public class LoginApp extends JFrame implements ActionListener {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnIniciarSesion;
    private final JButton btnRegistrarse;

    public LoginApp() {
        setTitle("Login");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 8, 8));

        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(20);
        panelUsuario.add(txtUsuario);

        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelContrasena.add(new JLabel("Contrasena:"));
        txtContrasena = new JPasswordField(20);
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
            iniciarSesion();
        } else if (e.getSource() == btnRegistrarse) {
            dispose();
            new RegistroFrame().setVisible(true);
        }
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Debe ingresar su usuario y contrasena, si no esta registrado debe registrarse"
            );
            return;
        }

        Usuario encontrado = Datos.buscarPorCredenciales(usuario, contrasena);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Usuario o contrasena incorrectos");
            return;
        }

        dispose();
        new PrincipalFrame().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}
