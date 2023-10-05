import java.util.Random;

public class Dado {
    private Random generate = new Random();
    private int sideUp;

    public Dado(){
        generate.nextInt(1);
        this.sideUp = 1;
    }

    public void roll(){
        sideUp = generate.nextInt(6) + 1;
    }
    
    public int getSideUp(){
        return sideUp;
    }

    public String toString(){
        return Integer.toString(sideUp);
    }

    public void trocaLado(){
        sideUp = -1;
    }
}
