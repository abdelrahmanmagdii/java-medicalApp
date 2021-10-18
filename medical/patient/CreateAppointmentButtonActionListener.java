package eg.edu.uofcanada.medical.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAppointmentButtonActionListener implements ActionListener {
    private PatientFrame patientFrame;

    public CreateAppointmentButtonActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        patientFrame.createAppointment();
    }
}
