package valueobjects;
public record DureeEvenement (int valeur){
    @Override
    public String toString() {
        return String.valueOf(valeur);
    }
}
