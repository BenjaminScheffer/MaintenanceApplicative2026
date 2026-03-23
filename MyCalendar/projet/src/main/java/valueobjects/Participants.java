package valueobjects;
public record Participants(String participants) {
    @Override
    public String toString() {
        return participants;
    }
}
