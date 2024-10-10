public class Fille extends Femme {
    Fille(String nom) {
        super(nom);
    }

    Fille(int age, int poids, String nom, int fertilite) {
        super(age, poids, nom, fertilite);
    }

    public void vieillir() {
        super.vieillir();
    }

    @Override
    public Humain rencontre(Humain h) throws BreedingForbiddenException {
        throw new BreedingForbiddenException(this, h);
    }

    protected void setEsperanceVie() {
        esperanceVie = loto.nextInt(55, 95);
    }

    @Override
    public boolean isKid() {
        return true;
    }
}
