
package palavras;

import game.Palavra;

public class Robo extends Palavra{
    public Robo() {
        super("Robo"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(3, "^", "ô");
    }
}
