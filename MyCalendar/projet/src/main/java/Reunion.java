import valueobjects.*;
public class Reunion implements Event{
    private final EventId id;
    private final TitreEvenement titre;
    private final Proprietaire proprietaire;
    private final DateEvenement dateDebut;
    private final DureeEvenement duree;
    private final Lieu lieu;
    private final Participants participants;

    public Reunion(EventId id,TitreEvenement titre, Proprietaire proprietaire,
                   DateEvenement dateDebut, DureeEvenement duree,
                   Lieu lieu, Participants participants) {
        this.id = id;
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public EventId id() { return id; }
    @Override
    public DateEvenement dateDebut() { return dateDebut; }

    @Override
    public DureeEvenement duree() { return duree; }

    @Override
    public TitreEvenement titre() { return titre; }


    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        return !dateDebut.valeur().isBefore(debut.valeur())
                && !dateDebut.valeur().isAfter(fin.valeur());
    }

    @Override
    public boolean estEnConflit(Event autre) {
        return dateDebut.valeur().isBefore(autre.dateDebut().valeur().plusMinutes(autre.duree().valeur()))
                && dateDebut.valeur().plusMinutes(duree.valeur()).isAfter(autre.dateDebut().valeur());
    }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }
}
