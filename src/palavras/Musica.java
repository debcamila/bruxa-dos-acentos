
package palavras;

import game.Palavra;

public class Musica extends Palavra{
    public Musica() {
        super("Musica"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(1, "´", "ú");
    }
}
