package paper.projetowebpaper.repository;

import org.springframework.data.repository.CrudRepository;
import paper.projetowebpaper.models.RatingModel;

public interface RatingRepository extends CrudRepository<RatingModel, Integer> {
}
