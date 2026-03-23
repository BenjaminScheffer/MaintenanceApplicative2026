import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                             String lieu, String participants, int frequenceJours) {
        Event e = new Event(type, new TitreEvenement(title), new Propietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(dureeMinutes), new Lieu(lieu), new Participants(participants), new FrequenceJours(frequenceJours));
        events.add(e);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.type.equals("PERIODIQUE")) {
                LocalDateTime temp = e.dateDebut.dateDebut();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(e.frequenceJours.frequenceJours());
                }
            } else if (!e.dateDebut.dateDebut().isBefore(debut) && !e.dateDebut.dateDebut().isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.dateDebut().plusMinutes(e1.dureeMinutes.dureeMinutes());
        LocalDateTime fin2 = e2.dateDebut.dateDebut().plusMinutes(e2.dureeMinutes.dureeMinutes());

        if (e1.type.equals("PERIODIQUE") || e2.type.equals("PERIODIQUE")) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.dateDebut().isBefore(fin2) && fin1.isAfter(e2.dateDebut.dateDebut())) {
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