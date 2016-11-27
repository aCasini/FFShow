package show.ff.kasoale.it.ffshow.utils;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.SerieTV;

/**
 * Created by kasoale on 05/11/2016.
 */

public class Utilis {

    private static Logger logger = Logger.getLogger("Utils");

    public static String infoSerie;

    public static Film json2Film(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        Film film = null;
        try {
            film = mapper.readValue(jsonString, Film.class);
        }catch (JsonMappingException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }

        return film;
    }

    public static SerieTV json2SerieTV(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        SerieTV serieTV = null;

        try {
            serieTV = mapper.readValue(jsonString, SerieTV.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  serieTV;
    }

    public static Film[] json2FilmList(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        Film[] filmList = null;
        try {
            filmList = mapper.readValue(jsonString, Film[].class);
        }catch (JsonMappingException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }

        return filmList;
    }

    public static void main(String[] args){
        String json= "{\"filmName\":\"Il Re Leone 3 hakuna matata\",\"imageUrl\":\"http://www.filmpertutti.click/wp-content/uploads/2012/01/ilreleone3hakunamatata.jpeg\",\"filmUrl\":\"http://www.filmpertutti.click/il-re-leone-3-hakuna-matata/\",\"streamingUrl\":\"http://speedvideo.net/embed-23vtr6nfcimk-530x302.html\",\"valid\":true}";
        logger.info(Utilis.json2Film(json).toString());

        String jsonList = "[{\"filmName\":\"Il Re Leone 3 hakuna matata\",\"imageUrl\":\"http://www.filmpertutti.click/wp-content/uploads/2012/01/ilreleone3hakunamatata.jpeg\",\"filmUrl\":\"http://www.filmpertutti.click/il-re-leone-3-hakuna-matata/\",\"streamingUrl\":\"http://speedvideo.net/embed-23vtr6nfcimk-530x302.html\",\"valid\":true},{\"filmName\":\"Il Re Leone 2: il regno di Simba\",\"imageUrl\":\"http://www.filmpertutti.click/wp-content/uploads/2012/01/IlReLeoneIIilregnodiSimba.jpeg\",\"filmUrl\":\"http://www.filmpertutti.click/il-re-leone-2-il-regno-di-simba/\",\"streamingUrl\":\"http://speedvideo.net/embed-rog7m9uuling-530x302.html\",\"valid\":true},{\"filmName\":\"Il Re Leone\",\"imageUrl\":\"http://www.filmpertutti.click/wp-content/uploads/2012/01/ilReLeone.jpeg\",\"filmUrl\":\"http://www.filmpertutti.click/il-re-leone/\",\"streamingUrl\":\"http://speedvideo.net/embed-fgd95cgxs3ov-530x302.html\",\"valid\":true},{\"filmName\":\"Le cronache di Narnia – Il leone, la strega e l’armadio (2005)\",\"imageUrl\":\"http://www.filmpertutti.click/wp-content/uploads/2014/11/2rWpu4.jpg\",\"filmUrl\":\"http://www.filmpertutti.click/le-cronache-di-narnia-il-leone-la-strega-e-larmadio-2005/\",\"streamingUrl\":\"http://speedvideo.net/embed-jeqdrkojwmc2-530x302.html\",\"valid\":true}]";
        Film[] filmsList = Utilis.json2FilmList(jsonList);
        logger.info("===================");
        for (int i = 0; i < filmsList.length; i++) {
            logger.info(filmsList[i].toString());
        }
        logger.info("===================");

    }

    public static String getInfoSerie() {
        return infoSerie;
    }

    public static void setInfoSerie(String infoSerie) {
        Utilis.infoSerie = infoSerie;
    }
}
