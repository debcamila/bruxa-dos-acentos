
package palavras;

import game.Palavra;

public class Cidadaos extends Palavra{
    public Cidadaos() {
        super("Cidadaos"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(5, "~", "ã");
    }
}


