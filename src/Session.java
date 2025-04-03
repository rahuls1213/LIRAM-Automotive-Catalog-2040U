/**
 * Session manages the state of the currently logged-in user.
 *
 * This class acts as a simple, static context holder for tracking
 * which user is actively authenticated across the application.
 *
 * It is referenced by UI classes such as {@link CarViewer} and {@link LoginScreen}
 * to determine access to user-specific features like favorites and login/logout behavior.
 *
 */
public class Session {

    /**
     * The currently logged-in user.
     * If null, no user is logged in and the session is considered anonymous/guest.
     */
    public static User currentUser = null;
}