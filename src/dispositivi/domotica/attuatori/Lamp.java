package dispositivi.domotica.attuatori;

import java.util.Arrays;

public class Lamp extends Attuatore {

    //attributi
    protected String stati[] = {"OFF", "ON"}; //stati possibili
    private int lux; //luminosita massima espressa in lux

    public Lamp(String sn, String marca, String modello, int carica, int lux) {
        super(sn, marca, modello,  carica);
        this.lux = lux;
        this.stato = 0;
    }
    
    public String cambiaStato() { //passo al prossimo stato
        return this.comando("PUSH");
    }

    public String comando(String comando){ //riceve il comando se è una Stringa 
        if (comando.equals("PUSH")) {
            stato = (++stato)%stati.length;
            System.err.println("LAMPLOG: "+this.id+" ==> "+this.stato());
            return this.stati[this.stato];
        }
        int posStato = Arrays.binarySearch(this.stati, comando); //lo cerco tra i possibili
        if (posStato!=-1) { //se c'è lo imposto
            this.stato=posStato;
            System.err.println("LAMPLOG: "+this.id+" ==> "+this.stato());
            return this.stati[this.stato];
        }   
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    protected String stato(){
        return this.stati[this.stato];
    }

    @Override
    public String toString() {
        return super.toString()+" - Stato: " + this.stato();
    }
}