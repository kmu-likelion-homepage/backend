package kmu.likelion.homepage.common.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ROLE_ADMIM"),
    MANAGER("ROLE_MANAGER"),
    USER("ROLE_USER");

    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
