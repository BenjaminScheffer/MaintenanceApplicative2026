package valueobjects;
public record FrequenceJours (int valeur){
    @Override
    public String toString() {
        return String.valueOf(valeur);
    }
}
