package cinema.task.manager;

import cinema.task.clients.OmdbClient;
import cinema.task.models.GetMoviesResponse;
import cinema.task.models.Movie;
import cinema.task.models.dao.WishlistEntity;
import cinema.task.repository.movies.CinemaRepositoryManager;
import cinema.task.repository.wishlist.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesManager {

    @Autowired
    private OmdbClient omdbClient;
    @Autowired
    private CinemaRepositoryManager cinemaRepositoryManager;
    @Autowired
    private WishlistRepository wishlistRepository;

    public GetMoviesResponse getMovieByTitle(String title, int page) {
        GetMoviesResponse getMoviesResponse = new GetMoviesResponse();
        List<Movie> movies = cinemaRepositoryManager.getByTitle(title);
        if (movies.isEmpty()) {
            movies = omdbClient.getMoviesByTitle(title, page);
            if (movies != null && !movies.isEmpty()) {
                cinemaRepositoryManager.addAll(movies);
            }
            getMoviesResponse.setRetrievedFromDb(false);
        } else {
            getMoviesResponse.setRetrievedFromDb(true);
        }
        getMoviesResponse.setMovies(movies);
        return getMoviesResponse;
    }

    public void addToWishlist(Integer id) {
        wishlistRepository.save(new WishlistEntity(id));
    }

    public void removeFromWishlist(Integer id) {
        wishlistRepository.delete(new WishlistEntity(id));
    }

    public List<String> getWishlist() {
        return wishlistRepository.findAll().stream().map(w -> w.getMovieId().toString()).collect(Collectors.toList());
    }
}
