import valueobjects.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }


    public void ajouterEventRendezVousPersonnel(String id,String titre, String proprietaire,
                                  LocalDateTime dateDebut, int duree) {
        events.add(new RendezVousPersonnel(new EventId(id),new TitreEvenement(titre), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree)));
    }

    public void ajouterEventReunion(String id,String title, String proprietaire,
                               LocalDateTime dateDebut, int duree,
                               String lieu, String participants) {
        events.add(new Reunion(new EventId(id),new TitreEvenement(title), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree), new Lieu(lieu), new Participants(participants)));
    }

    public void ajouterEventPeriodique(String id,String titre, String proprietaire,
                                           LocalDateTime dateDebut, int duree,
                                           int frequence) {
        events.add(new EvenementPeriodique(new EventId(id),new TitreEvenement(titre), new Proprietaire(proprietaire), new DateEvenement(dateDebut), new DureeEvenement(duree), new FrequenceJours(frequence)));
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        return events.stream()
                .filter(e -> e.estDansPeriode(new DateEvenement(debut), new DateEvenement(fin)))
                .toList();
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.estEnConflit(e2);
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public void supprimerEvent(String id) {
        for (Event e : events) {
            if(e.id().valeur().equals(id)){
                events.remove(e);
                break;
            }
        }
    }
}