package internet.shop.utility;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuthority implements GrantedAuthority {

    private final String role;

    private UserAuthority(String role) {
        this.role = role;
    }


    public static Collection<GrantedAuthority> getAuth(String role) {
        List<GrantedAuthority> res = new ArrayList<>(1);
        res.add(new UserAuthority(role));
        return res;
    }


    @Override
    public String getAuthority() {
        return this.role;
    }
}
