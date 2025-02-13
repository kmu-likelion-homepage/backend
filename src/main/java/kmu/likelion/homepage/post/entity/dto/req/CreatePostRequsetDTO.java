package kmu.likelion.homepage.post.entity.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostRequsetDTO {

    @NotBlank
    private String type;

    @NotNull
    private short semester;

    @NotBlank
    private String title;

    private String subtitle;

    private String award;

    private String competitionName;
}
