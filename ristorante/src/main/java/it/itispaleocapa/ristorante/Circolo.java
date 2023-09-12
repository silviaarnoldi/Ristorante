package it.itispaleocapa.ristorante;
import java.util.*;

public class Circolo {
    LinkedList<Socio> soci;
    public Circolo() {
        soci = new LinkedList<Socio>();
    }
	 
	public void aggiungiSocio(Socio s)throws SocioEsisteException{
	if(soci.contains(s)){
		throw new SocioEsisteException();
	}
	soci.add(s);
	}
	public void eliminaSocio(Socio s)throws SocioNullException{
	if(!soci.contains(s)){
		throw new SocioNullException();
	}
	soci.remove(s);
	}
	public void modificaSocio(Socio s, String nuovoN, String nuovoC)throws SocioNullException{
	if(!soci.contains(s)){
		throw new SocioNullException();
	}
	for (Socio socio : soci) {
		if (socio.nome.equals(s.nome)) {
			socio.nome = nuovoN;
			socio.cognome = nuovoC;
			return; 
		}
	}

	}
	public void incrementoEta(){
	 for (Socio socio : soci) {
    	socio.eta+=1;
	 }
	}
	public double etaMedia(){
		int sommaEta = 0;
		for (Socio socio : soci) {
    		sommaEta += socio.eta;
		}
		double etaMedia = (double) sommaEta / soci.size();
		return etaMedia;
	}
	public void etaMediaMaschiFemmine() {
    int sommaEtaMaschi = 0;
    int sommaEtaFemmine = 0;
    int numeroMaschi = 0;
    int numeroFemmine = 0;

    for (Socio socio : soci) {
        if (socio.sesso.equals("maschio")) {
            sommaEtaMaschi += socio.eta;
            numeroMaschi++;
        } else if (socio.sesso.equals("femmina")) {
            sommaEtaFemmine += socio.eta;
            numeroFemmine++;
        }
    }

    double etaMediaMaschi = (double) sommaEtaMaschi / numeroMaschi;
    double etaMediaFemmine = (double) sommaEtaFemmine / numeroFemmine;

    System.out.println("Eta media maschi: " + etaMediaMaschi + "; Eta media femmine: " + etaMediaFemmine);
}

	public void percentuale() {
    int numeroMaschi = 0;
    int numeroFemmine = 0;

    for (Socio socio : soci) {
        if (socio.sesso.equals("maschio")) {
            numeroMaschi++;
        } else if (socio.sesso.equals("femmina")) {
            numeroFemmine++;
        }
    }

    int totaleSoci = numeroMaschi + numeroFemmine;

    double percentualeMaschi = ((double) numeroMaschi / totaleSoci) * 100.0;
    double percentualeFemmine = ((double) numeroFemmine / totaleSoci) * 100.0;

    System.out.println("Percentuale maschi: " + percentualeMaschi + "; Percentuale femmine: " + percentualeFemmine);
}
}