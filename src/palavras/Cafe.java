
package palavras;

import game.Palavra;

public class Cafe extends Palavra{
    public Cafe() {
        super("Cafe"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(3, "´", "é");
    }
}
