package com.mycompany.dvdstore;

import com.mycompany.dvdstore.controller.MovieController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */

@SpringBootApplication
//@Configuration
//@ComponentScan(basePackages = {"com.mycompany.dvdstore.controller", "com.mycompany.dvdstore.service", "com.mycompany.dvdstore.repository.file"})
//@PropertySource("classpath:application.properties")
public class App
{
    public static void main( String[] args )
    {
        //ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class, AppConfigPropertySource.class);
        //ApplicationContext context=new AnnotationConfigApplicationContext(App.class);
        ApplicationContext context= SpringApplication.run(App.class,args);
        MovieController movieController=context.getBean(MovieController.class);

        movieController.addUsingConsole();

    }
}


 /*MovieController movieController=new MovieController();
        DefaultMovieService movieService=new DefaultMovieService();
        movieController.setMovieService(movieService);
        FileMovieRepository goLiveMovieRepository=new FileMovieRepository();
        movieService.setMovieRepository(goLiveMovieRepository);*/