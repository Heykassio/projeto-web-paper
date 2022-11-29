package paper.projetowebpaper.repository;

import org.springframework.data.repository.CrudRepository;
import paper.projetowebpaper.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String Email);
}
