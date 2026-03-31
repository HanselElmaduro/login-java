import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LoginApp extends JFrame {
    private final JTextField userField;
    private final JPasswordField passField;
    private final JLabel statusLabel;

    public LoginApp() {
        setTitle("Login Java");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Iniciar sesion", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        centerPanel.add(userLabel, gbc);

        userField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        centerPanel.add(userField, gbc);

        JLabel passLabel = new JLabel("Contrasena:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        centerPanel.add(passLabel, gbc);

        passField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        centerPanel.add(passField, gbc);

        JButton loginButton = new JButton("Entrar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0;
        centerPanel.add(loginButton, gbc);

        add(centerPanel, BorderLayout.CENTER);

        statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 14, 0));
        add(statusLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> login());
        getRootPane().setDefaultButton(loginButton);
    }

    private void login() {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            setStatus("Completa usuario y contrasena", Color.RED);
            return;
        }

        // Credenciales de ejemplo. Luego puedes cambiarlas por BD/API.
        boolean success = user.equals("admin") && pass.equals("1234");

        if (success) {
            setStatus("Login correcto", new Color(0, 130, 0));
            JOptionPane.showMessageDialog(this, "Bienvenido, " + user + "!");
        } else {
            setStatus("Usuario o contrasena incorrectos", Color.RED);
        }
    }

    private void setStatus(String text, Color color) {
        statusLabel.setText(text);
        statusLabel.setForeground(color);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Usar look and feel por defecto si falla.
            }
            new LoginApp().setVisible(true);
        });
    }
}
