public class BreedingForbiddenException extends MeetingException {
    public BreedingForbiddenException(Humain h1, Humain h2) {
        super(h1, h2, "naissance impossible : " + h1.getNom() + " et " + h2.getNom() + " sont de meme sexe");
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }
}