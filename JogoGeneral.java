import java.util.Scanner;
public class JogoGeneral {

    static Dado[] dados = new Dado[5];
    private int[] jogadas = new int[13];
    private int [] [] rodadas = new int [8] [13];
    private int pontos;
    private boolean []escolhidos = new boolean[13];

    public JogoGeneral(){
        pontos = 0;
        int i;
        for(i=0;i<5;i++){
            dados[i]=new Dado();
        }
    }

    public void rolarDados(){
        int i;
        for(i=0;i<5;i++){
            dados[i].roll();
            System.out.print(dados[i].getSideUp()+"\t");
        }
    }

    public String toString(){
        return dados[0].toString();
    }

    private int somaDados(){
        int i;
        int soma = 0;
        for(i=0;i<5;i++){
            soma = soma + dados[i].getSideUp();
        }

        return soma;
    }

    private void getRodada(Dado dados[], int pontos, int num, int rodada){
        int i;
        for(i=0;i<5;i++){
            rodadas[i][rodada] = dados[i].getSideUp();
        }
        rodadas[5][rodada] = num;
        rodadas[6][rodada] = pontos;
        if(rodada == 0){
            rodadas[7][rodada] = 0;
        }
        else{
            rodadas[7][rodada] = rodadas[7][rodada-1] + pontos;
        }
    }

    public int validarJogada(int num){
        int i,j,k;
        int aux;
        int soma=0;
        if(num>=1 && num<=6){
            for(i=0;i<5;i++){
                if(dados[i].getSideUp()==num){
                    soma = soma + num;
                }
            }
            return soma;
        }

        if(num==7){
            aux=0;
            for(i=0;i<4;i++){
                for(j=(i+1);j<5;j++){
                    if(dados[i].getSideUp()==dados[j].getSideUp()){
                        aux++;
                    }
                }
                if(aux>=2){
                    return somaDados();
                }
                else{
                    aux=0;
                }
            }
            return 0;
        }

        if(num==8){
            aux=0;
            for(i=0;i<4;i++){
                for(j=(i+1);j<5;j++){
                    if(dados[i].getSideUp()==dados[j].getSideUp()){
                        aux++;
                    }
                }
                if(aux>=3){
                    return somaDados();
                }
                else{
                    aux=0;
                }
            }
            return 0;
        }

        if(num==9){
            aux=0;
            boolean flush1 = false;
            boolean flush2 = false;
            for(i=0;i<=3;i++){
                for(j=(i+1);j<=4;j++){
                    if(dados[j].getSideUp()!=-1 && dados[i].getSideUp()==dados[j].getSideUp()){
                        aux++;
                        dados[j].trocaLado();
                    }
                }
                if((dados[i].getSideUp()!=-1)||i==3){
                    if(aux!=2 && aux!=1){
                        return 0;
                    }
                    else if(aux==2){
                        flush1 = true;
                        aux=0;
                    }
                    else if(aux==1){
                        flush2 = true;
                        aux=0;
                    }

                    if(flush1==true && flush2 ==true){
                        return 25;
                    }
                }
            }
        }

        if(num==10){
            for(j=2;j<=6;j++){
                aux=0;
                for(i=0;i<5;i++){
                    if(dados[i].getSideUp()==j){
                        aux++;
                    }
                }
                if(aux!=1){
                    return 0;
                }
            }
            return 30;
        }

        if(num==11){
            for(j=1;j<=5;j++){
                aux=0;
                for(i=0;i<5;i++){
                    if(dados[i].getSideUp()==j){
                        aux++;
                    }
                }
                if(aux!=1){
                    return 0;
                }
            }
            return 40;
        }

        if(num==12){
            aux=0;
            for(i=1;i<5;i++){
                if(dados[0].getSideUp()==dados[i].getSideUp()){
                    aux++;
                }
            }
            if(aux==4){
                return 50;
            }
            else{
                return 0;
            }
        }

        if(num==13){
            return somaDados();
        }

        return 0;
    }

    public void pontuarJogada(int opcao,int i){
        Scanner scan = new Scanner(System.in);
        int aux;
        if(escolhidos[opcao]==false){
            this.jogadas[i] =  validarJogada(opcao);
            this.escolhidos[opcao]=true;
            pontos= pontos+this.jogadas[i];
            getRodada(dados, pontos, opcao, i);
        }
        else{
            System.out.println("Opcao invalida, escolha outra opcao: \t");
            aux = scan.nextInt();
            pontuarJogada(aux, i);
        }
    }

}
