package com.example.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Marathon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private LocalDateTime startTime;

    public Marathon(){}

    public Marathon(Long id, String name, LocalDateTime startTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Marathon))
            return false;
        Marathon other = (Marathon) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() { return 31;}

    @Override
    public String toString() {
        return "Marathon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
            name = "marathon_movie",
            joinColumns = @JoinColumn(name = "marathon_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie>movies;

    public List<Movie>getMovies(){
        if(movies==null){
            movies = new ArrayList<>();
        }
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
