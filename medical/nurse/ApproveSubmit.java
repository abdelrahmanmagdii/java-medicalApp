package eg.edu.uofcanada.medical.nurse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApproveSubmit implements ActionListener {
    private NurseFrame nurseFrame;

    public ApproveSubmit(NurseFrame nurseFrame) {
        this.nurseFrame = nurseFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nurseFrame.registerApproval();
    }
}
