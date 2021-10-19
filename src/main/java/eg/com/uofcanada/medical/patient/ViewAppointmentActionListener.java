package eg.com.uofcanada.medical.patient;

import eg.com.uofcanada.medical.common.BasicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAppointmentActionListener implements ActionListener {
    private PatientFrame patientFrame;

    public ViewAppointmentActionListener(PatientFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        patientFrame.showPanel(patientFrame.viewAppointmentPanelName);
        patientFrame.getData();
    }
}
