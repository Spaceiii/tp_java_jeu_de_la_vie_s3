public class NoBreadingException extends MeetingException {
    public enum NoBreadingReason {
        BATIFOLAGE,
        FERTILITE,
        KID,
        AGE,
        WEIGHT
    }

    private static final String NAISSANCE_MESSAGE = "naissance impossible : ";

    public NoBreadingException(Humain h1, Humain h2, NoBreadingReason reason) {
        super(
            h1,
            h2,
            switch (reason) {
                case BATIFOLAGE -> NAISSANCE_MESSAGE + h1.getNom() + " et " + h2.getNom() + " batifolent!";
                case FERTILITE -> NAISSANCE_MESSAGE + h1.getNom() + " et " + h2.getNom() + " ne sont pas fertiles!";
                case KID -> NAISSANCE_MESSAGE + h1.getNom() + " et " + h2.getNom() + " sont trop jeunes!";
                case AGE -> NAISSANCE_MESSAGE + h1.getNom() + " et " + h2.getNom() + " sont trop vieux ou trop jeunes!";
                case WEIGHT -> NAISSANCE_MESSAGE + h1.getNom() + " et " + h2.getNom() + " sont trop gros!";
            });
        source = new Humain[2];
        source[0] = h1;
        source[1] = h2;
    }
}
