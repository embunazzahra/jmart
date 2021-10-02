package DhauEmbunAzzahraJmartPK;
import java.util.Calendar;
import java.text.*;
import java.util.Date;

public class Shipment implements FileParser{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;

    @Override
    public boolean read(String content) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object write() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public static class Duration{
        public static final Duration INSTANT = new Duration((byte)(1<<0));
        public static final Duration SAME_DAY = new Duration((byte)(1<<1));
        public static final Duration NEXT_DAY = new Duration((byte)(1<<2));
        public static final Duration REGULER = new Duration((byte)(1<<3));
        public static final Duration KARGO = new Duration((byte)(1<<4));
        public final byte bit;
        SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("ddd MMMM dd yyyy");
        Calendar cal = Calendar.getInstance();
        private Duration(byte bit){
            this.bit = bit;
        }
        public String getEstimatedArrival(Date reference){
            if(bit==Duration.INSTANT.bit || bit==Duration.SAME_DAY.bit){
                return ESTIMATION_FORMAT.format(reference);
            }
            else if(bit==Duration.NEXT_DAY.bit){
                return ESTIMATION_FORMAT.format(reference);
            }
            else{
                return ESTIMATION_FORMAT.format(reference);
            }
         }
    }//end duration
    
    public static class MultiDuration{
        public byte bit;
        public MultiDuration(Duration... args){
            byte save = 0;
            for (Duration x:args){
                save = (byte) (save | x.bit);
            }
            this.bit = save;
        }

        public boolean isDuration(Duration reference){
            if((this.bit & reference.bit) != 0){
                return true;
            }
            else{
                return false;
            }
        }
    }//end multiduration
    
    public Shipment(String address, int shipmentCost, Duration duration, String receipt){
        this.address = address;
        this.shipmentCost=shipmentCost;
        this.duration = duration;
        this.receipt=receipt;
    }
}