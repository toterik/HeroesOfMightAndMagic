import java.util.Random;

/**
 * A foldmuves egy olyan egyseg, aminek ara 2, elete 3, sebessege 15, kezdemenyezese 8, sebzese 1 <br>
 * Specialis kepessege nincs
 */
public class Foldmuves extends Egyseg
{
    public Foldmuves(boolean jatekos) {
        super(2, 3 ,4,8);
        if(jatekos) this.nev="Foldmuves";
        else this.nev="foldmuves";
        this.sebzes = 1;
    }


}
