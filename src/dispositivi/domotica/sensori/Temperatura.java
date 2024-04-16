package dispositivi.domotica.sensori;

public class Temperatura extends Sensore {

    protected double temp;
    protected double sensibilità;
    
    public Temperatura(String sn, String marca, String modello, int carica, double temp, double sensibilità){
        super(sn, marca, modello, carica);
        this.temp=0;
        this.sensibilità=0.5;

    }
    public String evento(double temp){
        
        if(Math.abs(this.temp-temp)>sensibilità){
            return "SENSE";
        }else{
            hub.evento(this, "ON");
            this.temp=temp;
            return "OK";
        }
        
    }
}
