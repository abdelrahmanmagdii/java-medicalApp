package eg.edu.uofcanada.medical.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class PatientTest {

    @Test
    public void getName() {
        Patient p = new Patient();
        p.setName("Abdo");
        assertNotNull(p);
        assertEquals("Abdo", p.getName());

    }

    @Test
    public void getEmail() {
        Patient p = new Patient();
        assertNotNull(p);
        p.setEmail("a");
        assertEquals("a", p.getEmail());

    }

    @Test
    public void getPassword() {
        Patient p = new Patient();
        assertNotNull(p);
        p.setPassword("a");
        assertEquals("a", p.getPassword());

    }
}
