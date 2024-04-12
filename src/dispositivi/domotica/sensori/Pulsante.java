package dispositivi.domotica.sensori;

public class Pulsante extends Sensore {
    //attributi
    public Pulsante(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    // simulo una pressione sul Pulsante tale da attivarlo
    public void premi() { this.evento("PUSH"); }

    // simulo una pressione sul Pulsante tale da attivarlo
    public void evento(String evento) { 
        if (hub != null) hub.evento(this, evento); 
    }
}