package bookstore;
import java.util.HashMap;
/**
 *
 * @author Jerome Magpantay
 */
public class Login {

    private static Login instance;
    private Customer currentCustomer = null;
    private Owner currentOwner = null;

    private Login() {

    }

    public static Login getInstance(){
        if (instance == null){
            instance = new Login();
        }
            return instance;
    }

    public boolean adminLogin(String usernameAttempt, String passwordAttempt){
        if (currentCustomer != null || currentOwner != null) {
            return false;
        }

        Owner ownerAccount = Owner.getInstance();
        boolean matchingUsername = ownerAccount.getName().equals(usernameAttempt);
        boolean matchingPassword = ownerAccount.getPassword().equals(passwordAttempt);
        if (matchingUsername && matchingPassword) {
            currentOwner = ownerAccount;
            return true;
        } else {
            return false;
        }
    }

    public boolean customerLogin(String usernameAttempt, String passwordAttempt){
        if (currentCustomer != null || currentOwner != null) {
            return false;
        }

        Customer foundCustomer = DataStore.getInstance().getCustomer(usernameAttempt);
        if (foundCustomer == null) {
            return false;
        }

        if(passwordAttempt.equals(foundCustomer.getPassword())) {
            currentCustomer = foundCustomer;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        currentCustomer = null;
        currentOwner = null;
        DataStore.getInstance().saveData();
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Owner getCurrentOwner() {
        return currentOwner;
    }
}
