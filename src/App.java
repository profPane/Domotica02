import dispositivi.domotica.altro.Hub;
import dispositivi.domotica.attuatori.Lamp;
import dispositivi.domotica.sensori.Presenza;
import dispositivi.domotica.sensori.Sensore;

public class App {

    public static void main(String[] args) {
        //creo l'HUB
        Hub hub = new Hub("HUB01", "Domotica", "1.0");

        //esempio di creazione di un Sensore e associazione all'hub
        Presenza sensPres1 = new Presenza("PR1234", "Philips" , "HUE PRESENCE", 100);
        sensPres1.associa(hub);

        //esempio di creazione di un Attuatore e associazione all'hub
        Lamp lamp1 = new Lamp("LAMP001", "BOSCH", "Addumina", -1, 1100);
        lamp1.associa(hub);

        //esempio di associazione Sensore => Attuatore
        //se succede una presenza si accende la lamp1 per "avvertire" dell'accaduto
        hub.collega(sensPres1.getID(), lamp1.getID());

        System.err.println("\nInfo sullo HUB:");
        // Stampa informazioni sui dispositivi
        System.out.println(hub);

        System.err.println("\nLista Sensori:");
        System.out.println(hub.listaDispositivi(Sensore.class));
    }
}