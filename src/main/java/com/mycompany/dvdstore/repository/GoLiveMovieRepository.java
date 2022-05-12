package com.mycompany.dvdstore.repository;

import com.mycompany.dvdstore.entity.Movie;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GoLiveMovieRepository {

    public void add(Movie movie){

        FileWriter writer ;
        try{
            writer=new FileWriter("C:\\temp\\movies.txt", true);

            FileInputStream file = new FileInputStream("C:\\temp\\movies.txt");
            Scanner scanner = new Scanner(file);

            //renvoie true s'il y a une autre ligne à lire
            while(scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
            scanner.close();



            writer.write(movie.getTitle()+";"+movie.getGenre()+"\n");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("The movie "+movie.getTitle()+" has been added");

    }
}
