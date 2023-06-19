import java.util.Random;
/**
 * A Pajzsos egy olyan egyseg, aminek ara 25, elete 60, sebessege 3, kezdemenyezese 12, sebzese 10-14 <br>
 * Specialis kepessege: Haritas. 30% eselye van, hogy ne kapjon sebzest
 */
public class Pajzsos extends Egyseg
{
    private int haritasiKepesseg;
    public Pajzsos(boolean jatekos)
    {
        super(25, 60, 3, 12);
        if(jatekos) this.nev="Pajzsos";
        else this.nev="pajzsos";
        this.haritasiKepesseg = 30;
        Random rnd = new Random();
        this.sebzes = rnd.nextInt(10,15);
    }

    /**
     * Haritast megvalosito metodus
     * @return Igaz, ha haritott, Hamis, ha nem.
     */
    public boolean haritott()
    {
        Random rnd = new Random() ;
        if(this.haritasiKepesseg >= rnd.nextInt(1,101))
        {
            System.out.println("Az egység hárított");
            return true;
        }
        return false;
    }

    public int getHaritasiKepesseg() {
        return haritasiKepesseg;
    }
}
