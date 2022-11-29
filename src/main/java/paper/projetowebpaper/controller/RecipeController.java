package paper.projetowebpaper.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paper.projetowebpaper.models.*;
import paper.projetowebpaper.repository.*;
import paper.projetowebpaper.viewmodels.comment.CommentViewModel;
import paper.projetowebpaper.viewmodels.image.ImageViewModel;
import paper.projetowebpaper.viewmodels.rating.RatingViewModel;
import paper.projetowebpaper.viewmodels.recipe.RecipeRegisterViewModel;
import paper.projetowebpaper.viewmodels.recipe.RecipeViewModel;
import paper.projetowebpaper.viewmodels.user.UserViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping(path = "/api/recipe")
    public ResponseEntity GetRecipes(){
        List<RecipeModel> recipes = (List<RecipeModel>) repository.findAll();
        List<RecipeViewModel> recipesReturn = new ArrayList<>();
        for (RecipeModel recipe: recipes) {
            double rate = 0;
            int rateAll = 0;
            if(recipe.ratings.size() != 0) {
                for (RatingModel rating: recipe.ratings) {
                    rateAll += rating.rate;
                }
                rate = rateAll / recipe.ratings.size();
            }

            UserModel author = recipe.author;

            UserViewModel user = new UserViewModel(author.id, author.name, author.email, author.createdAt, author.updatedAt);

            recipesReturn.add(new RecipeViewModel(recipe.id, recipe.name, recipe.steps, recipe.ingredients,
                    recipe.timeInMinutes, user, rate, null, null, null ));
        }

        return ResponseEntity.ok().body(recipesReturn);
    }

    @GetMapping(path = "/api/recipe/user/{id}")
    public ResponseEntity GetUserRecipes(@PathVariable("id") Integer Id) {
        Optional<UserModel> userOpt = userRepository.findById(Id);

        if(userOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        UserModel user = userOpt.get();
        List<RecipeModel> recipes = user.recipes;
        List<RecipeViewModel> userRecipes = new ArrayList<>();

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

            userRecipes.add(new RecipeViewModel(recipe.id, recipe.name, recipe.steps, recipe.ingredients, recipe.timeInMinutes, userRecipe, rate, images, null, null));
        }

        return ResponseEntity.ok().body(userRecipes);
    }

    @GetMapping(path = "/api/recipe/{id}")
    public ResponseEntity GetRecipe(@PathVariable("id") Integer Id){
        Optional<RecipeModel> recipeOpt = repository.findById(Id);

        if(recipeOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        RecipeModel recipe = recipeOpt.get();

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

        List<CommentViewModel> comments = new ArrayList<>();

        for (CommentModel comment: recipe.comments) {
            comments.add(new CommentViewModel(comment.id, comment.content, comment.createdAt, comment.author.id, comment.recipe.id));
        }

        List<RatingViewModel> ratings = new ArrayList<>();

        for (RatingModel rating: recipe.ratings) {
            ratings.add(new RatingViewModel(rating.id, rating.rate, recipe.id, author.id));
        }

        UserViewModel userRecipe = new UserViewModel(author.id, author.name, author.email, author.createdAt, author.updatedAt);

        RecipeViewModel recipeReturn = new RecipeViewModel(recipe.id, recipe.name, recipe.steps, recipe.ingredients, recipe.timeInMinutes, userRecipe, rate, images, comments, ratings);

        return ResponseEntity.ok().body(recipeReturn);
    }

    @PostMapping(path = "/api/recipe")
    public ResponseEntity PostRecipe(@RequestBody RecipeRegisterViewModel recipe) {
        Optional<UserModel> userOpt = userRepository.findById(recipe.authorId);

        if(userOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<ImagemModel> images = new ArrayList<>();

        for (ImageViewModel image: recipe.images) {
            ImagemModel imageSave = new ImagemModel();
            imageSave.url = image.url;

            images.add(imageSave);
        }

        UserModel user = userOpt.get();

        RecipeModel recipeSave = new RecipeModel(recipe.name,
                recipe.steps, recipe.ingredients, recipe.timeInMinutes, LocalDateTime.now(),
                LocalDateTime.now(), user, null, null, images);
        RecipeModel recipeReturn = repository.save(recipeSave);

        for (ImagemModel image: images) {
            image.recipe = recipeReturn;
            imageRepository.save(image);
        }

        UserViewModel author = new UserViewModel(user.id, user.name, user.email, user.createdAt, user.updatedAt);
        return ResponseEntity.ok().body(new RecipeViewModel(recipeReturn.id, recipeReturn.name, recipeReturn.steps,
                recipeReturn.ingredients, recipeReturn.timeInMinutes, author, 0.0, recipe.images, null, null));
    }

    @PutMapping(path = "/api/recipe")
    public ResponseEntity EditRecipe(@RequestBody RecipeRegisterViewModel recipe){
        Optional<RecipeModel> recipeEditOpt = repository.findById(recipe.id);

        if(recipeEditOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        RecipeModel recipeEdit = recipeEditOpt.get();

        for (ImagemModel image: recipeEdit.images) {
            imageRepository.delete(image);
        }

        List<ImagemModel> images = new ArrayList<>();

        for (ImageViewModel image: recipe.images) {
            ImagemModel imageSave = new ImagemModel();
            imageSave.url = image.url;
            imageSave.recipe = recipeEdit;
            images.add(imageSave);
        }

        recipeEdit.name = recipe.name;
        recipeEdit.steps = recipe.steps;
        recipeEdit.ingredients = recipe.ingredients;
        recipeEdit.timeInMinutes = recipe.timeInMinutes;
        recipeEdit.images = images;
        recipeEdit.updateAt = LocalDateTime.now();

        repository.save(recipeEdit);

        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping(path = "/api/recipe/{id}")
    public ResponseEntity DeleteRecipe(@PathVariable Integer Id){
        Optional<RecipeModel> recipeOpt = repository.findById(Id);

        if(recipeOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        RecipeModel recipe = recipeOpt.get();

        for (ImagemModel image: recipe.images) {
            imageRepository.delete(image);
        }

        for (CommentModel comment: recipe.comments) {
            commentRepository.delete(comment);
        }

        for(RatingModel rating: recipe.ratings){
            ratingRepository.delete(rating);
        }

        repository.delete(recipe);

        return ResponseEntity.ok().body("Excluído com sucesso.");
    }
}
