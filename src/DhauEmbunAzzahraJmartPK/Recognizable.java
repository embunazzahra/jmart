package DhauEmbunAzzahraJmartPK;

import java.lang.*;


public class Recognizable implements Comparable<Recognizable> {
   public final int id; 
   protected Recognizable(){
       this.id = 0;
   }

   @Override
   public boolean equals(Object other){
       if (other instanceof Recognizable){
           return equals(other);
       }
       else{
           return false;
       }
   }
   
   public boolean equals(Recognizable other){
       return this.id == other.id;
   }

    @Override
    public int compareTo(Recognizable other) {
        if(this.id < other.id){
            return -1;
        }
        else if (this.id > other.id){
            return 1;
        }
        else {
            return 0;
        }
    }

   public static <T> int getClosingId(Class<T> clazz){
       return 0;
   }
    public static <T> int setClosingId(Class<T> clazz, int id){
        return 0;
    }

}
