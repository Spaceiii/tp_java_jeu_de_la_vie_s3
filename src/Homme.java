public class Homme extends Humain {

    protected int batifolage;
    int salaire;

    Homme(String nom) {
        super(nom);

        batifolage = 0;
    }

    Homme(int age, int poids, String nom, int batifolage) {
        super(age, poids, nom);
        this.batifolage = batifolage;

        if (age >= 18)
            salaire = loto.nextInt(1000, 11000);
    }

    public Humain rencontre (Femme f) throws MeetingException {
        int b = loto.nextInt(0, 100);
        int c = loto.nextInt(0, 100);
        if (
            f.isKid()
            || (age <= 15 || age >= 50 || f.getAge() <= 15)
            || (poids > 150 || f.getPoids() > 150)
        ) {
            throw new BreedingForbiddenException(this, f);
        }

        if (
            b < batifolage
            || c > f.getFertilite()
        ) {
            throw new NoBreadingException(this, f);
        }

        Humain e;
        int p = loto.nextInt(0, 100);
        if (p < 50) {
            e = new Garcon(nom + f.getNom());
        } else {
            e = new Fille(nom + f.getNom());
        }

        int g = loto.nextInt(-10, 10);
        poids += g;
        f.setPoids(f.getPoids() + 10);

        return e;
    }

    public void vieillir() {
        age++;
        if (age > 15) batifolage = loto.nextInt(70, 100);
        if (age > 30) batifolage = loto.nextInt(20, 50);
        if (age > 60) batifolage = loto.nextInt(50, 100);

        if (age <= 20) poids = 3+(int)(3.6*age);
        else if (age >= 50) poids += (age % 2);

        if (age == 18) salaire = loto.nextInt(1000, 11000);
    }

    @Override
    public Humain rencontre(Humain h) throws MeetingException {
        if (h.isFemme()) {
            return rencontre((Femme) h);
        }
        throw new BreedingForbiddenException(this, h);
    }

    @Override
    protected void setEsperanceVie() {
        esperanceVie = loto.nextInt(50, 80);
    }

    @Override
    public int compareTo(Humain h) {
        int comp = this.age - h.age;

        if (comp == 0) {
            if (h.isHomme()) {
                Homme homme = (Homme) h;
                return salaire - homme.salaire;
            }
            return 0;
        }

        return comp;
    }

    @Override
    public boolean isHomme() {
        return true;
    }

    @Override
    public boolean isFemme() {
        return false;
    }

    @Override
    public boolean isKid() {
        return false;
    }
}