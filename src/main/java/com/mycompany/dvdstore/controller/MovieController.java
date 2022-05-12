package com.mycompany.dvdstore.controller;

import com.mycompany.dvdstore.entity.Movie;
import com.mycompany.dvdstore.service.MovieService;

import java.util.Scanner;

public class MovieController {

    MovieService movieService;

    public void addUsingConsole(){

        Movie movie= new Movie();

        Scanner sc=new Scanner(System.in);
        System.out.println("Entrez le titre du film");
        String movieTitle=sc.nextLine();
        System.out.println("Entrez le genre du film");
        String movieGenre=sc.nextLine();
        movie.setTitle(movieTitle);
        movie.setGenre(movieGenre);
        movieService=new MovieService();
        movieService.registerMovie(movie);
    }
}
