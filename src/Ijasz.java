import java.util.Random;
/**
 * Az ijasz egy olyan egyseg, aminek ara 6, elete 7, sebessege 4, kezdemenyezese 9, sebzese 2-5 <br>
 * Specialis kepessege: Loves
 */

public class Ijasz extends Egyseg{
    public Ijasz(boolean jatekos)
    {
        super(6,7,4,9);
        if(jatekos) this.nev="Ijasz";
        else this.nev="ijasz";
        Random rnd = new Random();
        this.sebzes = rnd.nextInt(2,5);
    }
}
