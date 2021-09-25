package DhauEmbunAzzahraJmartPK;


/**
 * Write a description of interface FileParser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface FileParser {
    boolean read(String content);
    Object write();
    public static Object newInstance(String content){
        return null;
    }
}
