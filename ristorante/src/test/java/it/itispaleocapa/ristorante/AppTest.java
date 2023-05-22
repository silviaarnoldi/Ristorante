package it.itispaleocapa.ristorante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class AppTest {
    Ristorante ristorante;
    @BeforeEach
    public void setUp() {
        ristorante = new Ristorante();
        Cliente silvia = new Cliente("Silvia", "Arnoldi");
        Cliente charles = new Cliente("Charles", "Leclerc");
        Cliente alessandro = new Cliente("Alessandro", "Rossi");
        Cliente luigi = new Cliente("Luigi", "Orsini");
        ristorante.aggiungiCliente(silvia);
        ristorante.aggiungiCliente(charles);
        ristorante.aggiungiCliente(alessandro);
        ristorante.aggiungiCliente(luigi);
    }
    @Test
    public void testAggiungiCliente() {
        assertEquals(4, ristorante.elencoClientiOrdinatiPerNumeroPrenotazioni().size());
    }

    @Test
    public void testAggiungiPrenotazione() {
        Cliente cliente = new Cliente("Silvia", "Arnoldi");
        Date dataInserimento = new Date();
        Date dataPrenotazione = new Date();
        int numeroCoperti = 3;
        Prenotazione prenotazione = new Prenotazione(cliente, dataInserimento, dataPrenotazione, numeroCoperti);
        ristorante.aggiungiPrenotazione(prenotazione);
        assertEquals(1, ristorante.ricercaPrenotazioniPerCliente(cliente).size());
        assertEquals(1, ristorante.ricercaPrenotazioniPerData(dataPrenotazione).size());
        assertEquals(numeroCoperti, ristorante.numeroCopertiPerData(dataPrenotazione));
        assertEquals(numeroCoperti, ristorante.numeroCopertiInRangeDiDate(dataPrenotazione, dataPrenotazione));
    }

    @Test
    public void testModificaCliente() {
        Cliente cliente = new Cliente("Silvia", "Arnoldi");
        String nuovoNome = "Silvia Maria";
        String nuovoCognome = "Rossi";
        try {
            ristorante.modificaCliente(cliente, nuovoNome, nuovoCognome);
            LinkedList<Cliente> clienti = ristorante.elencoClientiOrdinatiPerNumeroPrenotazioni();
            assertEquals(nuovoNome, clienti.get(0).nome);
            assertEquals(nuovoCognome, clienti.get(0).cognome);
        } catch (ClienteNullException e) {
            fail("ClienteNullException ");
        }
    }



    @Test
public void testModificaPrenotazione() {
    Cliente cliente = new Cliente("Silvia", "Arnoldi");
    Date dataInserimento = new Date();
    Date dataPrenotazione = new Date();
    int numeroCoperti = 3;
    Prenotazione prenotazione = new Prenotazione(cliente, dataInserimento, dataPrenotazione, numeroCoperti);
    ristorante.aggiungiPrenotazione(prenotazione);
    int nuovoNumeroCoperti = 5;

    try {
        ristorante.modificaPrenotazione(prenotazione, nuovoNumeroCoperti);
        assertEquals(nuovoNumeroCoperti, ristorante.numeroCopertiPerData(dataPrenotazione));
        assertEquals(nuovoNumeroCoperti, ristorante.numeroCopertiInRangeDiDate(dataPrenotazione, dataPrenotazione));
    } catch (PrenotazioneNullException e) {
        fail(" PrenotazioneNullException");
    }
}


    @Test
    public void testEliminaCliente() throws ClienteNullException {
        Cliente cliente = new Cliente("Silvia", "Arnoldi");
        ristorante.eliminaCliente(cliente);
        assertEquals(3, ristorante.elencoClientiOrdinatiPerNumeroPrenotazioni().size());
    }


    @Test
    public void testEliminaPrenotazione() throws PrenotazioneNullException {
        Cliente cliente = new Cliente("Silvia", "Arnoldi");
        Date dataInserimento = new Date();
        Date dataPrenotazione = new Date();
        int numeroCoperti = 3;
        Prenotazione prenotazione = new Prenotazione(cliente, dataInserimento, dataPrenotazione, numeroCoperti);
        ristorante.aggiungiPrenotazione(prenotazione);
        ristorante.eliminaPrenotazione(prenotazione);
        assertEquals(0, ristorante.ricercaPrenotazioniPerCliente(cliente).size());
        assertEquals(0, ristorante.ricercaPrenotazioniPerData(dataPrenotazione).size());
        assertEquals(0, ristorante.numeroCopertiPerData(dataPrenotazione));
        assertEquals(0, ristorante.numeroCopertiInRangeDiDate(dataPrenotazione, dataPrenotazione));
    }

    

    @Test
    public void testRicercaPrenotazioniPerCliente() {
        Cliente cliente = new Cliente("Silvia", "Arnoldi");
        assertEquals(0, ristorante.ricercaPrenotazioniPerCliente(cliente).size());
    }

    @Test
    public void testRicercaPrenotazioniPerData() {
        Date data = new Date();
        assertEquals(0, ristorante.ricercaPrenotazioniPerData(data).size());
    }

    @Test
    public void testNumeroCopertiPerData() {
        Date data = new Date();
        assertEquals(0, ristorante.numeroCopertiPerData(data));
    }

    @Test
    public void testNumeroCopertiInRangeDiDate() {
        Date dataInizio = new Date();
        Date dataFine = new Date();
        assertEquals(0, ristorante.numeroCopertiInRangeDiDate(dataInizio, dataFine));
    }

    @Test
    public void testGiornoConMaggiorNumeroCoperti() {
        assertEquals(null, ristorante.giornoConMaggiorNumeroCoperti());
    }

    @Test
    public void testClienteConMaggiorNumeroCopertiPrenotati() {
        Date dataInserimento= new Date();
        Date dataPrenotazione = new Date();
        Cliente cl=new Cliente("Alessandro", "Rossi");
        Prenotazione p= new Prenotazione(cl, dataInserimento, dataPrenotazione, 6);
        ristorante.aggiungiPrenotazione(p);
        Cliente cliente = ristorante.clienteConMaggiorNumeroCopertiPrenotati();
        assertEquals("Alessandro", cliente.nome);
    }

    @Test
    public void testElencoClientiOrdinatiPerNumeroPrenotazioni() {
        LinkedList<Cliente> clienti = ristorante.elencoClientiOrdinatiPerNumeroPrenotazioni();
        assertEquals("Silvia", clienti.get(0).nome);
        assertEquals("Charles", clienti.get(1).nome);
        assertEquals("Alessandro", clienti.get(2).nome);
        assertEquals("Luigi", clienti.get(3).nome);
    }

   
}
