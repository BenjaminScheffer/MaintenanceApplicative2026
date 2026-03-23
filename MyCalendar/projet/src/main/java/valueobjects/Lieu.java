package valueobjects;
public record Lieu(String valeur) {
    @Override
    public String toString() {
        return valeur;
    }
}
