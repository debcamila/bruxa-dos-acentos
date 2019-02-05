
package game;

import javafx.scene.image.Image;

public class Potion {
    private int x;
    private int y;
    private int largura = 51;
    private int altura = 104;
    private Image pot;
    private int posiInicialX;
    private int posiInicialY;
    
    public boolean isSelecionado = false;
    public String acento;
    
   public Potion(String img, int x, int y, String acento) {
        this.pot = new Image(img);
        this.x = x;
        this.y = y;
        this.posiInicialX = x;
        this.posiInicialY = y;
        this.acento = acento;
    }
        
    public void setX(int value){
        x = value;
    }
    
    public int getX(){
        return x;
    }
    
    public void setY(int value){
        y = value;
    }
    
    public int getY(){
        return y;
    }
    
    public void setLargura(int l){
        largura = l;
    }
    
    public int getLargura(){
        return largura;
    }
    
    public void setAltura(int a){
        altura = a;
    }
    
    public int getAltura(){
        return altura;
    }
    
    public void setPosX(int posiX){
        posiInicialX = posiX;
    }
    
    public int getPosX(){
        return posiInicialX;
    }
    
    public void setPosY(int posiY){
        posiInicialY = posiY;
    }
    
    public int getPosY(){
        return posiInicialY;
    }
    
    public void setImage(String img){
        pot = new Image(img);
    }
    
    public Image getImg(){
        return pot;
    }
    
    public void resetar() {
        x = posiInicialX;
        y = posiInicialY;
    }
    
    public boolean checarMouse(int mx, int my) {
        return mx > x &&
               mx <= x + largura &&
               my > y &&
               my <= y + altura; 
    }
}
