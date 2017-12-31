package enums;

public enum Sexe {

    FEMALE(2), //
    MALE(1);

    private final int code;

    Sexe(final int code) {
        this.code = code;
    }

    public static Sexe valueOfByCode(final int code) {
        switch (code) {
            case 2:
                return FEMALE;
            case 1:
                return MALE;
            default:
                throw new IllegalArgumentException("Le sexe demande n'existe pas.");
        }
    }

    public boolean isMale() {
        return this == MALE;
    }

    public int getCode() {
        return code;
    }

}
