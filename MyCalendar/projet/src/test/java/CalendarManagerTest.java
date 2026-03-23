import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarManagerTest {

    @Test
    void testAjoutEvent() {
        CalendarManager calendarManager = new CalendarManager();
        calendarManager.ajouterEvent("REUNION","test","Moi",LocalDateTime.of(2000, 7, 12, 12, 0),30,"CHEZ MOI","BOB",0);
        assertEquals(1,calendarManager.events.size());
        assertEquals("REUNION",calendarManager.events.getFirst().type);
    }

}
