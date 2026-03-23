import valueobjects.*;

public class EvenementPeriodique implements Event{
    private final TitreEvenement titre;
    private final Proprietaire proprietaire;
    private final DateEvenement dateDebut;
    private final DureeEvenement duree;
    private final FrequenceJours frequence;

    public EvenementPeriodique(TitreEvenement titre, Proprietaire proprietaire,
                               DateEvenement dateDebut, DureeEvenement duree,
                               FrequenceJours frequence) {
        this.titre = titre;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.frequence = frequence;
    }

    public FrequenceJours frequenceJours() { return frequence; }

    @Override
    public DateEvenement dateDebut() { return dateDebut; }

    @Override
    public DureeEvenement duree() { return duree; }

    @Override
    public TitreEvenement titre() { return titre; }

    @Override
    public String description() {
        return "Événement périodique : " + titre + " tous les " + frequence + " jours";
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        var temp = dateDebut.valeur();
        while (!temp.isAfter(fin.valeur())) {
            if (!temp.isBefore(debut.valeur())) {
                return true;
            }
            temp = temp.plusDays(frequence.valeur());
        }
        return false;
    }

    @Override
    public boolean estEnConflit(Event autre) {
        return false;
    }
}
