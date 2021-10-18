package eg.edu.uofcanada.medical.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener {
        public ExitActionListener()
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //System.out.println("Hi");
        System.exit(0);
    }
}
