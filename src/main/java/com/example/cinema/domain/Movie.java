package com.example.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIgnoreProperties(ignoreUnknown=true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private EMovieCategory category;
    private Integer lenght;
    private String description;
    private Integer requiredAge;

    public Movie(){}

    public Movie(Long id, String title, EMovieCategory category, Integer lenght, String description, Integer requiredAge) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.lenght = lenght;
        this.description = description;
        this.requiredAge = requiredAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EMovieCategory getCategory() {
        return category;
    }

    public void setCategory(EMovieCategory category) {
        this.category = category;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(Integer requiredAge) {
        this.requiredAge = requiredAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie))
            return false;
        Movie other = (Movie) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() { return 31;}

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", lenght=" + lenght +
                ", description='" + description + '\'' +
                ", requiredAge=" + requiredAge +
                '}';
    }

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Marathon>marathons;

    public List<Marathon>getMarathons(){
        if (marathons==null){
            marathons = new ArrayList<>();
        }
        return marathons;
    }

    public void setMarathons(List<Marathon>marathons){this.marathons = marathons;}
}
