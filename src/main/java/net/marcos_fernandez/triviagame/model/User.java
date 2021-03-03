package net.marcos_fernandez.triviagame.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Document
@Builder(toBuilder = true)
public class User implements UserDetails {

    public static final GrantedAuthority USER_AUTHORITY = new SimpleGrantedAuthority("user");
    public static final GrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("admin");

    private static final long serialVersionUID = 5720184500563242339L;

    @Id
    private String id;
    private String password;
    @Indexed(unique = true)
    private String username;
    private String name;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public static User empty() {
        return new User(null, "", "", "", true, true, true, true, List.of(USER_AUTHORITY));
    }
}
