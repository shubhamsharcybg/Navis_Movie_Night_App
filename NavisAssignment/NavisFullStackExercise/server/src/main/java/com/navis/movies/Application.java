package com.navis.movies;



import com.navis.movies.dao.MoviesDAO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = "com.navis.movies")

public class Application {

    public static final String DEFAULT_URL = "/api";
    public static final String GENRE_URL = DEFAULT_URL + "/genre";
    public static final String MOVIE_URL = DEFAULT_URL + "/movie";


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    MoviesDAO moviesDAO;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Application Started");
        };
    }
}
