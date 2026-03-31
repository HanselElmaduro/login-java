import java.util.ArrayList;

public class Datos {
    public static final ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static Usuario buscarPorCredenciales(String usuario, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario) && u.getContrasena().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }

    public static boolean existeUsuario(String usuario) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
        }
        return false;
    }
}