import java.sql.Date;
import java.time.LocalDateTime;

public class Event {
    public String type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public TitreEvenement title;
    public Propietaire proprietaire;
    public DateEvenement dateDebut;
    public DureeEvenement dureeMinutes;
    public Lieu lieu; // utilisé seulement pour REUNION
    public Participants participants; // séparés par virgules (pour REUNION uniquement)
    public FrequenceJours frequenceJours; // uniquement pour PERIODIQUE

    public Event(String type, TitreEvenement title, Propietaire proprietaire, DateEvenement dateDebut, DureeEvenement dureeMinutes,
                 Lieu lieu, Participants participants, FrequenceJours frequenceJours) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.lieu = lieu;
        this.participants = participants;
        this.frequenceJours = frequenceJours;
    }

    public String description() {
        String desc = "";
        if (type.equals("RDV_PERSONNEL")) {
            desc = "RDV : " + title + " à " + dateDebut.toString();
        } else if (type.equals("REUNION")) {
            desc = "Réunion : " + title + " à " + lieu + " avec " + participants;
        } else if (type.equals("PERIODIQUE")) {
            desc = "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
        }
        return desc;
    }
}