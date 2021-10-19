package eg.com.uofcanada.medical.nurse;

import eg.com.uofcanada.medical.common.Appointment;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AppointmentItemListener implements ItemListener {
    private NurseFrame nurseFrame;


    public AppointmentItemListener(NurseFrame nurseFrame) {
        this.nurseFrame = nurseFrame;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            nurseFrame.appointmentReport.setText("");
            nurseFrame.approve.setEnabled(false);
            nurseFrame.refuse.setEnabled(false);
            nurseFrame.nurseComment.setEnabled(false);
            nurseFrame.approvalSubmit.setEnabled(false);
            Object selectedItem = nurseFrame.appointmentList.getSelectedItem();
            if(selectedItem instanceof Appointment)
            {
                Appointment app = (Appointment) selectedItem;
                String reportStr =app.getDay()+"\\"+app.getMonth()+"\\"+ app.getYear()
                        +", status "+app.getStatusStr();
                if((app.getPatientComment()!= null)&&app.getPatientComment().length()>0)
                    reportStr+=", comment "+app.getPatientComment();
                nurseFrame.appointmentReport.setText(reportStr);
                nurseFrame.approve.setEnabled(true);
                nurseFrame.refuse.setEnabled(true);
                nurseFrame.approvalSubmit.setEnabled(true);
                nurseFrame.nurseComment.setEnabled(true);
            }
        }
    }
}
