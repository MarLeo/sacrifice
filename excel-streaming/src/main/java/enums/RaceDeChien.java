package enums;

public enum RaceDeChien {
    BASSET_ALPES("Basset des Alpes", "basset_alp"), //
    BERGER_ALLEMAND("Berger allemand", "berger_all"), //
    CANICHE("Caniche", "caniche"), //
    HARRIER("Harrier", "harrier"), //
    GOLDEN("Golden retriever", "golden_ret"), //
    ROTTWEILER("Rottweiler", "rottweiler"), //
    WESTIE("Westie", "westie");

    private final String label;
    private final String code;

    RaceDeChien(final String label, final String code) {
        this.label = label;
        this.code = code;
    }

    public static RaceDeChien valueOfByCode(final String code) {

        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Le code ne peut pas etre vide.");
        }

        for (RaceDeChien race : values()) {
            if (race.code.equalsIgnoreCase(code)) {
                return race;
            }
        }

        throw new IllegalArgumentException("La race de chien demandee n'existe pas.");
    }

    public String getLabel() {
        return label;
    }

    public String getCode() {
        return code;
    }


}
