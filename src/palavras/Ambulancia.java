
package palavras;

import game.Palavra;

public class Ambulancia extends Palavra{
    public Ambulancia() {
        super("Ambulancia"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(5, "^", "â");
    }
}
