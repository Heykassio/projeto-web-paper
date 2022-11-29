package paper.projetowebpaper.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paper.projetowebpaper.models.*;
import paper.projetowebpaper.repository.*;
import paper.projetowebpaper.viewmodels.image.ImageViewModel;
import paper.projetowebpaper.viewmodels.initial.InitialInformationViewModel;
import paper.projetowebpaper.viewmodels.recipe.RecipeViewModel;
import paper.projetowebpaper.viewmodels.user.UserViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class InitialController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/api/initial")
    public ResponseEntity GetInitialInformations(){
        List<UserModel> users = (List<UserModel>) userRepository.findAll();
        List<CommentModel> comments = (List<CommentModel>) commentRepository.findAll();
        List<ImagemModel> imagens = (List<ImagemModel>) imageRepository.findAll();
        List<RecipeModel> recipes = (List<RecipeModel>) recipeRepository.findAll();
        List<RecipeViewModel> recipesViewModel = new ArrayList<>();
        for (RecipeModel recipe: recipes ) {
            double rate = 0;
            int rateAll = 0;
            if(recipe.ratings.size() != 0) {
                for (RatingModel rating: recipe.ratings) {
                    rateAll += rating.rate;
                }
                rate = rateAll / recipe.ratings.size();
            }

            UserModel author = recipe.author;
            List<ImageViewModel> images = new ArrayList<>();

            for (ImagemModel image: recipe.images) {
                images.add(new ImageViewModel(image.id, image.url, recipe.id));
            }

            UserViewModel userRecipe = new UserViewModel(author.id, author.name, author.email, author.createdAt, author.updatedAt);

            recipesViewModel.add(new RecipeViewModel(recipe.id, recipe.name, recipe.steps, recipe.ingredients, recipe.timeInMinutes, userRecipe, rate, images, null, null));
        }

        Collections.sort(recipesViewModel, new Comparator<RecipeViewModel>(){
            public int compare(RecipeViewModel s1, RecipeViewModel s2) {
                return s1.rate.compareTo(s2.rate);
            }
        });

        List<RecipeViewModel> recipesReturn = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            if(i >= recipesViewModel.size()){
                break;
            }
            recipesReturn.add(recipesViewModel.get(i));
        }

        InitialInformationViewModel initial = new InitialInformationViewModel(recipesReturn, users.size(), imagens.size(), comments.size(), recipesViewModel.size());

        return ResponseEntity.ok().body(initial);

    }
}
