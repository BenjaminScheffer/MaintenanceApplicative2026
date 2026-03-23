import java.time.LocalDateTime;

public record DateEvenement (LocalDateTime dateDebut){
    @Override
    public String toString() {
        return dateDebut.toString();
    }
}
