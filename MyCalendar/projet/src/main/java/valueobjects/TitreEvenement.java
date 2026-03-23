package valueobjects;
public record TitreEvenement(String title) {
    @Override
    public String toString() {
        return title;
    }
}
