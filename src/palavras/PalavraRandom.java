
package palavras;

import game.Palavra;
import java.util.ArrayList;
import java.util.Random;

public class PalavraRandom {
    ArrayList<Palavra> p = new ArrayList();
    
    public PalavraRandom(){
        p.add(new Matematica());
        p.add(new Cafe());
        p.add(new Musica());
        p.add(new Ambulancia());
        p.add(new Lampada());
        p.add(new Robo());
        p.add(new Opiniao());
        p.add(new Cidadaos());
        p.add(new Coracao());
        p.add(new Tenis());
    }

    public ArrayList<Palavra> getP() {
        return p;
    }
    
    public int gerarPalavra() {
        Random rand = new Random();
        int pal = rand.nextInt(10);
        return pal;
    }
    public int proxPalavra(int atual){
        return (atual +1)%10;
    }
}
