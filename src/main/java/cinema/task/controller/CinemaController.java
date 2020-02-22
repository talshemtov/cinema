package cinema.task.controller;

import cinema.task.manager.MoviesManager;
import cinema.task.models.GetMoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class CinemaController {

    @Autowired
    private MoviesManager moviesManager;

    @GetMapping("/{title}/{page}")
    @ResponseStatus(HttpStatus.OK)
    public GetMoviesResponse getMovieByTitle(@PathVariable("title") String title, @PathVariable("page") int page) {
        return moviesManager.getMovieByTitle(title, page);
    }

    @PostMapping("/wishlist/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToWishlist(@PathVariable("id") Integer id) {
        moviesManager.addToWishlist(id);
    }

    @DeleteMapping("/wishlist/{id}")
    public void removeFromWishlist(@PathVariable("id") Integer id) {
        moviesManager.removeFromWishlist(id);
    }

    @GetMapping("/wishlist")
    public List<String> getWishlist() {
        return moviesManager.getWishlist();
    }
}
