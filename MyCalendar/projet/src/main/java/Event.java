import valueobjects.*;
public interface Event {
    DateEvenement dateDebut();
    DureeEvenement duree();
    TitreEvenement titre();
    String description();
    boolean estDansPeriode(DateEvenement debut, DateEvenement fin);
    boolean estEnConflit(Event autre);
}