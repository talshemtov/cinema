package cinema.task.repository.movies;

import cinema.task.models.Movie;
import cinema.task.models.dao.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<MovieEntity, Integer> {
}
