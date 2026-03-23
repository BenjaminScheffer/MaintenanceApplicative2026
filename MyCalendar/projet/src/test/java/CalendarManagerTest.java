import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

    @Test
    void testAjoutEvent() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEvent("REUNION","test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                ,30,"CHEZ MOI","BOB",0);

        assertEquals(1,calendarManager.events.size());
        assertEquals("REUNION",calendarManager.events.getFirst().type);
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
        Event e = new Event("REUNION","test","Moi",
                LocalDateTime.of(2000, 7, 12, 12, 0) // 12/07/2000
                , 30,"CHEZ MOI","BOB",0);

        Event e1 = new Event("RDV_PERSONNEL","repas nouvelle année","Moi",
                LocalDateTime.of(2010, 1, 1, 12, 0), // 01/01/2010
                30,"","",0);

        Event e2 = new Event("PERIODIQUE","Musculation","Moi",
                LocalDateTime.of(2010, 1, 1, 14, 0) // 01/01/2010
                ,90,"","",30);

        Event e3 = new Event("PERIODIQUE","Escalade","Moi",
                LocalDateTime.of(2008, 1, 1, 14, 0) // 01/01/2008
                ,90,"","",30);

        Event e4 = new Event("RDV_PERSONNEL","Anniversaire LuLu","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020 12h00 -> 12h30
                ,30,"","",0);

        Event e5 = new Event("RDV_PERSONNEL","Anniversaire Lolo","Moi",
                LocalDateTime.of(2020, 11, 7, 12, 0) // 07/11/2020 12h00 -> 12h30
                ,30,"","",0);

        Event e6 = new Event("RDV_PERSONNEL","Mariage Paul","Moi",
                LocalDateTime.of(2020, 11, 7, 11, 0) // 07/11/2020 11h00 -> 12h30
                ,90,"","",0);

        assertFalse(calendarManager.conflit(e1, e3));
        assertFalse(calendarManager.conflit(e2, e));
        assertTrue(calendarManager.conflit(e4, e5));
        assertTrue(calendarManager.conflit(e5, e6));


    }
}
