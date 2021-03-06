package show.ff.kasoale.it.ffshow.utils;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import show.ff.kasoale.it.ffshow.beans.Film;
import show.ff.kasoale.it.ffshow.beans.FilmDetail;
import show.ff.kasoale.it.ffshow.beans.FilmDetailsList;
import show.ff.kasoale.it.ffshow.beans.SerieTV;
import show.ff.kasoale.it.ffshow.beans.SerieTvDetails;

/**
 * Created by kasoale on 05/11/2016.
 */

public class Utilis {

    private static Logger logger = Logger.getLogger("Utils");

    public static String infoSerie;
    public static Map<Integer,String> genereMap = new HashMap<>();

    public static void initMap(){
        genereMap.put(28, "Action");
        genereMap.put(12, "Adventure");
        genereMap.put(16, "Animation");
        genereMap.put(35, "Comedy");
        genereMap.put(80, "Crime");
        genereMap.put(99, "Documentary");
        genereMap.put(18, "Drama");
        genereMap.put(10751, "Family");
        genereMap.put(14, "Fantasy");
        genereMap.put(36, "History");
        genereMap.put(27, "Horror");
        genereMap.put(10402, "Music");
        genereMap.put(9648, "Mystery");
        genereMap.put(10749, "Romance");
        genereMap.put(878, "Science Fiction");
        genereMap.put(10770, "TV Movie");
        genereMap.put(53, "Thriller");
        genereMap.put(10752, "War");
        genereMap.put(37, "Western");
    }

    public static FilmDetail json2FilmDetail(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        FilmDetail filmDetail = null;

        try {
            filmDetail = mapper.readValue(jsonString, FilmDetail.class);
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

        return filmDetail;

    }

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

    public static SerieTvDetails json2SerieTVDeetail(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        SerieTvDetails serieTVDetails = null;

        try {
            serieTVDetails = mapper.readValue(jsonString, SerieTvDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  serieTVDetails;
    }

    public static FilmDetailsList json2FilmDetailsList(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        FilmDetailsList filmDetailsList = null;
        try{
            filmDetailsList = mapper.readValue(jsonString, FilmDetailsList.class);
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

        return filmDetailsList;
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
