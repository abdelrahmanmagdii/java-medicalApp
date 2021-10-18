package eg.edu.uofcanada.medical.patient;

import eg.edu.uofcanada.medical.common.BasicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class CreateAppointmentActionListener implements ActionListener {
    private PatientFrame patientFrame;

    public CreateAppointmentActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        patientFrame.showPanel(patientFrame.createAppointmentPanelName);
    }
}
