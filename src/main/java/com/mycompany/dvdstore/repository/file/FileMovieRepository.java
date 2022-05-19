package com.mycompany.dvdstore.repository.file;

import com.mycompany.dvdstore.entity.Movie;
import com.mycompany.dvdstore.repository.MovieRepositoryInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Repository
public class FileMovieRepository implements MovieRepositoryInterface {

    @Value("${movies.file.location}")
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void add(Movie movie){

        FileWriter writer ;
        try{
            writer=new FileWriter(file,true);
            //writer=new FileWriter("C:\\temp\\movies.txt", true);  le chemin dacces est dans le fichier de configuration propre application context


            //petite parenthese qui affiche le contenu du fichier
            //FileInputStream files = new FileInputStream("C:\\temp\\movies.txt");
            FileInputStream files = new FileInputStream(file);
            Scanner scanner = new Scanner(files);

            //renvoie true s'il y a une autre ligne à lire
            while(scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
            //Fin de la petite parenthese qui affiche le contenu du fichier


            writer.write(movie.getTitle()+";"+movie.getGenre()+"\n");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("The movie "+movie.getTitle()+" has been added");

    }
}
