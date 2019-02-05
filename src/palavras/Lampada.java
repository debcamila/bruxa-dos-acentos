
package palavras;

import game.Palavra;

public class Lampada extends Palavra{
    public Lampada() {
        super("Lampada"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(1, "^", "â");
    }
}
