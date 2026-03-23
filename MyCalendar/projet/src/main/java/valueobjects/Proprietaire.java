package valueobjects;
public record Proprietaire(String valeur) {
    @Override
    public String toString() {
        return valeur;
    }
}
