import valueobjects.*;

public class RendezVousPersonnel implements Event{

    private final EventId id;
    private final TitreEvenement title;
    private final Proprietaire proprietaire;
    private final DateEvenement dateDebut;
    private final DureeEvenement duree;

    public RendezVousPersonnel(EventId id,TitreEvenement titre, Proprietaire proprietaire,
                               DateEvenement dateDebut, DureeEvenement duree) {

        this.id = id;
        this.title = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    @Override
    public EventId id() { return id; }

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
        return "RDV : " + title + " à " + dateDebut;
    }
}
