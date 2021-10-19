package eg.com.uofcanada.medical.it;

import eg.com.uofcanada.medical.common.Appointment;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AppointmentItemListener implements ItemListener {
    private ITFrame itFrame;


    public AppointmentItemListener(ITFrame patientFrame) {
        this.itFrame = patientFrame;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            itFrame.appointmentReport.setText("");
            itFrame.approve.setEnabled(false);
            itFrame.refuse.setEnabled(false);
            itFrame.approvalSubmit.setEnabled(false);
            Object selectedItem = itFrame.appointmentList.getSelectedItem();
            if(selectedItem instanceof Appointment)
            {
                Appointment app = (Appointment) selectedItem;
                String reportStr =app.getDay()+"\\"+app.getMonth()+"\\"+ app.getYear()
                        +", status "+app.getStatusStr();
                if((app.getPatientComment()!= null)&&app.getPatientComment().length()>0)
                    reportStr+=", comment "+app.getPatientComment();
                itFrame.appointmentReport.setText(reportStr);
                itFrame.approve.setEnabled(true);
                itFrame.refuse.setEnabled(true);
                itFrame.approvalSubmit.setEnabled(true);
            }
        }
    }
}
