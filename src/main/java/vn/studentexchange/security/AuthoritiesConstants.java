package vn.studentexchange.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String STAFF = "ROLE_STAFF";

    public static final String MANAGER = "ROLE_MANAGER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String LOGISTICS = "ROLE_LOGISTICS";

    private AuthoritiesConstants() {
    }
}
