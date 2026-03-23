import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

    @Test
    void testAjoutEventReunion() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEventReunion("test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                ,30,"CHEZ MOI","BOB");

        assertEquals(1,calendarManager.events.size());
        assertInstanceOf(Reunion.class, calendarManager.events.getFirst());
    }

    @Test
    void testAjoutEventRdv() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEventRendezVousPersonnel("test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                ,30);

        assertEquals(1,calendarManager.events.size());
        assertInstanceOf(RendezVousPersonnel.class, calendarManager.events.getFirst());
    }

    @Test
    void testAjoutEventPeriodique() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEventPeriodique("test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                ,30);

        assertEquals(1,calendarManager.events.size());
        assertInstanceOf(EvenementPeriodique.class, calendarManager.events.getFirst());
    }

    @Test
    void testEventsDansPeriode() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEvent("REUNION","test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                , 30,"CHEZ MOI","BOB",0);

        calendarManager.ajouterEvent("RDV_PERSONNEL","repas nouvelle année","Moi",
                LocalDateTime.of(2010, 1, 1, 12, 0), // 01/01/2010
                30,"","",0);

        calendarManager.ajouterEvent("PERIODIQUE","Musculation","Moi",
                LocalDateTime.of(2010, 1, 1, 14, 0) // 01/01/2010
                ,90,"","",30);

        calendarManager.ajouterEvent("PERIODIQUE","Escalade","Moi",
                LocalDateTime.of(2008, 1, 1, 14, 0) // 01/01/2008
                ,90,"","",30);

        calendarManager.ajouterEvent("RDV_PERSONNEL","Anniversaire LuLu","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020
                ,30,"","",0);

        List<Event> events = calendarManager.eventsDansPeriode(
                LocalDateTime.of(2009,1,1,0,0), // 01/01/2009
                LocalDateTime.of(2011,1,1,0,0)  // 01/01/2011
        );

        assertEquals(3,events.size());
    }

    @Test
    void testConflit() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEvent("REUNION","test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                , 30,"CHEZ MOI","BOB",0);

        calendarManager.ajouterEvent("RDV_PERSONNEL","repas nouvelle année","Moi",
                LocalDateTime.of(2010, 1, 1, 12, 0), // 01/01/2010
                30,"","",0);

        calendarManager.ajouterEvent("PERIODIQUE","Musculation","Moi",
                LocalDateTime.of(2010, 1, 1, 14, 0) // 01/01/2010
                ,90,"","",30);

        calendarManager.ajouterEvent("PERIODIQUE","Escalade","Moi",
                LocalDateTime.of(2008, 1, 1, 14, 0) // 01/01/2008
                ,90,"","",30);

        calendarManager.ajouterEvent("RDV_PERSONNEL","Anniversaire LuLu","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020 12h00 -> 12h30
                ,30,"","",0);

        calendarManager.ajouterEvent("RDV_PERSONNEL","Anniversaire Lolo","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020 12h00 -> 12h30
                ,30,"","",0);

        calendarManager.ajouterEvent("RDV_PERSONNEL","Mariage Paul","Moi",
                LocalDateTime.of(2020, 11, 7, 11, 0) // 07/11/2020 11h00 -> 12h30
                ,90,"","",0);

        assertFalse(calendarManager.conflit(calendarManager.events.get(1), calendarManager.events.get(3)));
        assertFalse(calendarManager.conflit(calendarManager.events.get(2), calendarManager.events.getFirst()));
        assertTrue(calendarManager.conflit(calendarManager.events.get(4), calendarManager.events.get(5)));
        assertTrue(calendarManager.conflit(calendarManager.events.get(5), calendarManager.events.get(6)));
        assertFalse(calendarManager.conflit(calendarManager.events.get(1), calendarManager.events.get(6)));
        assertFalse(calendarManager.conflit(calendarManager.events.get(6), calendarManager.events.get(1)));
    }

    @Test
    void testEventDescription() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEvent("REUNION","test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                , 30,"CHEZ MOI","BOB",0);
        assertEquals("Réunion : test à CHEZ MOI avec BOB",calendarManager.events.getFirst().description());

        calendarManager.ajouterEvent("RDV_PERSONNEL","repas nouvelle année","Moi",
                LocalDateTime.of(2010, 1, 1, 12, 0), // 01/01/2010
                30,"","",0);
        assertEquals("RDV : repas nouvelle année à 2010-01-01T12:00",calendarManager.events.get(1).description());

        calendarManager.ajouterEvent("PERIODIQUE","Musculation","Moi",
                LocalDateTime.of(2010, 1, 1, 14, 0) // 01/01/2010
                ,90,"","",30);
        assertEquals("Événement périodique : Musculation tous les 30 jours",calendarManager.events.get(2).description());

        calendarManager.ajouterEvent("PERIODIQUE","Escalade","Moi",
                LocalDateTime.of(2008, 1, 1, 14, 0) // 01/01/2008
                ,90,"","",30);
        assertEquals("Événement périodique : Escalade tous les 30 jours",calendarManager.events.get(3).description());

        calendarManager.ajouterEvent("RDV_PERSONNEL","Anniversaire LuLu","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020
                ,30,"","",0);
        assertEquals("RDV : Anniversaire LuLu à 2020-11-07T12:00",calendarManager.events.get(4).description());

        calendarManager.ajouterEvent("RDV_PERSONNEL","Anniversaire Lolo","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020
                ,30,"","",0);
        assertEquals("RDV : Anniversaire Lolo à 2020-11-07T12:00",calendarManager.events.get(5).description());

        calendarManager.ajouterEvent("RDV_PERSONNEL","Mariage Paul","Moi",
                LocalDateTime.of(2020, 11, 7, 11, 0) // 07/11/2020
                ,90,"","",0);
        assertEquals("RDV : Mariage Paul à 2020-11-07T11:00",calendarManager.events.get(6).description());
    }

}
