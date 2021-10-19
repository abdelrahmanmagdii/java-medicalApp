package eg.com.uofcanada.medical.common;

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
