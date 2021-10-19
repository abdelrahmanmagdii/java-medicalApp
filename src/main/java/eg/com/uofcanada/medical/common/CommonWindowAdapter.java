package eg.com.uofcanada.medical.common;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CommonWindowAdapter extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
