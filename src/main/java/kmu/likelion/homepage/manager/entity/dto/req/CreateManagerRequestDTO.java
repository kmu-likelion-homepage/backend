package kmu.likelion.homepage.manager.entity.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateManagerRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String major;

    @NotBlank
    private String part;
}
