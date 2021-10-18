package eg.edu.uofcanada.medical.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientLoginActionListener implements ActionListener {
    private PatientFrame patientFrame;

    public PatientLoginActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("To Do 1");
    }
}
