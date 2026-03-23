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
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        return !dateDebut.dateDebut().isBefore(debut.dateDebut())
                && !dateDebut.dateDebut().isAfter(fin.dateDebut());
    }

    @Override
    public boolean estEnConflit(Event autre) {
        return dateDebut.dateDebut().isBefore(autre.dateDebut().dateDebut().plusMinutes(autre.duree().dureeMinutes()))
                && dateDebut.dateDebut().plusMinutes(duree.dureeMinutes()).isAfter(autre.dateDebut().dateDebut());
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut;
    }
}
