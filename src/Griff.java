import java.util.Random;

/**
 * A griff egy olyan egyseg, aminek ara 15, elete 30, sebessege 7, kezdemenyezese 15, sebzese 5-10 <br>
 * Specialis kepessege: vegtelen visszatamadas
 */
public class Griff extends Egyseg
{
    public Griff(boolean jatekos) {
        super(15, 30, 7, 15);
        if(jatekos) this.nev="Griff";
        else this.nev="griff";
        Random rnd = new Random();
        this.sebzes = rnd.nextInt(5, 11);
    }
}
