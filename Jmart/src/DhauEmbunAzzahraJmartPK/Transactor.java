package DhauEmbunAzzahraJmartPK;


/**
 * Write a description of interface Transactor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Transactor
{
    boolean validate();
    Invoice perform();
}
