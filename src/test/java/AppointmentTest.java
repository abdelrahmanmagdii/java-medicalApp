import eg.com.uofcanada.medical.common.Appointment;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppointmentTest {

    @Test
    public void getId() {
        Appointment app = new Appointment();
        app.setId(1);
        assertEquals(1, app.getId());
    }

    @Test
    public void getStatus() {
        Appointment app = new Appointment();
        app.setStatus(1);
        assertEquals(1, app.getStatus());
    }

    @Test
    public void getStatusStr() {
        //case 1: return "submitted";
        //            case 2: return "IT approved";
        //            case 3: return "nurse approved";
        //            case 5: return "rejected";
        //            default: return "No Status";
        Appointment app = new Appointment();
        app.setStatus(1);
        assertEquals("submitted", app.getStatusStr());
        app.setStatus(2);
        assertEquals("IT approved", app.getStatusStr());
        app.setStatus(3);
        assertEquals("nurse approved", app.getStatusStr());
        app.setStatus(5);
        assertEquals("rejected", app.getStatusStr());
        app.setStatus(0);
        assertEquals("No Status", app.getStatusStr());
        app.setStatus(10);
        assertEquals("No Status", app.getStatusStr());
    }
}
