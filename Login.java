import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Login {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

class Usuario {
    private String usuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contrasena;

    public Usuario(
            String usuario,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            String contrasena
    ) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

class UserStore {
    private static final List<Usuario> usuarios = new ArrayList<>();

    public static boolean existeUsuario(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static Usuario buscarPorCredenciales(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(username) && u.getContrasena().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public static void agregar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<Usuario> obtenerTodos() {
        return usuarios;
    }
}

class LoginFrame extends JFrame implements ActionListener {
    private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;
    private final JButton btnIniciarSesion;
    private final JButton btnRegistrarse;

    public LoginFrame() {
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

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
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
            return;
        }

        if (e.getSource() == btnRegistrarse) {
            new RegistroFrame(this).setVisible(true);
            setVisible(false);
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

        Usuario encontrado = UserStore.buscarPorCredenciales(usuario, contrasena);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Usuario o contrasena incorrecta");
            return;
        }

        dispose();
        new PrincipalFrame().setVisible(true);
    }
}

class RegistroFrame extends JFrame implements ActionListener {
    private final LoginFrame loginFrame;
    private final JTextField txtUsuario;
    private final JTextField txtNombre;
    private final JTextField txtApellido;
    private final JTextField txtTelefono;
    private final JTextField txtCorreo;
    private final JPasswordField txtContrasena;
    private final JPasswordField txtConfirmar;
    private final JButton btnGuardar;
    private final JButton btnCancelar;

    public RegistroFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        setTitle("Registro");
        setSize(420, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel panelCampos = new JPanel(new GridLayout(7, 2, 6, 6));

        panelCampos.add(new JLabel("Nombre de usuario:"));
        txtUsuario = new JTextField();
        panelCampos.add(txtUsuario);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelCampos.add(txtApellido);

        panelCampos.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panelCampos.add(txtTelefono);

        panelCampos.add(new JLabel("Correo electronico:"));
        txtCorreo = new JTextField();
        panelCampos.add(txtCorreo);

        panelCampos.add(new JLabel("Contrasena:"));
        txtContrasena = new JPasswordField();
        txtContrasena.setEchoChar('*');
        panelCampos.add(txtContrasena);

        panelCampos.add(new JLabel("Confirmar contrasena:"));
        txtConfirmar = new JPasswordField();
        txtConfirmar.setEchoChar('*');
        panelCampos.add(txtConfirmar);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            registrarUsuario();
            return;
        }

        if (e.getSource() == btnCancelar) {
            dispose();
            loginFrame.setVisible(true);
        }
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Nombre de usuario");
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
            JOptionPane.showMessageDialog(this, "Falta el campo: Correo electronico");
            return;
        }
        if (contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Contrasena");
            return;
        }
        if (confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta el campo: Confirmar contrasena");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contrasenas no coinciden");
            return;
        }

        if (UserStore.existeUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe");
            return;
        }

        UserStore.agregar(new Usuario(usuario, nombre, apellido, telefono, correo, contrasena));
        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
        dispose();
        loginFrame.setVisible(true);
    }
}

class PrincipalFrame extends JFrame implements ActionListener {
    private final DefaultTableModel modelo;
    private final JTable tabla;
    private final JButton btnEditar;
    private final JButton btnEliminar;
    private final JButton btnCerrarSesion;

    public PrincipalFrame() {
        setTitle("Usuarios registrados");
        setSize(620, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        modelo = new DefaultTableModel(new Object[] {"Nombre", "Telefono", "Correo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        btnEditar = new JButton("Editar usuario");
        btnEliminar = new JButton("Eliminar usuario");
        btnCerrarSesion = new JButton("Cerrar sesion");

        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnCerrarSesion.addActionListener(this);

        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrarSesion);
        add(panelBotones, BorderLayout.SOUTH);

        refrescarTabla();
    }

    private void refrescarTabla() {
        modelo.setRowCount(0);
        for (Usuario u : UserStore.obtenerTodos()) {
            String nombreCompleto = u.getNombre() + " " + u.getApellido();
            modelo.addRow(new Object[] {nombreCompleto.trim(), u.getTelefono(), u.getCorreo()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEditar) {
            editarUsuarioSeleccionado();
            return;
        }

        if (e.getSource() == btnEliminar) {
            eliminarUsuarioSeleccionado();
            return;
        }

        if (e.getSource() == btnCerrarSesion) {
            dispose();
            new LoginFrame().setVisible(true);
        }
    }

    private void editarUsuarioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar");
            return;
        }

        Usuario original = UserStore.obtenerTodos().get(fila);

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

        for (Usuario u : UserStore.obtenerTodos()) {
            boolean esMismoUsuario = u == original;
            if (!esMismoUsuario && u.getUsuario().equalsIgnoreCase(nuevoUsuario)) {
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

        refrescarTabla();
        JOptionPane.showMessageDialog(this, "Usuario editado correctamente");
    }

    private void eliminarUsuarioSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "Desea eliminar el usuario seleccionado?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            UserStore.obtenerTodos().remove(fila);
            refrescarTabla();
            JOptionPane.showMessageDialog(this, "Usuario eliminado");
        }
    }
}
