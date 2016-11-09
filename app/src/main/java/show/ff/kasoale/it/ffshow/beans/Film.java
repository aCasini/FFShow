package show.ff.kasoale.it.ffshow.beans;

import java.io.Serializable;

/**
 * Created by kasoale on 05/11/2016.
 */

public class Film implements Serializable{

    private String filmName;
    private String imageUrl;
    private String filmUrl;
    private String streamingUrl;
    private boolean valid;

    public String getFilmName() {
        return filmName;
    }

    public String getFilmUrl() {
        return filmUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStreamingUrl() {
        return streamingUrl;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmUrl(String filmUrl) {
        this.filmUrl = filmUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStreamingUrl(String streamingUrl) {
        this.streamingUrl = streamingUrl;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "\nFilm Name: " + this.filmName + "\n"
                + "Film URL: "+ this.filmUrl + "\n"
                + "Image URL: "+ this.imageUrl + "\n"
                + "Streaming URL: "+ this.streamingUrl;
    }
}
