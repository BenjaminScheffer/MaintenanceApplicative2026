package valueobjects;

public record EventId(String valeur) {
    @Override
    public String toString() {
        return valeur;
    }
}
