
package palavras;

import game.Palavra;

public class Coracao extends Palavra{
    public Coracao() {
        super("Coraçao"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(5, "~", "ã");
    }
}
