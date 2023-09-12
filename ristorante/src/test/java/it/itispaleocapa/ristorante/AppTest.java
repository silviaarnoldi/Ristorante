package it.itispaleocapa.ristorante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
class AppTest {
    private Circolo circolo;

    @BeforeEach
    void setUp() {
        circolo = new Circolo();
    }

    @Test
    void testAggiungiSocio() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");
        assertDoesNotThrow(() -> circolo.aggiungiSocio(socio));
    }

    @Test
    void testAggiungiSocioDuplicato() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");
         try {
        circolo.aggiungiSocio(socio);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }

        assertThrows(SocioEsisteException.class, () -> circolo.aggiungiSocio(socio));
    }

    @Test
    void testEliminaSocio() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");
         try {
        circolo.aggiungiSocio(socio);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }

        assertDoesNotThrow(() -> circolo.eliminaSocio(socio));
    }

    @Test
    void testEliminaSocioInesistente() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");

        assertThrows(SocioNullException.class, () -> circolo.eliminaSocio(socio));
    }

    @Test
    void testModificaSocio() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");
         try {
        circolo.aggiungiSocio(socio);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }

        String nuovoNome = "Sofia";
        String nuovoCognome = "Bianchi";

        assertDoesNotThrow(() -> circolo.modificaSocio(socio, nuovoNome, nuovoCognome));
        assertEquals(nuovoNome, socio.nome);
        assertEquals(nuovoCognome, socio.cognome);
    }

    @Test
    void testModificaSocioInesistente() {
        Socio socio = new Socio("Luigi", "Rossi", 30, "maschio");

        String nuovoNome = "Sofia";
        String nuovoCognome = "Bianchi";

        assertThrows(SocioNullException.class, () -> circolo.modificaSocio(socio, nuovoNome, nuovoCognome));
    }

    @Test
    void testIncrementoEta() {
        Socio socio1 = new Socio("Luigi", "Rossi", 30, "maschio");
        Socio socio2 = new Socio("Sofia", "Bianchi", 25, "femmina");
        
        try {
        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }
        circolo.incrementoEta();

        assertEquals(31, socio1.eta);
        assertEquals(26, socio2.eta);
    }

    @Test
   void testEtaMedia() {
    Socio socio3 = new Socio("Luigi", "Rossi", 30, "maschio");
    Socio socio4 = new Socio("Sofia", "Bianchi", 25, "femmina");

    try {
        circolo.aggiungiSocio(socio3);
        circolo.aggiungiSocio(socio4);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }

    double mediaEta = circolo.etaMedia();

    assertEquals(27.5, mediaEta);
}

    @Test
    void testEtaMediaMaschiFemmine() {
        Socio socio1 = new Socio("Luigi", "Rossi", 30, "maschio");
        Socio socio2 = new Socio("Sofia", "Bianchi", 25, "femmina");
        Socio socio3 = new Socio("Silvia", "Arnoldi", 35, "femmina");
        Socio socio4 = new Socio("Gianfiliberto", "Patata", 28, "maschio");
        
         try {
        circolo.aggiungiSocio(socio1);
        circolo.aggiungiSocio(socio2);
        circolo.aggiungiSocio(socio3);
        circolo.aggiungiSocio(socio4);
    } catch (SocioEsisteException e) {
        fail("Eccezione SocioEsisteException non prevista");
    }


        assertDoesNotThrow(() -> circolo.etaMediaMaschiFemmine());
    }

    @Test
    void testPercentuale() {
        Socio socio1 = new Socio("Luigi", "Rossi", 30, "maschio");
        Socio socio2 = new Socio("Sofia", "Bianchi", 25, "femmina");
        Socio socio3 = new Socio("Silvia", "Arnoldi", 35, "femmina");
        Socio socio4 = new Socio("Gianfiliberto", "Patata", 28, "maschio");
            try {
            circolo.aggiungiSocio(socio1);
            circolo.aggiungiSocio(socio2);
                circolo.aggiungiSocio(socio3);
                circolo.aggiungiSocio(socio4);
        } catch (SocioEsisteException e) {
            fail("Eccezione SocioEsisteException non prevista");
        }

        assertDoesNotThrow(() -> circolo.percentuale());
    }
  
}