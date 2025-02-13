package kmu.likelion.homepage.post.entity.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllRequestDTO {

    @NotNull(message = "기수가 비어있습니다.")
    private short semester;

    @NotBlank
    private String type;

}
