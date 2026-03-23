import valueobjects.*;
public interface Event {
    DateEvenement dateDebut();
    DureeEvenement duree();
    TitreEvenement titre();
    String description();
}