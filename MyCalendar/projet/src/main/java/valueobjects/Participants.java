package valueobjects;
public record Participants(String valeur) {
    @Override
    public String toString() {
        return valeur;
    }
}
