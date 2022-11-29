package paper.projetowebpaper.repository;

import org.springframework.data.repository.CrudRepository;
import paper.projetowebpaper.models.CommentModel;

public interface CommentRepository extends CrudRepository<CommentModel, Integer> {
}
