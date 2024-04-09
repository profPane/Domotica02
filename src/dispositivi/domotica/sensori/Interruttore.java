package dispositivi.domotica.sensori;

import java.util.Arrays;

public class Interruttore extends Sensore {

    //attributi
    public String stati[] = {"OFF", "ON"}; //stati possibili
    
    public Interruttore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
        this.stato = 0;
    }

    public String stato(){ 
        return stati[stato]; 
    }

    public String evento(String comando) { //simula l'uso del Sensore
        int posStato = Arrays.binarySearch(stati, comando); //lo cerco tra i possibili
        if (posStato!=-1) { //se c'è lo imposto
            this.stato=posStato;
            if (hub != null) return hub.evento(this, stati[this.stato]);;
        }   
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    @Override
    public String toString() {
        return super.toString()+" - " + this.stati[stato];
    }
}