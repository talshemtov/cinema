package cinema.task.repository.movies;

import cinema.task.models.Movie;
import cinema.task.models.dao.MovieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CinemaRepositoryManager {
    private CinemaRepository repository;

    @Transactional
    public void insert(Movie movie) {
        MovieEntity movieEntity = new MovieEntity(movie);
        repository.save(movieEntity);
    }

    @Transactional
    public List<Movie> getByTitle(String title) {
        List<MovieEntity> movieEntities = repository.findAll();
        movieEntities = movieEntities.stream().filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        return movieEntities.stream().map(Movie::new).collect(Collectors.toList());
    }

    @Transactional
    public void addAll(List<Movie> movies) {
        movies.forEach(movie -> {
            MovieEntity movieEntity = repository.save(new MovieEntity(movie));
            movie.setId(movieEntity.getId());
        });
    }
}
