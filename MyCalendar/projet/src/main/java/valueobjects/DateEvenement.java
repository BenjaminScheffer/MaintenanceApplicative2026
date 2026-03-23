package valueobjects;
import java.time.LocalDateTime;

public record DateEvenement (LocalDateTime valeur){
    @Override
    public String toString() {
        return valeur.toString();
    }
}
