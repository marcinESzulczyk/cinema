package com.example.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String filePath;

    public Poster(){}

    public Poster(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {

            if (this == o) return true;
            if (!(o instanceof Poster))
                return false;
            Poster other = (Poster) o;
            return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() { return 31;}

    @Override
    public String toString() {
        return "Poster{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @OneToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
