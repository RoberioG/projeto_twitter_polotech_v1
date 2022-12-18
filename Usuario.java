public class Usuario {

    public String nome;
    public String senha;

    int id;

    public String[] twittes = new String[200];

    Usuario (String nome, String senha, int id){
        this.nome = nome;
        this.senha = senha;
        this.id = id;
    }
    public void display(){
        System.out.println("nome: " + nome + " "
                + "senha: " + senha);
    }

}
