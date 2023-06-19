import java.util.Random;
/**
 * Az orgyilkos egy olyan egyseg, aminek ara 20, elete 20, sebessege 7, kezdemenyezese 13, sebzese 10-13 <br>
 * Specialis kepessege: 25%-al tobbet sebez, ha az ellenseg mogott van
 */
public class Orgyilkos extends Egyseg
{
    private int bonuszSebzes;

    public Orgyilkos(boolean jatekos) {
        super(20, 20, 7, 13);
        if(jatekos) this.nev="Orgyilkos";
        else this.nev="orgyilkos";
        Random rnd = new Random();
        this.sebzes = rnd.nextInt(10, 14);
        this.bonuszSebzes = 25;
    }

    public int getBonuszSebzes() {
        return bonuszSebzes;
    }
}
