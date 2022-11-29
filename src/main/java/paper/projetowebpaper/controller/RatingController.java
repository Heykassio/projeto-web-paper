package paper.projetowebpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import paper.projetowebpaper.models.RatingModel;
import paper.projetowebpaper.models.RecipeModel;
import paper.projetowebpaper.models.UserModel;
import paper.projetowebpaper.repository.RatingRepository;
import paper.projetowebpaper.repository.RecipeRepository;
import paper.projetowebpaper.repository.UserRepository;
import paper.projetowebpaper.viewmodels.rating.RatingViewModel;

import java.util.Optional;

@RestController
public class RatingController {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @PostMapping("api/rating")
    public ResponseEntity PostRating(@RequestBody RatingViewModel rating){
        Optional<RatingModel> ratting = repository.findById(rating.id);

        if(ratting.isEmpty()) {
            RatingModel rattingSave = new RatingModel();

            Optional<RecipeModel> recipeOpt = recipeRepository.findById(rating.recipeId);
            Optional<UserModel> authorOpt = userRepository.findById(rating.authorId);

            if(authorOpt.isEmpty() || recipeOpt.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            rattingSave.rate = rating.rate;
            rattingSave.author = authorOpt.get();
            rattingSave.recipe = recipeOpt.get();

            repository.save(rattingSave);

            return ResponseEntity.ok().body(rating);
        }
        RatingModel ratingSave = ratting.get();
        ratingSave.rate = rating.rate;

        repository.save(ratingSave);


        return ResponseEntity.ok().body(rating);
    }
}
