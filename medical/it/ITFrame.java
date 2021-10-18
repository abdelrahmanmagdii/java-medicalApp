package eg.edu.uofcanada.medical.it;

import eg.edu.uofcanada.medical.common.*;
import eg.edu.uofcanada.medical.patient.CreateAppointmentActionListener;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ITFrame extends BasicFrame {

    private JMenu appointmentMenu;
    protected JTextArea appointmentReport;
    public final String viewAppointmentListPanelName = "viewAppointmentListPanel";
    protected JComboBox<ComboElement> appointmentList;
    protected JRadioButton approve;
    protected JRadioButton refuse;
    protected JButton approvalSubmit;

    public ITFrame()
    {
        appointmentMenu = new JMenu("Appointment");
        appointmentMenu.setMnemonic('a');
        menuBar.add(appointmentMenu);
        appointmentMenu.setEnabled(false);//start disabled till login

        JMenuItem viewAppointment = new JMenuItem("View Appointment List");
        viewAppointment.setMnemonic('v');
        appointmentMenu.add(viewAppointment);
        viewAppointment.addActionListener(new ViewppointmentListActionListener(this));

        JPanel appointmentPanel = createViewAppointmentPanel();
        mainPanel.add(viewAppointmentListPanelName, appointmentPanel);
    }

    private JPanel createViewAppointmentPanel() {
        JPanel myPanel = new JPanel(new GridLayout(4,1));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        appointmentList = new JComboBox<>();
        DefaultCombo defaultCombo = new DefaultCombo();
        appointmentList.addItem(defaultCombo);
        titlePanel.add(appointmentList);
        myPanel.add(titlePanel);

        JPanel reportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        appointmentReport = new JTextArea(5,30);
        appointmentReport.setEditable(false);
        reportPanel.add(appointmentReport);
        appointmentReport.setLineWrap(true);
        appointmentReport.setWrapStyleWord(true);
        myPanel.add(reportPanel);

        appointmentList.addItemListener(new AppointmentItemListener(this));

        JPanel approvalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(approvalPanel);
        ButtonGroup approvalGroup = new ButtonGroup();
        approve = new JRadioButton("Approve");
        approvalPanel.add(approve);
        refuse = new JRadioButton("Refuse");
        approvalPanel.add(refuse);
        approvalGroup.add(approve);
        approvalGroup.add(refuse);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(submitPanel);
        approvalSubmit = new JButton("Submit");
        submitPanel.add(approvalSubmit);

        approve.setEnabled(false);
        refuse.setEnabled(false);
        approvalSubmit.setEnabled(false);
        approvalSubmit.addActionListener(new ApproveSubmit(this));

        return myPanel;
    }

    @Override
    public void processLoginButton() {
        String email = this.logEmail.getText();
        String password = this.logPassword.getText();

        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "SELECT " +
                    "    `it`.`name`\n" +
                    " FROM `cs_medical_app`.`it`" +
                    " WHERE `it`.`email`=? " +
                    " AND `it`.`password`= ?";
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, email);
            s.setString(2, password);


            ResultSet resultSet = s.executeQuery();
            String name = "";
            if(resultSet.next())
            {
                name = resultSet.getString("name");
                //if everyting is OK, start the app
                this.login.setEnabled(false);
                this.appointmentMenu.setEnabled(true);
                this.email = email;
                this.showPanel(this.welcomePanelName);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid email/password", "En Error Has Occurred", JOptionPane.ERROR_MESSAGE);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            //System.exit(1);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "En Error Has Occurred", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if(con != null)
            {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


    }

    public void loadData()
    {
        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "SELECT `appointment`.`id` , \n" +
                    "    `appointment`.`status` , \n" +
                    "    `appointment`.`app_year`,\n" +
                    "    `appointment`.`app_month`,\n" +
                    "    `appointment`.`app_day`,\n" +
                    "    `appointment`.`patient_comment`,\n" +
                    "    `appointment`.`nurse_comment` \n" +
                    "  FROM `cs_medical_app`.`appointment`" +
                    "  WHERE `appointment`.`status` = ?\n";
            PreparedStatement s = con.prepareStatement(sql);
            s.setInt(1, 1);


            ResultSet resultSet = s.executeQuery();
            ArrayList<Appointment> list = new ArrayList<>();
            while(resultSet.next())
            {
                Appointment app = new Appointment();
                list.add(app);
                app.setId(resultSet.getInt("id"));
                app.setYear(resultSet.getInt("app_year"));
                app.setMonth(resultSet.getInt("app_month"));
                app.setDay(resultSet.getInt("app_day"));
                app.setPatientComment(resultSet.getString("patient_comment"));
                app.setNurseComment(resultSet.getString("nurse_comment"));
                app.setStatus(resultSet.getInt("status"));
            }

            this.appointmentReport.setText("");
            this.appointmentList.removeAllItems();
            this.appointmentList.addItem(new DefaultCombo());
            for(Appointment app: list)
            {
                this.appointmentList.addItem(app);
            }




        } catch (Exception ex) {
            ex.printStackTrace();
            //System.exit(1);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "En Error Has Occurred", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if(con != null)
            {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void registerApproval()
    {
        Object selectedItem = this.appointmentList.getSelectedItem();
        if(selectedItem instanceof Appointment)
        {
            Appointment app = (Appointment) selectedItem;
            if(this.approve.isSelected()==true)
            {
                app.setStatus(2);
            }
            if(this.refuse.isSelected()==true)
            {
                app.setStatus(5);
            }
            if(app.getStatus()==1)
            {
                JOptionPane.showMessageDialog(this,"Please select approve or refuse", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Connection con = null;
                try {

                    Class.forName(Constant.driverName).newInstance();
                    con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
                    String sql = " UPDATE `cs_medical_app`.`appointment`\n" +
                            " SET `status` = ?"+
                            " WHERE `id` = ?";
                    PreparedStatement s = con.prepareStatement(sql);
                    s.setInt(1, app.getStatus());
                    s.setInt(2, app.getId());

                    s.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Approval Submitted", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    this.showPanel(this.welcomePanelName);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //System.exit(1);
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "En Error Has Occurred", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    if(con != null)
                    {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
             }

        }
    }
}
