package kmu.likelion.homepage.common.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIM");

    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
