package cinema.task.models.dao;

import cinema.task.models.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "year")
    private String year;
    @Column(name = "rated")
    private String rated;
    @Column(name = "released")
    private String released;
    @Column(name = "runtime")
    private String runtime;
    @Column(name = "genre")
    private String genre;
    @Column(name = "director")
    private String director;
    @Column(name = "writer")
    private String writer;
    @Column(name = "actors")
    private String actors;
    @Column(name = "plot")
    private String plot;
    @Column(name = "language")
    private String language;
    @Column(name = "country")
    private String country;
    @Column(name = "awards")
    private String awards;
    @Column(name = "poster")
    private String poster;
    @Column(name = "metascore")
    private String metaScore;
    @Column(name = "imdb_rating")
    private String imdbRating;
    @Column(name = "imdb_votes")
    private String imdbVotes;
    @Column(name = "imdb_id")
    private String imdbID;
    @Column(name = "type")
    private String type;
    @Column(name = "dvd")
    private String dvd;
    @Column(name = "box_office")
    private String boxOffice;
    @Column(name = "production")
    private String production;
    @Column(name = "website")
    private String website;
    @Column(name = "response")
    private String response;

    public MovieEntity(Movie movie) {
        this.title = movie.getTitle();
        this.year = movie.getYear();
        this.rated = movie.getRated();
        this.released = movie.getReleased();
        this.runtime = movie.getRuntime();
        this.genre = movie.getGenre();
        this.director = movie.getDirector();
        this.writer = movie.getWriter();
        this.actors = movie.getActors();
        this.plot = movie.getPlot();
        this.language = movie.getLanguage();
        this.country = movie.getCountry();
        this.awards = movie.getAwards();
        this.poster = movie.getPoster();
        this.metaScore = movie.getMetaScore();
        this.imdbRating = movie.getImdbRating();
        this.imdbVotes = movie.getImdbVotes();
        this.imdbID = movie.getImdbID();
        this.type = movie.getType();
        this.dvd = movie.getDvd();
        this.boxOffice = movie.getBoxOffice();
        this.production = movie.getProduction();
        this.website = movie.getWebsite();
        this.response = movie.getResponse();
    }
}
