package valueobjects;
public record TitreEvenement(String valeur) {
    @Override
    public String toString() {
        return valeur;
    }
}
