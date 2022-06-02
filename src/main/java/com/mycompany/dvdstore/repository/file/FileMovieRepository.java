package com.mycompany.dvdstore.repository.file;

import com.mycompany.dvdstore.entity.Movie;
import com.mycompany.dvdstore.repository.MovieRepositoryInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public Movie add(Movie movie){

        FileWriter writer ;
        try{
            long lastId=list().stream().map(Movie::getId).max(Long::compare).orElse(0L);
            movie.setId(lastId+1);
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


            writer.write(movie.getId()+";"+movie.getTitle()+";"+movie.getGenre()+";"+movie.getDescription()+"\n");            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("The movie "+movie.getTitle()+" has been added");
        return movie;
    }

    @Override
    public List<Movie> list() {
        List<Movie> movies=new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                final Movie movie=new Movie();
                final String[] titreEtGenre = line.split("\\;");
                movie.setId(Long.valueOf(titreEtGenre[0]));
                movie.setTitle(titreEtGenre[1]);
                movie.setGenre(titreEtGenre[2]);
                movie.setDescription(titreEtGenre[3]);
                movies.add(movie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getById(long id) {
        final Movie movie = new Movie();
        movie.setId(id);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {

                final String[] allProperties = line.split("\\;");
                final long nextMovieId=Long.parseLong(allProperties[0]);
                if (nextMovieId==id) {
                    movie.setTitle(allProperties[1]);
                    movie.setGenre(allProperties[2]);
                    movie.setDescription(allProperties[3]);
                    return movie;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("A movie from the file does not have a proper id");
            e.printStackTrace();
        }
        movie.setTitle("UNKNOWN");
        movie.setGenre("UNKNOWN");
        movie.setDescription("UNKNOWN");
        return movie;
    }
}
