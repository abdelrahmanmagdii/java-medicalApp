package eg.edu.uofcanada.medical.it;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApproveSubmit implements ActionListener {
    private ITFrame itFrame;

    public ApproveSubmit(ITFrame itFrame) {
        this.itFrame = itFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        itFrame.registerApproval();
    }
}
