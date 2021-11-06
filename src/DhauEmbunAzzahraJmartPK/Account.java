package DhauEmbunAzzahraJmartPK;


import java.util.regex.Pattern;

public class Account extends Serializable {
    public String name;
    public String email;
    public String password;
    public double balance;
    public Store store;
    public static final String REGEX_EMAIL = "^\\w+([\\.&`~-]?\\w+)*@\\w+([\\.-]?\\w+)+$";
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d][^-\\s]{8,}$";
    
    public Account(String name, String email, String password, double balance){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public boolean validate(){
        return (Pattern.matches(REGEX_EMAIL, email)) && (Pattern.matches(REGEX_PASSWORD, password));
    }
    
}