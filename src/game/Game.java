package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import palavras.PalavraRandom;

class mousePos { // criar posiçoes do mouse x e y

    public int x;
    public int y;
}

public class Game extends Application {

    ArrayList<Potion> pocoes;
    ArrayList<Letra> acertos;
    PalavraRandom palavraRandom = new PalavraRandom();
    int pos = palavraRandom.gerarPalavra();
    Palavra pal = palavraRandom.getP().get(pos);
    public static MediaPlayer player;
    public static Media media;
    int palavraInicio = pos;
    boolean acertou = false;
    private Label pontuacao;
    int ponto = 0;
    private Jogador jogador;

    // MAIN DO JavaFx
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Bruxa dos Acentos");
        Group raiz = new Group();
        Scene s = new Scene(raiz, 1024, 640);
        primaryStage.setScene(s);       
        Image kitchen = new Image("img/kitchen.png");
        ImageView iv = new ImageView();
        iv.setImage(kitchen);
        iv.setFitWidth(1024);
        iv.setFitHeight(640);
        raiz.getChildren().add(iv);

        Canvas canvas = new Canvas(s.getWidth(), s.getHeight());
        raiz.getChildren().add(canvas);
        pontuacao(raiz);
        GraphicsContext gc = canvas.getGraphicsContext2D(); // para pintar no canvas

        pocoes = criarPocoes();// cria a lista de poçoes
        acertos = new ArrayList<>();
        mousePos mouse = new mousePos();
        criarJogador();

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() { //javafx precisa saber quem é o no que vai receber os eventos, so temos um no: canvas
            @Override
            public void handle(MouseEvent event) {
                mouse.x = (int) event.getX();
                mouse.y = (int) event.getY();
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {//preciona e Arrasta! verificar se o acneto ta letra certa
            @Override
            public void handle(MouseEvent event) {
                for (Potion p : pocoes) {
                    if (p.isSelecionado == true) {
                        p.setX((int) event.getX());
                        p.setY((int) event.getY());
                        int pos = pal.checarColisao(p.getX(), p.getY());
                        if (pos != -1) {
                            Letra l = pal.getLetras().get(pos);
                            if (pal.verificarAcento(pos, p.acento)) { //se o acento ta na letra certo, pinto para amarelo
                                l.cor = Color.YELLOW;
                                pal.corrigir(pos);
                                l.setIsAtivo(false);
                                acertou = true;
                            } else {
                                l.cor = Color.RED; //se o acento ta na letra errada, pinto a letra pra vermelha
                                if(l.cor == Color.RED){
                                    incremento(-5);
                                }
                            }
                        }
                    }
                }
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {//quando o solta o botao do mouse
            @Override                               // passa pra proxima palavra e soma os pontos
            public void handle(MouseEvent event) {
                for (Potion p : pocoes) {
                    if (p.isSelecionado == true) {
                        p.isSelecionado = false;
                        p.resetar();
                    }
                }
                if (acertou == true) {
                    pos = palavraRandom.proxPalavra(pos);
                    if (pos == palavraInicio) {
                        
                        jogador.setTotalPontos(ponto);
                        Inicio.lista.add(jogador);
                        FXLMDocument menu = new FXLMDocument();
                        primaryStage.close();
                        
                        try {
                            menu.start(new Stage());
                        } catch (Exception ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    pal = palavraRandom.getP().get(pos);
                    acertou = false;
                    incremento(10);
                }
            }
        });

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() { // checa qual pocao ta selecionada e qual posicao o mouse ta tocando
            @Override
            public void handle(MouseEvent event) {
                for (Potion p : pocoes) {
                    if (p.checarMouse((int) event.getX(), (int) event.getY()) == true) {
                        p.isSelecionado = true;
                    }
                }
            }
        });

        mousePos bruxaPos = new mousePos();

        new AnimationTimer() { //loop principal do jogo(main loop) que roda 60x por segundo de forma otimizada, desenho tudo
            int x = 0;
            int dir = 1;

            int bdirx = 0;
            int bdiry = 0;

            // N realidade, QUEM vai rodar 60x por segundo é este método HANDLE
            @Override
            public void handle(long now) {
                if (x > canvas.getWidth() - 100) {
                    dir = -1;
                } else if (x < 0) {
                    dir = 1;
                }

                x += dir;

                if (bruxaPos.x > mouse.x) {
                    bdirx = -5;
                } else if (bruxaPos.x < mouse.x) {
                    bdirx = 5;
                }

                bruxaPos.x += bdirx;
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFont(new Font(48));
                gc.setFill(Color.WHITE);


                for (Potion p : pocoes) {
                    gc.drawImage(p.getImg(), p.getX(), p.getY());
                }
                  // PARA CALCULAR O VALOR DE X E Y
//                gc.setFill(Color.WHITE); //mostrar na tela os valores de x e y
//                gc.fillText("MouseX: " + mouse.x, 100, 100);
//                gc.fillText("MouseY: " + mouse.y, 100, 130);

                gc.setFont(new Font(48));
                gc.setFill(Color.WHITE);
                for (int i = 0; i < pal.getLetras().size(); i++) {
                    pal.getLetras().get(i).setX(i * 64 + 64);
                    pal.getLetras().get(i).setY(200);
                    pal.getLetras().get(i).render(gc);

                }
            }
        }.start();
        primaryStage.show();
    } //fim do start

    public void pontuacao(Group raiz){
        String p = Integer.toString(ponto);
        pontuacao = new Label("Pontuação: " + p);
        pontuacao.setLayoutX(775);
        pontuacao.setLayoutY(15);
        pontuacao.setAlignment(Pos.CENTER);
        pontuacao.setFont(Font.font("Berlin Sans FB", 30));
        pontuacao.setTextFill(Color.WHITE);
        pontuacao.setPrefSize(250, 35);
        raiz.getChildren().add(pontuacao);
    }
    
    public void incremento(int i){
        ponto = ponto + i;
        if(ponto <= 0){
            ponto = 0;
        }
        String p = Integer.toString(ponto);
        pontuacao.setText("Pontuação: " + p);
    }
    

    public ArrayList<Potion> criarPocoes() {
        ArrayList<Potion> pocoes = new ArrayList();
        Potion p1 = new Potion("img/potioncircunflexo.png", 817, 93, "^");//string da imagem, posicao x e y, string do acento gráfico
        Potion p2 = new Potion("img/potiontiu.png", 882, 93, "~");
        Potion p3 = new Potion("img/potionagudo.png", 940, 93, "´");

        pocoes.add(p1);
        pocoes.add(p2);
        pocoes.add(p3);

        return pocoes;
    }
    
    private Jogador criarJogador() {
        String nome;
        Jogador novo;
        nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        novo = new Jogador(nome);
    
        for(Jogador jogo : Inicio.lista){
            if(novo.getNome().equalsIgnoreCase(jogo.getNome())){
                jogador = Inicio.lista.remove();
                return jogador;
            }
        }
        
        jogador = novo;
        return jogador;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
