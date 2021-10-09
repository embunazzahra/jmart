package DhauEmbunAzzahraJmartPK;

import java.util.List;

public class Recognizable implements Comparable<Recognizable> {
   public final int id; 
   protected Recognizable(int id){
       this.id = id;
   }
   
   public boolean equals(Object object){
       if (object instanceof Recognizable){
           return equals(object);
       }
       else{
           return false;
       }
   }
   
   public boolean equals(Recognizable recognizable){
       return this.id == recognizable.id;
   }

    @Override
    public int compareTo(Recognizable o) {
        if(this.id < o.id){
            return -1;
        }
        else if (this.id > o.id){
            return 1;
        }
        else {
            return 0;
        }
    }

   public static<T> int getClosingId(List<T> obj){
        if( obj instanceof Recognizable){
            return 0;
        }
        else {
            return 0;
        }
   }
    public static<T> int setClosingId(List<T> obj, int id){
        if( obj instanceof Recognizable){
            return 0;
        }
        else {
            return 0;
        }
    }

}
