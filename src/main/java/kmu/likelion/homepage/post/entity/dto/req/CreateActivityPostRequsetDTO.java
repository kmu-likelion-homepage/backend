package kmu.likelion.homepage.post.entity.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateActivityPostRequsetDTO extends CreatePostRequsetDTO{

    @NotBlank
    private String subtitle;
}
