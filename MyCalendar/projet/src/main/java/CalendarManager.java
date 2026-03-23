import valueobjects.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }


    public void ajouterEventRendezVousPersonnel(String titre, String proprietaire,
                                  LocalDateTime dateDebut, int duree) {
        events.add(new RendezVousPersonnel(new TitreEvenement(titre), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree)));
    }

    public void ajouterEventReunion(String title, String proprietaire,
                               LocalDateTime dateDebut, int duree,
                               String lieu, String participants) {
        events.add(new Reunion(new TitreEvenement(title), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree), new Lieu(lieu), new Participants(participants)));
    }

    public void ajouterEventPeriodique(String titre, String proprietaire,
                                           LocalDateTime dateDebut, int duree,
                                           int frequence) {
        events.add(new EvenementPeriodique(new TitreEvenement(titre), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree), new FrequenceJours(frequence)));
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e instanceof EvenementPeriodique) {
                EvenementPeriodique event = (EvenementPeriodique) e;
                LocalDateTime temp = e.dateDebut().dateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(event.frequenceJours().frequenceJours());
                }
            } else if (!e.dateDebut().dateDebut().isBefore(debut) && !e.dateDebut().dateDebut().isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut().dateDebut().plusMinutes(e1.duree().dureeMinutes());
        LocalDateTime fin2 = e2.dateDebut().dateDebut().plusMinutes(e2.duree().dureeMinutes());

        if (e1 instanceof EvenementPeriodique || e2 instanceof EvenementPeriodique) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut().dateDebut().isBefore(fin2) && fin1.isAfter(e2.dateDebut().dateDebut())) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}