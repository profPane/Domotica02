package dispositivi.domotica.sensori;

public class Pulsante extends Sensore {
    //attributi
    public Pulsante(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public void premi() { this.evento("PUSH"); }

    public void evento(String evento) { 
        if (hub != null) hub.evento(this, evento); 
    }
}