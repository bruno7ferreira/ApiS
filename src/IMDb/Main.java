package IMDb;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpResponse.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Olá mundo!");
        // fazer uma conexao http e pegar os dados do imdb
        String url = "https://imdb-api.com/en/API/Top250Movies/k_tmhk2tbz";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println();

        // pegar somente dos dados que interessam(titulo,imagem,nota)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));


        // exibir e manipular os dados

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Titulo: " + filme.get("title"));
            //System.out.println("imagem -> " + filme.get("image"));
            //System.out.println("Nota: " + filme.get("imDbRating"));
            System.out.println();
        }

    }

}
