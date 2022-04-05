package bookstore;

public class Owner {
    
    private static Owner instance; 
    private String name; 
    private String password; 
    
    private Owner(String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public static Owner getInstance(){
        if (instance == null) {
            instance = new Owner("admin", "admin");
        }
        
        return instance; 
    }
        
    public String getName(){ 
        return name;
    }    
    
    public String getPassword(){ 
        return password;
    }
    
}
