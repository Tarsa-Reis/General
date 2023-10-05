public class Jogador {
    private String nome;
    private boolean tipoJogador;
    private JogoGeneral jogoG;

    public Jogador(){

    }

    public Jogador(String nome){
        this.nome = nome;
    }

    public void newGame(){
        jogoG = null;
        jogoG = new JogoGeneral();
    }

    public boolean getTipoJogador(){
        return this.tipoJogador;
    }
}
