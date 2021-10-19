package eg.com.uofcanada.medical.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPatientActionListener implements ActionListener{
    private PatientFrame patientFrame;

    public RegisterPatientActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        patientFrame.registerPatient();
    }
}
