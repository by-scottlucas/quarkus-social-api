package quarkussocial.persistence.dto;

import java.time.LocalDateTime;

import lombok.Data;
import quarkussocial.persistence.model.Post;

@Data
public class PostResponse {
    private String text;
    private LocalDateTime datetime;

    public static PostResponse fromEntity(Post post) {
        var response = new PostResponse();

        response.setText(post.getText());
        response.setDatetime(post.getDateTime());

        return response;
    }
}
