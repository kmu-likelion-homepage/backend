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

    @NotNull(message = "게시물 id가 비어있습니다.")
    private Long post_id;

    @NotBlank(message = "게시물 유형이 비어있습니다.")
    private PostType type;

    @NotNull(message = "기수가 비어있습니다.")
    private short semester;

    @NotBlank(message = "제목이 비어있습니다.")
    private String title;

    private String award;
    private String competitionName;
    private String subtitle;
}
