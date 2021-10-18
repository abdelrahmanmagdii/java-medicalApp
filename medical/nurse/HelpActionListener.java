package eg.edu.uofcanada.medical.nurse;

import eg.edu.uofcanada.medical.common.Appointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HelpActionListener implements ActionListener {
    private NurseFrame nurseFrame;


    public HelpActionListener(NurseFrame nurseFrame) {
        this.nurseFrame = nurseFrame;
    }

    public void actionPerformed(ActionEvent e) {
        nurseFrame.showPanel(nurseFrame.helpPanelName);
    }
}
