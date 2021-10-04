package DhauEmbunAzzahraJmartPK;


import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser{
    public String name;
    public String email;
    public String password;
    public static final String REGEX_EMAIL = "^(?!\\.)[a-zA-Z0-9&_*~.]+@(?!\\-|\\.)[a-zA-Z0-9.-]+.*(?<!\\.)$";
    public static final String REGEX_PASSWORD ="((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{8,})";
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean validate(){
        return (Pattern.matches(REGEX_EMAIL, email)) && (Pattern.matches(REGEX_PASSWORD, password));
    }

    @Override
    public boolean read(String content){
        return true;
    }

    @Override
    public Object write() {
        return null;
    }
    @Override
    public String toString(){
        return "name: "+this.name+"\nemail: "+this.email+"\npassword: "+this.password;
    }
    
}