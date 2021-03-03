package net.marcos_fernandez.triviagame.model;

import lombok.Value;

import static java.util.Collections.singleton;
import static net.marcos_fernandez.triviagame.model.User.ADMIN_AUTHORITY;
import static net.marcos_fernandez.triviagame.model.User.USER_AUTHORITY;

@Value
public class UserBasicInformation {

    String id;
    String username;
    String name;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;
    boolean admin;

    public static UserBasicInformation empty() {
        return new UserBasicInformation(null, "", "", true, true, true, true, false);
    }

    public User applyChanges(final User existingUser) {
        return existingUser.toBuilder()
                .id(this.id)
                .username(this.username)
                .name(this.name)
                .accountNonExpired(this.accountNonExpired)
                .accountNonLocked(this.accountNonLocked)
                .credentialsNonExpired(this.credentialsNonExpired)
                .enabled(this.enabled)
                .authorities(this.admin ? singleton(ADMIN_AUTHORITY) : singleton(USER_AUTHORITY))
                .build();
    }
}
