package show.ff.kasoale.it.ffshow.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kasoale on 13/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDetailsList implements Serializable{
    private ArrayList<FilmDetail> filmsDetails;

    @JsonProperty("results")
    public ArrayList<FilmDetail> getFilmsDetails() {
        return filmsDetails;
    }

    @JsonProperty("results")
    public void setFilmsDetails(ArrayList<FilmDetail> filmsDetails) {
        this.filmsDetails = filmsDetails;
    }

    @Override
    public String toString(){
        String result = null;
        for (FilmDetail filmDetail : this.filmsDetails) {
            result += filmDetail.toString() + "\n\n";

        }

        return result;
    }
}
