package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {



    @Autowired
    private MoviesBean moviesBean ;






    @GetMapping("/setup")
    @Transactional
    public String setup(Map<String, Object> model) {
        moviesBean.addMovie(new Movie("Bahubali", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Magadheera", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Lucky the racer", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("Badhsah", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("troy", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Tuxedo", "Tom Dey", "Comedy", 7, 2000));

        model.put("movies", moviesBean.getMovies());
        return "setup";
    }

    @GetMapping("/")
    public String indexRenderer(){
        return "index";
    }

}
