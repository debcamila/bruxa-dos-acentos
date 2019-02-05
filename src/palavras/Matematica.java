
package palavras;

import game.Palavra;

public class Matematica extends Palavra {
    
   public Matematica() {
        super("Matematica"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(5, "´", "á");
    }
}
