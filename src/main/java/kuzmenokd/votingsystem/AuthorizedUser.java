package kuzmenokd.votingsystem;

import kuzmenokd.votingsystem.model.Role;
import kuzmenokd.votingsystem.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        this.restaurantId = user.getRestaurant() != null ? user.getRestaurant().getId() : null;
        this.id = user.getId();
        this.roles = user.getRoles();
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    private final Set<Role> roles;

    public static Set<Role> getRoles() {
        return get().roles;
    }

    private Integer restaurantId;

    public static Integer getRestaurantId() {
        return get().restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    private final Integer id;

    public static Integer getId() {
        return get().id;
    }

}