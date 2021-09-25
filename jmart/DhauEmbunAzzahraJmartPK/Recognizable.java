package DhauEmbunAzzahraJmartPK;

public class Recognizable {
   public final int id; 
   protected Recognizable(int id){
       this.id = id;
   }
   
   public boolean equals(Object object){
       return true;
   }
   
   public boolean equals(Recognizable recognizable){
       if (this.id == recognizable.id){
           return true;
       }
       else{
           return false;
       }
   }
}
