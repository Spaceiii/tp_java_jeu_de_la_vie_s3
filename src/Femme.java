public class Femme extends Humain {

    private int fertilite;

    Femme(String nom) {
        super(nom);

        fertilite = 0;
    }

    Femme(int age, int poids, String nom, int fertilite) {
        super(age, poids, nom);
        this.fertilite = fertilite;
    }

    int getFertilite() {
        return fertilite;
    }

    public void vieillir() {
        if (age == 15) fertilite = loto.nextInt(0, 100);
        if (age <= 20) poids = 3+(int)(2.6*age);
        else if (age >= 50) poids += (age % 2);
    }

    @Override
    public Humain rencontre(Humain h) throws MeetingException {
        if (h.isHomme()) {
            return rencontre((Homme) h);
        }
        throw new BreedingForbiddenException(this, h);
    }

    public Humain rencontre (Homme h) throws MeetingException {
        int f = loto.nextInt(0, 100);
        if (
            age <= 15 || age >= 50
            || h.getAge() <= 15
            || poids > 150 || h.getPoids() > 150
            || f > fertilite
        ) {
            throw new NoBreadingException(this, h);
        }

        int p = loto.nextInt(0, 100);

        Humain e;
        if (p < 50) {
            e = new Garcon(this.getNom() + h.getNom());
        } else {
            e = new Fille(this.getNom() + h.getNom());
        }

        int g = loto.nextInt(0, 20);
        h.setPoids(h.getPoids() + g);
        poids += 10;

        return e;
    }

    @Override
    protected void setEsperanceVie() {
        esperanceVie = loto.nextInt(55, 95);
    }

    @Override
    public boolean isHomme() {
        return false;
    }

    @Override
    public boolean isFemme() {
        return true;
    }

    public boolean isKid() {
        return false;
    }
}