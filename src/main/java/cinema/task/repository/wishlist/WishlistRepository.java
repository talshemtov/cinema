package cinema.task.repository.wishlist;

import cinema.task.models.dao.MovieEntity;
import cinema.task.models.dao.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer> {
}
