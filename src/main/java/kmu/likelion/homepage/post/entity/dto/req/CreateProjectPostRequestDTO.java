package kmu.likelion.homepage.post.entity.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProjectPostRequestDTO extends CreatePostRequsetDTO{

    @NotBlank
    private String award;

    @NotBlank
    private String competitionName;
}
