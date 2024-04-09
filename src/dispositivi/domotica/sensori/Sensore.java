package dispositivi.domotica.sensori;

import dispositivi.Dispositivo;

public class Sensore extends Dispositivo {

    public Sensore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public String evento(Object stato){ 
        //segnaposto per la gestione dell'evento diversa per ogni sensore
        return "FAIL"; 
    }
}