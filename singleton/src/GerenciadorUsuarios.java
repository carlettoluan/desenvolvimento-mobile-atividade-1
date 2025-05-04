

public class GerenciadorUsuarios {
    private static GerenciadorUsuarios instancia;
    
    private java.util.Map<String, Usuario> usuarios;
    
    private GerenciadorUsuarios() {
        usuarios = new java.util.HashMap<>();
    }
    
    public static synchronized GerenciadorUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorUsuarios();
        }
        return instancia;
    }
    
    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            return false; // Usu�rio j� existe
        }
        
        if (emailJaExiste(usuario.getEmail())) {
            return false; // Email j� est� em uso
        }
        
        usuarios.put(usuario.getId(), usuario);
        return true;
    }
    
    private boolean emailJaExiste(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.get(id);
    }
    
    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean removerUsuario(String id) {
        if (usuarios.containsKey(id)) {
            usuarios.remove(id);
            return true;
        }
        return false;
    }
    
    public java.util.List<Usuario> listarUsuarios() {
        return new java.util.ArrayList<>(usuarios.values());
    }
    
    public int getTotalUsuarios() {
        return usuarios.size();
    }
}
