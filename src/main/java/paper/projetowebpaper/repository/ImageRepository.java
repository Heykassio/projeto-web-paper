package paper.projetowebpaper.repository;

import org.springframework.data.repository.CrudRepository;
import paper.projetowebpaper.models.ImagemModel;

public interface ImageRepository extends CrudRepository<ImagemModel, Integer> {
}
