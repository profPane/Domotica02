package dispositivi.domotica.sensori;

public class Illuminamento extends Sensore{
    
    int illum;
    int sens;
    public String stati[] = {"OFF", "ON"}; //stati possibili
    
    public Illuminamento(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
        illum=0;
        sens=5;
        stato=0;
    }
    public String evento(int illum){
        
        if (Math.abs(this.illum-illum)<sens) {
            return "SENS";
        } else {
            this.illum=illum;
            if (sens<10) {
                if (this.stato==1){
                    this.stato=0;
                    hub.evento(this, "OFF");
                }
            } else if (sens>500) {
                if (this.stato==0){
                    this.stato=1;
                    hub.evento(this, "ON");
                }  
            }
            return "OK";          
        }
    }
}