import valueobjects.*;

public class RendezVousPersonnel implements Event{

    private final TitreEvenement title;
    private final Proprietaire proprietaire;
    private final DateEvenement dateDebut;
    private final DureeEvenement duree;

    public RendezVousPersonnel(TitreEvenement titre, Proprietaire proprietaire,
                               DateEvenement dateDebut, DureeEvenement duree) {
        this.title = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }


    @Override
    public DateEvenement dateDebut() {
        return dateDebut;
    }

    @Override
    public DureeEvenement duree() {
        return duree;
    }

    @Override
    public TitreEvenement titre() {
        return title;
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut;
    }
}
