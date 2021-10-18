package eg.edu.uofcanada.medical.nurse;

import eg.edu.uofcanada.medical.patient.PatientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewppointmentListActionListener implements ActionListener {
    private NurseFrame nurseFrame;


    public ViewppointmentListActionListener(NurseFrame nurseFrame) {
        this.nurseFrame = nurseFrame;
    }

    public void actionPerformed(ActionEvent e) {
        nurseFrame.showPanel(nurseFrame.viewAppointmentListPanelName);
        nurseFrame.loadData();
    }
}
