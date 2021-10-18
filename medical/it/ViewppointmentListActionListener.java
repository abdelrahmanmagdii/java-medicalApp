package eg.edu.uofcanada.medical.it;

import eg.edu.uofcanada.medical.patient.PatientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewppointmentListActionListener implements ActionListener {
    private ITFrame itFrame;


    public ViewppointmentListActionListener(ITFrame patientFrame) {
        this.itFrame = patientFrame;
    }

    public void actionPerformed(ActionEvent e) {
        itFrame.showPanel(itFrame.viewAppointmentListPanelName);
        itFrame.loadData();
    }
}
