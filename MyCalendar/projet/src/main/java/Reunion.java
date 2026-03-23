import valueobjects.*;
public class Reunion implements Event{
    private final TitreEvenement titre;
    private final Proprietaire proprietaire;
    private final DateEvenement dateDebut;
    private final DureeEvenement duree;
    private final Lieu lieu;
    private final Participants participants;

    public Reunion(TitreEvenement titre, Proprietaire proprietaire,
                   DateEvenement dateDebut, DureeEvenement duree,
                   Lieu lieu, Participants participants) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public DateEvenement dateDebut() { return dateDebut; }

    @Override
    public DureeEvenement duree() { return duree; }

    @Override
    public TitreEvenement titre() { return titre; }

    @Override
    public String description() {
        return "Réunion : " + titre + " à " + lieu + " avec " + participants;
    }
}
