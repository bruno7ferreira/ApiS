package IMDb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradorDeFigurinhas {

    // métodos
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // fazer leitura da imagem
        //(imagem local) InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));

        //(usando uma url) InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        //(imagem local)  BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme.jpg"));

        // cria nova imagem em memoria com transparencia e tamanho novo
        int imagemLargura = imagemOriginal.getWidth();
        int imagemAltura = imagemOriginal.getHeight();
        int novaImagemAltura = imagemAltura + 200;
        BufferedImage novaImagem = new BufferedImage(imagemLargura, novaImagemAltura, BufferedImage.TRANSLUCENT);

        // cópiar a imagem original para nova imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,null,0,0);

        // configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.setColor(Color.YELLOW);
        graphics.drawString("TOPZERA",370,novaImagemAltura-80);

        // escrever a nova imagem em um arquivo quando usar url
        //ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

    /* main somente para teste
    public static void main(String[] args) throws Exception {

        GeradorDeFigurinhas geradorDeFigurinhas = new GeradorDeFigurinhas();
        geradorDeFigurinhas.cria();

        System.out.println("Arquivo gerado com sucesso!");

    }

     */

}
