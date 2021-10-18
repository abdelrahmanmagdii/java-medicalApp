package eg.edu.uofcanada.medical.common;

import eg.edu.uofcanada.medical.common.BasicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicLoginActionListener implements ActionListener {
    private BasicFrame basicFrame;

    public BasicLoginActionListener(BasicFrame basicFrame) {
        this.basicFrame = basicFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        basicFrame.showPanel(basicFrame.loginPanelName);
    }
}
