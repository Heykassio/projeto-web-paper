package paper.projetowebpaper.viewmodels.comment;

import java.time.LocalDateTime;

public class CommentViewModel {
    public Integer id;
    public String content;
    public LocalDateTime createdAt;
    public Integer authorId;
    public Integer recipeId;

    public CommentViewModel() {
    }

    public CommentViewModel(Integer id, String content, LocalDateTime createdAt, Integer authorId, Integer recipeId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.authorId = authorId;
        this.recipeId = recipeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
}
