public record DureeEvenement (int dureeMinutes){
    @Override
    public String toString() {
        return String.valueOf(dureeMinutes);
    }
}
