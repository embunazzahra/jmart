package DhauEmbunAzzahraJmartPK;

import java.lang.*;
import java.util.HashMap;
import java.util.Map;


public class Serializable implements Comparable<Serializable> {
   public final int id;
   private static Map<Class<?>, Integer> mapCounter = new HashMap();
   protected Serializable(){
       if(mapCounter.containsKey(this.getClass())){
           Class<?> key = this.getClass();
           mapCounter.put(key, mapCounter.get(key) + 1);
       }
       else {
           mapCounter.put(this.getClass(),0);
       }
       this.id = mapCounter.get(this.getClass());
   }

   @Override
   public boolean equals(Object other){
       if (other instanceof Serializable){
           return equals(other);
       }
       else{
           return false;
       }
   }
   
   public boolean equals(Serializable other){
       return this.id == other.id;
   }

    @Override
    public int compareTo(Serializable other) {
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
       return mapCounter.get(clazz);

   }
    public static <T> int setClosingId(Class<T> clazz, int id){
       Integer id_before = mapCounter.get(clazz);
       return mapCounter.put(clazz, id);
    }

}
