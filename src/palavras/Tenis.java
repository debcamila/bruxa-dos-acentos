
package palavras;

import game.Palavra;

public class Tenis extends Palavra{
    public Tenis(){
        super("Tenis"); // super significa o CONSTRUTOR da classe pai, ou seja, Palavra
        addAcento(1, "^", "ê");
    }
}
