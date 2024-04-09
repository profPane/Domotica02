package dispositivi.domotica.altro;

import java.util.HashMap;

import dispositivi.Dispositivo;
import dispositivi.domotica.attuatori.*;
import dispositivi.domotica.sensori.Sensore;

public class Hub extends Dispositivo {

    //Chiave (idDispositivo, Dispositivo)
    private HashMap<Integer, Dispositivo> dispositivi; //dispositivi associati
    //Chiave (idDispositivo, Attuatore)
    private HashMap<Integer, Attuatore> collegamenti; //dispositivi collegati
    //private ArrayList<Object> conosciuti;
    private int progressivoID; //id contiene l'ultimo ID assegnato

    public Hub(String sn, String marca, String modello) {
        super(sn, marca, modello, -1);
        this.dispositivi = new HashMap<>();
        this.collegamenti = new HashMap<>();
        //this.conosciuti = new ArrayList<>();
        progressivoID=-1;
    }

    //associa un Dispositivo all'HUB
    public int associa(Dispositivo dispositivo) {
        dispositivi.put((++progressivoID), dispositivo);
        //conosciuti.add(dispositivo.getClass());
        return progressivoID;
    }

    //collega un sensore ad un attuatore tramite ID
    public String collega(int idSensore, int idAttuatore){
        Dispositivo dispositivo = dispositivi.get(idAttuatore);
        if (dispositivo instanceof Attuatore){ //è un attuatore
                collegamenti.put(idSensore, (Attuatore) dispositivo);
                return "OK";
        } 
        return "FAIL";
    }

    //collega un sensore ad un attuatore tramite REFERENCE
    public String collega(Dispositivo sensore, Dispositivo attuatore){
        Object arrayDisp[] = dispositivi.values().toArray();
        if ((sensore instanceof Sensore) && (attuatore instanceof Attuatore)){
                collegamenti.put(((Sensore) sensore).getID(), (Attuatore) attuatore);
                return "OK";
        } 
        return "FAIL";
    }

    //comunica un evento all'HUB
    public String evento(Sensore sensore, String comando) {
        //cerco l'eventuale Attuatore collegato a questo Sensore tramite ID nella lista dei collementi
        Attuatore attuatore = collegamenti.get(sensore.getID());

        if (attuatore==null) { 
            System.err.println("HUBLOG: Nessun attuatore collegato a "+sensore );
        }
        else { //c'è un attuatore collegato
            String response="FAIL"; //risposta (al Sensore) nel caso peggiore
            if (comando.equals("PUSH")) { //se è un pulsate uso cambiaStato()
                response = attuatore.cambiaStato(); // risposta dell'attuatore
            } else { //prende il comando ricevuto e lo inoltra all'attuatore
                response = attuatore.evento(comando); // risposta dell'attuatore
            }
            System.err.println("HUBLOG: Evento su "+sensore+" Comando: "+comando );
            System.err.println("HUBLOG: Risultato evento su "+response);
            return response; //risposta (al Sensore)
        }
        return "FAIL";
    }

    //crea una lista in formato String dei dispositivi connessi
    public String listaDispositivi(Class tipo) {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositivi.values()) {
           if (tipo.isInstance(dispositivo)) 
                sb.append(dispositivo.toString()).append("\n");
        }
        return sb.toString();
    }
    
    //Realizza una lista in formato String dei collegamenti tra Sensori e Attuatori
    public String collegamenti(){
        StringBuilder collegati = new StringBuilder();
        collegamenti.forEach((id,att)->{
            collegati.append("Il sensore: \n");
            collegati.append(dispositivi.get(id).toString());
            collegati.append("\n agisce su: \n");
            collegati.append(att.toString());
            collegati.append("\n");
        });
        return collegati.toString();
    }

    @Override
    public String toString() {
        return "Informazioni sull'HUB: " +this.getSn()+ "\nDispositivi associati:\n" + listaDispositivi(Dispositivo.class);
    }
}
