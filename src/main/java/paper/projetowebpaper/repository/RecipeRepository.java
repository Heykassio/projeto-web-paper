package paper.projetowebpaper.repository;

import org.springframework.data.repository.CrudRepository;
import paper.projetowebpaper.models.RecipeModel;

public interface RecipeRepository extends CrudRepository<RecipeModel, Integer> {
}
