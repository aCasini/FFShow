package show.ff.kasoale.it.ffshow.beans;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by kasoale on 08/12/2016.
 */

public class SerieTvDetails implements Serializable{

    private String posterPath;
    private double popularity;
    private Integer id;
    private String backdropPath;
    private double voteCount;
    private String overview;
    private String firstAirDate;
    private String[] originalCountry;
    private Integer[] gereriId;
    private String originalLanguage;
    private String name;
    private String originalName;

    @JsonProperty("poster_path")
    public String getPosterPath() {
        return posterPath;
    }

    @JsonProperty("poster_path")
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @JsonProperty("popularity")
    public double getPopularity() {
        return popularity;
    }

    @JsonProperty("popularity")
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("backdrop_path")
    public String getBackdropPath() {
        return backdropPath;
    }

    @JsonProperty("backdrop_path")
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    @JsonProperty("vote_count")
    public double getVoteCount() {
        return voteCount;
    }

    @JsonProperty("vote_count")
    public void setVoteCount(double voteCount) {
        this.voteCount = voteCount;
    }

    @JsonProperty("overview")
    public String getOverview() {
        return overview;
    }

    @JsonProperty("overview")
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @JsonProperty("first_air_date")
    public String getFirstAirDate() {
        return firstAirDate;
    }

    @JsonProperty("first_air_date")
    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    @JsonProperty("origin_country")
    public String[] getOriginalCountry() {
        return originalCountry;
    }

    @JsonProperty("origin_country")
    public void setOriginalCountry(String[] originalCountry) {
        this.originalCountry = originalCountry;
    }

    @JsonProperty("genre_ids")
    public Integer[] getGereriId() {
        return gereriId;
    }

    @JsonProperty("genre_ids")
    public void setGereriId(Integer[] gereriId) {
        this.gereriId = gereriId;
    }

    @JsonProperty("original_language")
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @JsonProperty("original_language")
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("original_name")
    public String getOriginalName() {
        return originalName;
    }

    @JsonProperty("original_name")
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
