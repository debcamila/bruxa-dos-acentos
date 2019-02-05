
package palavras;

import game.Palavra;

public class Opiniao extends Palavra{
    public Opiniao() {
        super("Opiniao"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(5, "~", "ã");
    }
}
