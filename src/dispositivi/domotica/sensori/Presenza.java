package dispositivi.domotica.sensori;

public class Presenza extends Sensore {
    //attributi vari
    String stati[]={"ON","OFF"};

    public Presenza(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    //evento di simulazione aggiuntivo, non espressamento richiesto
    public void presenza() { this.evento("PRESENZA"); }

    //per simulare una presenza l'evento viene impostato a "PRESENZA"
    public String evento(String evento) {
        String response="FAIL";
        if (this.hub!=null) { //l'hub vuole "ON"/"OFF" non "PRESENZA"
            this.stato=1; //Il sensore di Presenza resta "ON"
            //per il tempo necessario a comunicare lo stato allo HUB
            response = this.hub.evento(this,this.stati[this.stato]); 
            this.stato=0; // immediatamente dopo torna OFF.
           
        }
        return response; //restituisce la risposta di HUB al chiamante (Sensore)
    }
}