package it.itispaleocapa.ristorante;
import java.util.*;
import java.util.Date;
public class Ristorante
{
    HashMap<Cliente,LinkedList<Prenotazione>>c;
    HashMap<Date,LinkedList<Prenotazione>>d;
    public Ristorante()
    {
        c=new HashMap<Cliente,LinkedList<Prenotazione>>();
        d=new HashMap<Date,LinkedList<Prenotazione>>();
    }
      public void aggiungiCliente(Cliente cliente) {
        c.put(cliente, new LinkedList<Prenotazione>());
    }

    public void aggiungiPrenotazione(Prenotazione p) {
        Cliente cliente = p.nomePrenotazione;
        if (!c.containsKey(cliente)) {
            aggiungiCliente(cliente);
        }
        c.get(cliente).add(p);
        if (!d.containsKey(p.gioPrenotazione)) {
            d.put(p.gioPrenotazione, new LinkedList<>());
        }
        d.get(p.gioPrenotazione).add(p);
    }
    public void modificaCliente(Cliente cliente, String nuovoNome, String nuovoCognome)throws ClienteNullException {
        if (!c.containsKey(cliente)) {
            throw new ClienteNullException();
        }

        Cliente nuovoCliente = new Cliente(nuovoNome, nuovoCognome);
        LinkedList<Prenotazione> prenotazioni = c.get(cliente);

        for (Prenotazione prenotazione : prenotazioni) {
            prenotazione.nomePrenotazione = nuovoCliente;
        }

        c.remove(cliente);
        c.put(nuovoCliente, prenotazioni);

        for (LinkedList<Prenotazione> listaPrenotazioni : d.values()) {
            for (Prenotazione prenotazione : listaPrenotazioni) {
                if (prenotazione.nomePrenotazione.equals(cliente)) {
                    prenotazione.nomePrenotazione = nuovoCliente;
                }
            }
        }
    }

    public void modificaPrenotazione(Prenotazione prenotazione, int nuovoNumeroCoperti) throws PrenotazioneNullException{
        Cliente cliente = prenotazione.nomePrenotazione;
        LinkedList<Prenotazione> prenotazioniCliente = c.getOrDefault(cliente, new LinkedList<>());

        if (!prenotazioniCliente.contains(prenotazione)) {
             throw new PrenotazioneNullException();
        }

        prenotazione.numeroCoperti = nuovoNumeroCoperti;

        for (Prenotazione p : prenotazioniCliente) {
            if (p.equals(prenotazione)) {
                p.numeroCoperti = nuovoNumeroCoperti;
                break;
            }
        }

        c.put(cliente, prenotazioniCliente);

        for (LinkedList<Prenotazione> listaPrenotazioni : d.values()) {
            for (Prenotazione p : listaPrenotazioni) {
                if (p.equals(prenotazione)) {
                    p.numeroCoperti = nuovoNumeroCoperti;
                    break;
                }
            }
        }
    }
    public void eliminaCliente(Cliente cliente) throws ClienteNullException {
        if (!c.containsKey(cliente)) {
            throw new ClienteNullException();
        }
    
        LinkedList<Prenotazione> prenotazioniCliente = c.remove(cliente);
    
        for (LinkedList<Prenotazione> listaPrenotazioni : d.values()) {
            listaPrenotazioni.removeIf(prenotazione -> prenotazione.nomePrenotazione.equals(cliente));
        }
    }

    public void eliminaPrenotazione(Prenotazione prenotazione)throws PrenotazioneNullException {
        Cliente cliente = prenotazione.nomePrenotazione;
        LinkedList<Prenotazione> prenotazioniCliente = c.getOrDefault(cliente, new LinkedList<>());

        if (!prenotazioniCliente.contains(prenotazione)) {
            throw new PrenotazioneNullException();
        }

        prenotazioniCliente.remove(prenotazione);
        c.put(cliente, prenotazioniCliente);

        for (LinkedList<Prenotazione> listaPrenotazioni : d.values()) {
            listaPrenotazioni.remove(prenotazione);
        }
    }
    public LinkedList<Prenotazione> ricercaPrenotazioniPerCliente(Cliente cliente) {
        return c.getOrDefault(cliente, new LinkedList<>());
    }

    public LinkedList<Prenotazione> ricercaPrenotazioniPerData(Date data) {
        return d.getOrDefault(data, new LinkedList<>());
    }

    public int numeroCopertiPerData(Date data) {
        return d.getOrDefault(data, new LinkedList<>()).stream().mapToInt(prenotazione -> prenotazione.numeroCoperti).sum();
    }
    
    private boolean isDateInRange(Date date, Date startDate, Date endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }
    
    public int numeroCopertiInRangeDiDate(Date dataInizio, Date dataFine) {
        return d.values().stream().flatMap(Collection::stream).filter(prenotazione -> isDateInRange(prenotazione.gioPrenotazione, dataInizio, dataFine)).mapToInt(prenotazione -> prenotazione.numeroCoperti).sum();
    }
    
    public Date giornoConMaggiorNumeroCoperti() {
        return d.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().stream().mapToInt(prenotazione -> prenotazione.numeroCoperti).sum())).map(Map.Entry::getKey).orElse(null);
    }
    
    public Cliente clienteConMaggiorNumeroCopertiPrenotati() {
        return c.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().stream().mapToInt(prenotazione -> prenotazione.numeroCoperti).sum())).map(Map.Entry::getKey).orElse(null);
    }

    public LinkedList<Cliente> elencoClientiOrdinatiPerNumeroPrenotazioni() {
        return c.entrySet().stream().sorted(Comparator.comparingInt(entry -> entry.getValue().size())).map(Map.Entry::getKey).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
    }
}