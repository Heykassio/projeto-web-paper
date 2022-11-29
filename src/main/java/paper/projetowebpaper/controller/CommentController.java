package paper.projetowebpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paper.projetowebpaper.models.CommentModel;
import paper.projetowebpaper.models.RecipeModel;
import paper.projetowebpaper.models.UserModel;
import paper.projetowebpaper.repository.CommentRepository;
import paper.projetowebpaper.repository.RecipeRepository;
import paper.projetowebpaper.repository.UserRepository;
import paper.projetowebpaper.viewmodels.comment.CommentViewModel;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/api/comment")
    public ResponseEntity PostComment(@RequestBody CommentViewModel comment){
        CommentModel commentSave = new CommentModel();
        Optional<UserModel> AuthorOpt = userRepository.findById(comment.authorId);
        Optional<RecipeModel> recipeOpt = recipeRepository.findById(comment.recipeId);
        commentSave.author = AuthorOpt.get();
        commentSave.recipe = recipeOpt.get();
        commentSave.createdAt = LocalDateTime.now();

        commentRepository.save(commentSave);

        return ResponseEntity.ok().body(comment);
    }

    @PutMapping(path = "/api/comment")
    public ResponseEntity EditComment(@RequestBody CommentViewModel comment){
        Optional<CommentModel> commentEdit = commentRepository.findById(comment.id);
        if(commentEdit.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        CommentModel commentSave = commentEdit.get();
        commentSave.content = comment.content;
        commentRepository.save(commentSave);

        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping(path = "/api/comment/{id}")
    public ResponseEntity DeleteComment(@PathVariable("id") Integer Id){
        Optional<CommentModel> commentEdit = commentRepository.findById(Id);
        if(commentEdit.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        commentRepository.delete(commentEdit.get());

        return ResponseEntity.ok().body("Comentário Deletado com sucesso!");
    }
}
