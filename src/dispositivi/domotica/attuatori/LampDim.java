package dispositivi.domotica.attuatori;

import java.util.Arrays;

//la lampada dimmerabile può essere OFF = 0 altrimenti ON con 10 possibili livelli di luminosità
public class LampDim extends Lamp {
    //overriding dell'attributo stati, che contiene gli stati possibili
    private String stati[] = {"OFF","10","20","30","40","50","60","70","80","90","100"}; //stati possibili

    public LampDim(String sn, String marca, String modello, int caricaMassima, int maxLux  ) {
        super(sn, marca, modello, caricaMassima, maxLux);
    }

    //oltre agli stati, gestiti da super, potrei aver passato una luminosita espressa in lux o una %, es "105lux", "91%"
    public String comando(String comando){
        //to do: controllo se stato contiene il tag lux o % altrimenti passo il comando a super
        String ris = super.comando(comando);
        if (!ris.equals("FAIL")) return ris;
        else {
            int posStato = Arrays.binarySearch(this.stati, comando); //lo cerco tra i possibili
            if (posStato!=-1) { //se c'è lo imposto
                this.stato=posStato;
                System.err.println("LAMPLOG: "+this.id+" ==> "+this.stato());
                return this.stati[this.stato];
            }   
        }
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    //oltre agli stati, gestiti da super, potrei aver passato una percentuale espressa come intero tra 0 e 100
    public String comando(int stato){ 
        //to do: controllo se stato contiene un numero intero tra 0 e 100 e chiamo super.setStato()
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    //oltre agli stati, gestiti da super, potrei aver passato una percentuale espressa come reale tra 0 e 1 (100%)
    public String comando(double stato){ 
        //to do: controllo se stato contiene un numero reale tra 0.0 e 1.0, lo proporziono a 100 e lo cerco sta gli stati possibili
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    public int getdimmer() {
        return stato;
    }

    //se cambio lo stato di una dimmerabile agisco di +10 sulla luminosità
    public String cambiaStato() {
        if (stato==100) stato=0;
        else stato = (stato + 10);
        return this.stato();
    }

    public String evento(String comando) { //simula l'uso del Sensore
        /* 
        int posStato = Arrays.binarySearch(stati, comando); //lo cerco tra i possibili
        if (posStato!=-1) { //se c'è lo imposto
            this.stato=posStato;
            if (hub != null) return hub.evento(this, stati[this.stato]);;
        }   
        */
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        super.comando(comando);
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    protected String stato(){
        return (this.stato>0)?"ON":"OFF";
    }

    protected String lux(){
        //todo: restituire gli attuali lux emessi rapportati a maxLux
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    @Override
    public String toString() {
        return super.toString()+ " - Luminosità attuale: " + this.stati[stato];
    }
}