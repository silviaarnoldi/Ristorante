package it.itispaleocapa.ristorante;
import java.util.Date;
public class Prenotazione
{
    Cliente nomePrenotazione;
    Date giornoInserimento;
    Date gioPrenotazione;
    int numeroCoperti;
    public Prenotazione(Cliente c, Date gi, Date gp,int num)
    {
        this.nomePrenotazione=c;
        this.giornoInserimento=gi;
        this.gioPrenotazione=gp;
        this.numeroCoperti=num;
    }

    
}
