package kmu.likelion.homepage.post.entity.dto.res;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kmu.likelion.homepage.post.entity.PostType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDTO {
    @NotNull
    private Long post_id;

    @NotBlank
    private PostType type;

    @NotNull
    private short semester;

    @NotBlank
    private String title;

    private String award;
    private String competitionName;
    private String subtitle;
}
