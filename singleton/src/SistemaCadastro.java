public class SistemaCadastro {
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);
    private static GerenciadorUsuarios gerenciador = GerenciadorUsuarios.getInstancia();
    
    public static void main(String[] args) {
        boolean executando = true;
        
        System.out.println("===== SISTEMA DE CADASTRO DE USUÁRIOS =====");
        
        while (executando) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar novo usuário");
            System.out.println("2. Buscar usuário por ID");
            System.out.println("3. Buscar usuário por email");
            System.out.println("4. Listar todos os usuários");
            System.out.println("5. Remover usuário");
            System.out.println("6. Total de usuários cadastrados");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarNovoUsuario();
                    break;
                case 2:
                    buscarUsuarioPorId();
                    break;
                case 3:
                    buscarUsuarioPorEmail();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    removerUsuario();
                    break;
                case 6:
                    mostrarTotalUsuarios();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
    }
    
    private static void cadastrarNovoUsuario() {
        System.out.println("\n== Cadastro de Novo Usuário ==");
        
        System.out.print("ID (único): ");
        String id = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Usuario novoUsuario = new Usuario(id, nome, email, senha);
        
        if (gerenciador.cadastrarUsuario(novoUsuario)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar usuário. ID ou email já existe.");
        }
    }
    
    private static void buscarUsuarioPorId() {
        System.out.print("\nDigite o ID do usuário: ");
        String id = scanner.nextLine();
        
        Usuario usuario = gerenciador.buscarUsuarioPorId(id);
        
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    private static void buscarUsuarioPorEmail() {
        System.out.print("\nDigite o email do usuário: ");
        String email = scanner.nextLine();
        
        Usuario usuario = gerenciador.buscarUsuarioPorEmail(email);
        
        if (usuario != null) {
            System.out.println("Usuário encontrado: " + usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    private static void listarUsuarios() {
        System.out.println("\n== Lista de Usuários ==");
        
        java.util.List<Usuario> usuarios = gerenciador.listarUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }
    
    private static void removerUsuario() {
        System.out.print("\nDigite o ID do usuário a ser removido: ");
        String id = scanner.nextLine();
        
        if (gerenciador.removerUsuario(id)) {
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
    
    private static void mostrarTotalUsuarios() {
        System.out.println("\nTotal de usuários cadastrados: " + gerenciador.getTotalUsuarios());
    }
}