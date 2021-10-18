package eg.edu.uofcanada.medical.common;

import eg.edu.uofcanada.medical.common.BasicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonActionListener implements ActionListener {
    private BasicFrame patientFrame;

    public LoginButtonActionListener(BasicFrame patientFrame) {
        this.patientFrame = patientFrame;
    }

    public void actionPerformed(ActionEvent e) {
        patientFrame.processLoginButton();
    }
}
