import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Usuario[] novoUser = new Usuario[100];
    static int numUsuarios = 2;

    public static void main(String[] args) throws InterruptedException {
        inicio();
    }

    private static void inicio() throws InterruptedException {

        novoUser[0] = new Usuario("mathias", "mat123", 0);
        novoUser[1] = new Usuario("fabia", "fabifabi", 1);
        novoUser[0].twittes[0] = "Meu primeiro twitte" + "\n    >> " + "@mathias 15:07 20/setembro/2022";
        novoUser[1].twittes[0] = "Me sentindo feliz :)" + "\n    >> " + "@fabia 13:22 12/outubro/2022";

        int opcao;
        do {

            limparConsole();

            System.out.println("######################################");
            System.out.println("##### BEM VINDO AO TWITER DA ADA #####");
            System.out.println("######################################");
            System.out.println();

            System.out.println("Escolha uma das opções");
            System.out.println("1 - Entrar");
            System.out.println("2 - Cadastrar");
            System.out.println("0 - Sair");

            opcao = scanner.nextByte();

            switch (opcao){
                case 1:
                    limparConsole();
                    opcao = entrar();
                    break;
                case 2:
                    limparConsole();
                    cadastrar();
                    break;
                case 0:
                    System.out.println(">>> Até logo, bye <<<");
                    break;
                default:
                    System.out.println(">>> Informe uma opção válida <<<");
                    Thread.sleep(4000);                                 //função que interrompe o programa por 4seg para mostrar a msg
                    limparConsole();                                          //função que "limpa" o console
            }
        }while(opcao != 0);
    }

    private static void cadastrar() throws InterruptedException {
        System.out.println("____________________________");
        System.out.println("----- TELA DE CADASTRO -----");
        System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
        System.out.println();

        System.out.print("Digite seu nome de usuário: ");
        String nomeUser = scanner.next();
        String senha1;
        String senha2;

        do {
            System.out.print("Digite sua senha: ");
            senha1 = scanner.next();
            System.out.print("Digite novamente sua senha: ");
            senha2 = scanner.next();

            if (!(senha1.equals(senha2))){
                System.out.println("\n>>> As senhas não coincidem. Por favor digite sua senha novamente <<<\n");
            }
        }while (!(senha1.equals(senha2)));

        novoUser[numUsuarios] = new Usuario(nomeUser, senha1, numUsuarios);

        numUsuarios++;
    }

    private static int entrar() throws InterruptedException {
        System.out.println("___________________________");
        System.out.println("------ TELA DE LOGIN ------");
        System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
        System.out.println();

        boolean usuarioNaoAchado = true;
        do {
            System.out.print("Digite seu nome de usuário: ");
            String nomeUser = scanner.next();
            System.out.print("Digite sua senha: ");
            String senhaUser = scanner.next();

            for (int i = 0; i < numUsuarios; i++) {
                if (nomeUser.equals(novoUser[i].nome)) {
                    if (senhaUser.equals(novoUser[i].senha)) {
                        return feedprincipal(novoUser[i].id);
                    }
                }
            }
            System.out.println("\n>>> Usuário ou senha incorretos, por favor verifique seus dados e tente novamente <<<\n");
        }while(usuarioNaoAchado);

        return 0;
    }

    private static int feedprincipal(int id) {
        limparConsole();
        System.out.println("____________________________");
        System.out.println("------ TELA PRINCIPAL ------");
        System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
        System.out.println();

        int opcao;
        do {
            System.out.println();
            System.out.println("Escolha uma das opções");
            System.out.println("1 - Escrever um twitte");
            System.out.println("2 - Ver twittes na rede");
            System.out.println("3 - Voltar para tela de inicial");
            System.out.println("0 - Sair");

            opcao = scanner.nextByte();

            switch (opcao){
                case 1:
                    limparConsole();
                    escreverTwitte(id);
                    break;
                case 2:
                    limparConsole();
                    verTwitte();
                    break;
                case 3:
                    return 3;
                case 0:
                    return 0;
                default:
                    System.out.println("\n>>> Informe uma opção válida <<<\n");
            }

        }while(opcao != 0);

        return 0;

    }

    private static void escreverTwitte(int id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MMMM/yyyy");

        System.out.println("No que voce está pensando?\n\n");
        scanner.nextLine();
        String texto1 = scanner.nextLine();
        String texto2 = "\n    >> " + "@" + novoUser[id].nome + " " + dtf.format(LocalDateTime.now());
        String twitte = texto1 + texto2;

        int posiTwitte = 0;
        for(int i = 0; i < novoUser[id].twittes.length; i++) {
            if(novoUser[id].twittes[i] == null){
                posiTwitte = i;
                break;
            }
        }

        novoUser[id].twittes[posiTwitte] = twitte;
    }

    private static void verTwitte() {

        for(int id = 0; id < numUsuarios; id++){
            for (String twitte : novoUser[id].twittes) {
                if (twitte != null) {
                    System.out.println();
                    System.out.println(twitte);
                }
            }
        }
    }

    private static void limparConsole() {
        for (int i = 10; i< 30; i++)
            System.out.println("\b");
    }
}