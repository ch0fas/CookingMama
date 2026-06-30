package cm_enums;

public enum UserArgs
{
    NAME("Name"), AGE("Age"), DAILY_PROTEIN_GOAL("Daily Protein Goal");
    private final String nom_arg;
    private UserArgs(String s)
    {
        this.nom_arg = s;
    }

    public String toString()
    {
        return this.nom_arg;
    }
}