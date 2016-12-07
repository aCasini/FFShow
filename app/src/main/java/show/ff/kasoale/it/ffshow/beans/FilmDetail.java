package show.ff.kasoale.it.ffshow.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

import show.ff.kasoale.it.ffshow.utils.Utilis;

/**
 * Created by acasini on 05/12/16.
 */

public class FilmDetail implements Serializable{

    private boolean adult;
    private String overview;
    private String releaseDate;
    private int id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private int popularity;
    private double voteAverage;
    private String posterPath;
    private int[] genere;

    @JsonProperty("genre_ids")
    public int[] getGenere() {
        return genere;
    }

    @JsonProperty("genre_ids")
    public void setGenere(int[] genere) {
        this.genere = genere;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("popularity")
    public int getPopularity() {
        return popularity;
    }

    @JsonProperty("vote_average")
    public double getVoteAverage() {
        return voteAverage;
    }

    @JsonProperty("original_language")
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @JsonProperty("original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    @JsonProperty("overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("poster_path")
    public String getPosterPath() {
        return posterPath;
    }

    @JsonProperty("release_date")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("adult")
    public boolean isAdult() {
        return adult;
    }

    @JsonProperty("adult")
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("original_title")
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @JsonProperty("original_language")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("popularity")
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("poster_path")
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @JsonProperty("release_date")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("vote_average")
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String toString(){
        return "title: "+this.getTitle() + "\n"
                    + "Titolo Originale: "+ this.getOriginalTitle() + "\n"
                    + "Media Voto: "+this.getVoteAverage();
    }

}
