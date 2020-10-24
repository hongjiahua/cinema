package com.gnnu.movie.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "movie")
@Data
public class Movie implements Serializable {
    @Id
    @Column(name = "Movie_ID")
    private int movieId;
    @Column(name = "Movie_Name")
    private String movieName;
    @Column(name = "Movie_MainActor")
    private String movieMainActor;
    @Column(name = "Movie_Director")
    private String movieDirector;
    @Column(name = "Movie_Duraction")
    private String movieDuraction;
    @Column(name = "Movie_Class")
    private String movieClass;
    @Column(name = "Movie_Description")
    private String movieDescription;
    @Column(name = "Movie_Image")
    private String movieImage;
    @Column(name = "Movie_Score")
    private String movieScore;
}
