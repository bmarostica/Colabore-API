package security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class WebSecurityConfig {

    @Test
    public void generatePass() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
