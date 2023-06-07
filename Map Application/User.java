
import java.io.File;

/**
 *
 * Represents a user with a username, password, and admin status. Provides
 * methods to get and set the username, password, and admin status. Also
 * includes a main method to check for the existence of a file.
 * @author Moksh, Simon
 */
public class User {

    private String user;
    private String pass;
    private Boolean isAdmin;

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUserName() {
        return user;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return pass;
    }

    /**
     * Returns whether the user is an admin or not.
     *
     * @return true if the user is an admin, false otherwise
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the username of the user.
     *
     * @param name the new username to set
     */
    public void setUserName(String name) {
        this.user = name;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.pass = password;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param NewAdmin the new admin status to set
     */
    public void setIsAdmin(Boolean NewAdmin) {
        this.isAdmin = NewAdmin;
    }

    /**
     * Constructs a new User object with the given username, password, and admin
     * status.
     *
     * @param user the username of the user
     * @param pass the password of the user
     * @param isAdmin true if the user is an admin, false otherwise
     */
    public User(String user, String pass, Boolean isAdmin) {
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    /**
     * Checks for the existence of a file and outputs information about the
     * file.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file = new File("Users.txt");
        User admin = new User("admin", "12345", true);
        if (file.exists()) {
            System.out.println("File name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Writeable: " + file.canWrite());
            System.out.println("Readable " + file.canRead());
            System.out.println("File size in bytes " + file.length());
        } else {
            System.out.println("File not found");
        }

    }
}
