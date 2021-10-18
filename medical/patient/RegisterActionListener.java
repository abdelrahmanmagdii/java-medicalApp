package eg.edu.uofcanada.medical.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterActionListener implements ActionListener {
    private PatientFrame patientFrame;

    public RegisterActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    public void actionPerformed(ActionEvent e) {
        patientFrame.showPanel(patientFrame.registerPanelName);
    }
}
