package DhauEmbunAzzahraJmartPK;


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
        public static final Duration INSTANT = new Duration(1<<0);
        public static final Duration SAME_DAY = new Duration(1<<1);
        public static final Duration NEXT_DAY = new Duration(1<<2);
        public static final Duration REGULER = new Duration(1<<3);
        public static final Duration KARGO = new Duration(1<<4);
        private final int bit;
    
        private Duration(int bit){
            this.bit = bit;
        }
    }//end duration
    
    public static class MultiDuration{
        public int bit;
        public MultiDuration(Duration... args){
            int save = 0;
            for (Duration x:args){
                save = (save | x.bit);
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