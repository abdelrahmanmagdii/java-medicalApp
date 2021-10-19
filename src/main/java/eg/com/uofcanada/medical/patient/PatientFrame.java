package eg.com.uofcanada.medical.patient;

import eg.com.uofcanada.medical.common.Appointment;
import eg.com.uofcanada.medical.common.BasicFrame;
import eg.com.uofcanada.medical.common.Constant;
import eg.com.uofcanada.medical.common.Patient;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class PatientFrame extends BasicFrame {

    public final String createAppointmentPanelName = "createAppointmentPanel";
    public final String viewAppointmentPanelName = "viewAppointmentPanel";
    public final String registerPanelName = "registerPanel";

    //registration
    private JTextField      regName;
    private JTextField      regEmail;
    private JPasswordField  regPassword;
    private JTextField      regYearOfBirth;
    private JRadioButton    regMale;
    private JRadioButton    regFemale;
    private JTextField      regWeight;
    private JRadioButton    regNoActivities;
    private JRadioButton    regSomeActivities;
    private JRadioButton    regNumerousActivities;
    private JRadioButton    regDietNo;
    private JRadioButton    regDietYes;
    private JTextArea       regIllness;
    private JTextArea       regAllergies;
    private JMenuItem       register;
    private JMenu           appointmentMenu;
    private JButton         registerSubmit;

    //create appointment
    private JDatePicker datePicker;
    private JTextArea comment;

    //view appointment
    JTextArea appointmentReport;

    public PatientFrame()
    {
        JMenu registerMenu = new JMenu("register");
        registerMenu.setMnemonic('r');
        menuBar.add(registerMenu);

        //creating set message menu item
        register = new JMenuItem("Register");
        register.setMnemonic('r');
        registerMenu.add(register);
        register.addActionListener(new RegisterActionListener(this));

        appointmentMenu = new JMenu("Appointment");
        appointmentMenu.setMnemonic('a');
        menuBar.add(appointmentMenu);
        appointmentMenu.setEnabled(false);//start disabled till login

        //creating set message menu item
        JMenuItem creatAppointment = new JMenuItem("Create Appointment");
        creatAppointment.setMnemonic('c');
        appointmentMenu.add(creatAppointment);
        creatAppointment.addActionListener(new CreateAppointmentActionListener(this));

        JMenuItem viewAppointment = new JMenuItem("View Appointment");
        viewAppointment.setMnemonic('v');
        appointmentMenu.add(viewAppointment);
        viewAppointment.addActionListener(new ViewAppointmentActionListener(this));

        JPanel registerPanel = registerCard();
        mainPanel.add(registerPanelName, registerPanel);
        JPanel appointmentPanel = createAddAppointmentPanel();
        mainPanel.add(createAppointmentPanelName, appointmentPanel);

        JPanel viewAppointmentPanel = viewAppointmentCard();
        mainPanel.add(viewAppointmentPanelName, viewAppointmentPanel);

       //loginSubmit.addActionListener(new PatientLoginActionListener(this));
    }

    private JPanel viewAppointmentCard() {
        JPanel myPanel = new JPanel(new GridLayout(2,1));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(new JLabel("title"));
        myPanel.add(titlePanel);

        JPanel reportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        appointmentReport = new JTextArea(5,60);
        appointmentReport.setEditable(false);
        reportPanel.add(appointmentReport);
        appointmentReport.setLineWrap(true);
        appointmentReport.setWrapStyleWord(true);
        myPanel.add(reportPanel);


        return myPanel;

    }

    public void getData()
    {
        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "SELECT \n" +
                    "    `appointment`.`app_year`,\n" +
                    "    `appointment`.`app_month`,\n" +
                    "    `appointment`.`app_day`,\n" +
                    "    `appointment`.`patient_comment`,\n" +
                    "    `appointment`.`nurse_comment`, " +
                    "     `appointment`.`status` \n" +
                    "  FROM `cs_medical_app`.`appointment`" +
                    "  WHERE `appointment`.`email` = ?\n";
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, email);


            ResultSet resultSet = s.executeQuery();
            ArrayList<Appointment> list = new ArrayList<>();
            while(resultSet.next())
            {
                Appointment app = new Appointment();
                list.add(app);
                app.setYear(resultSet.getInt("app_year"));
                app.setMonth(resultSet.getInt("app_month"));
                app.setDay(resultSet.getInt("app_day"));
                app.setPatientComment(resultSet.getString("patient_comment"));
                app.setNurseComment(resultSet.getString("nurse_comment"));
                app.setStatus(resultSet.getInt("status"));
            }

            this.appointmentReport.setText("");
            String newLine = System.getProperty("line.separator");

            for(Appointment app: list)
            {
                String reportStr =newLine+app.getDay()+"\\"+app.getMonth()+"\\"+ app.getYear()
                        +", status "+app.getStatusStr();
                if((app.getPatientComment() != null)&&app.getPatientComment().length()>0)
                    reportStr+=", your comment "+app.getPatientComment();
                if((app.getNurseComment()!= null)&&app.getNurseComment().length()>0)
                    reportStr+=", nurse comment "+app.getNurseComment();
                this.appointmentReport.append(reportStr);
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


    public JPanel registerCard() {
        JPanel myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(11,1));
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(namePanel);
        namePanel.add(new JLabel("Name"));
        regName = new JTextField(40);
        namePanel.add(regName);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(emailPanel);
        emailPanel.add(new JLabel("Email"));
        regEmail = new JTextField(40);
        emailPanel.add(regEmail);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(passwordPanel);
        passwordPanel.add(new JLabel("Password"));
        regPassword = new JPasswordField(40);
        passwordPanel.add(regPassword);

        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(yearPanel);
        yearPanel.add(new JLabel("Year Of Birth"));
        regYearOfBirth = new JTextField(40);
        yearPanel.add(regYearOfBirth);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(genderPanel);
        ButtonGroup genderGroup = new ButtonGroup();
        genderPanel.add(new JLabel("Gender"));
        regMale = new JRadioButton("Male");
        genderPanel.add(regMale);
        regFemale = new JRadioButton("Female");
        genderPanel.add(regFemale);
        genderGroup.add(regMale);
        genderGroup.add(regFemale);

        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(weightPanel);
        weightPanel.add(new JLabel("Weight"));
        regWeight = new JTextField(40);
        weightPanel.add(regWeight);

        JPanel activityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(activityPanel);
        ButtonGroup activityGroup = new ButtonGroup();
        activityPanel.add(new JLabel("Activity"));
        regNoActivities = new JRadioButton("No");
        activityGroup.add(regNoActivities);
        activityPanel.add(regNoActivities);
        regSomeActivities = new JRadioButton("Some");
        activityGroup.add(regSomeActivities);
        activityPanel.add(regSomeActivities);
        regNumerousActivities = new JRadioButton("Numerous");
        activityGroup.add(regNumerousActivities);
        activityPanel.add(regNumerousActivities);

        JPanel dietPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(dietPanel);
        ButtonGroup dietGroup = new ButtonGroup();
        dietPanel.add(new JLabel("Diet"));
        regDietNo = new JRadioButton("No");
        dietGroup.add(regDietNo);
        dietPanel.add(regDietNo);
        regDietYes = new JRadioButton("Yes");
        dietGroup.add(regDietYes);
        dietPanel.add(regDietYes);

        JPanel illnessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(illnessPanel);
        illnessPanel.add(new JLabel("Illness"));
        regIllness = new JTextArea(5,30);
        illnessPanel.add(regIllness);

        JPanel allergiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        myPanel.add(allergiesPanel);
        allergiesPanel.add(new JLabel("Allergies"));
        regAllergies = new JTextArea(5,30);
        allergiesPanel.add(regAllergies);



        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        myPanel.add(submitPanel);
        registerSubmit = new JButton("Register");
        submitPanel.add(registerSubmit);

        registerSubmit.addActionListener(new RegisterPatientActionListener(this));

        return myPanel;

    }

    public JPanel createAddAppointmentPanel() {
        JPanel myPanel = new JPanel();
        JPanel appointmentPanel = new JPanel();
        appointmentPanel.setLayout(new GridLayout(5,1));

        JPanel date = new JPanel(new FlowLayout(FlowLayout.CENTER));
        date.add(new JLabel("Choose Date"));
        appointmentPanel.add(date);

        datePicker = new JDatePicker();
        JPanel commentStringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        commentStringPanel.add(datePicker);
        appointmentPanel.add(commentStringPanel);

        JPanel dateChooser = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dateChooser.add(new JLabel("Comment"));
        appointmentPanel.add(dateChooser);

        JPanel commentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        comment = new JTextArea(5,30);
        commentPanel.add(comment);
        appointmentPanel.add(commentPanel);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginSubmit = new JButton("Submit");
        submitPanel.add(loginSubmit);
        appointmentPanel.add(submitPanel);

        loginSubmit.addActionListener(new CreateAppointmentButtonActionListener(this));

        myPanel.add(appointmentPanel);

        return myPanel;

    }

    public void createAppointment()
    {
        int day = this.datePicker.getModel().getDay();
        int month = this.datePicker.getModel().getMonth()+1;
        int year = this.datePicker.getModel().getYear();
        String comment = this.comment.getText();
        System.out.println(day+"\\"+month+"\\"+year+"\n"+comment);

        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "INSERT INTO `cs_medical_app`.`appointment`\n" +
                    "(`email`,\n" +
                    "`app_year`,\n" +
                    "`app_month`,\n" +
                    "`app_day`,\n" +
                    "`patient_comment`," +
                    "`status`)\n" +
                    "VALUES\n" +
                    "(?,?,?,?,?,?)";
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, this.email);
            s.setInt(2, year);
            s.setInt(3, month);
            s.setInt(4, day);
            s.setString(5, comment);
            s.setInt(6, 1);

            s.executeUpdate();
            JOptionPane.showMessageDialog(this, "Appointment Submitted", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
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

    public void registerPatient()
    {
        String name = this.regName.getText();
        String email = this.regEmail.getText();
        String password = new String(this.regPassword.getPassword());
        int gender = 0;
        if(this.regMale.isSelected() == true)
        {
            gender = 2;
        }
        if(this.regFemale.isSelected() == true)
        {
            gender = 1;
        }
        String weight = this.regWeight.getText();
        int activities = 0;
        if(this.regNumerousActivities.isSelected() == true)
        {
            activities = 3;
        }
        if(this.regSomeActivities.isSelected() == true)
        {
            activities = 2;
        }
        if(this.regNoActivities.isSelected() == true)
        {
            activities = 1;
        }


        int diet =0;
        if(this.regDietYes.isSelected() == true)
        {
            diet = 1;
        }
        if(this.regDietNo.isSelected() == true)
        {
            diet = 0;
        }
        String illness = this.regIllness.getText();
        String allergy = this.regAllergies.getText();
        String yearOfBirth= this.regYearOfBirth.getText();

        String validateErrorMsg ="";

        if(name.trim().length() ==0)
        {
            validateErrorMsg += "Please enter a name\n";
        }

        try
        {
            int year = Integer.parseInt(yearOfBirth);
            if(year<1900 || year>2021)
            {
                validateErrorMsg += "Birth year must be between 1900 and 2021\n";
            }
        }
        catch (Exception e)
        {
            validateErrorMsg += "Please enter a valid year of birth\n";
        }
        if(validateErrorMsg.length()>0)//in case of error
        {
            JOptionPane.showMessageDialog(this, validateErrorMsg, "Form Error", JOptionPane.ERROR_MESSAGE);
            return; //stop method don't save to db
        }

//(String name, String email, String password,
//                   int gender, String weight, int activities,
//                   int diet, String illness, String allergy)

        Patient patient = new Patient(name, email, password, gender, weight, activities,diet, illness, allergy, yearOfBirth);


        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "INSERT INTO `cs_medical_app`.`patient`\n" +
                    "(`email`,\n" +
                    "`password`,\n" +
                    "`name`,\n" +
                    "`year_of_birth`,\n" +
                    "`gender`,\n" +
                    "`weight`,\n" +
                    "`activities`,\n" +
                    "`diet`,\n" +
                    "`illness`,\n" +
                    "`allergy`)\n" +
                    "VALUES\n" +
                    "(?,?,?,?,?,?,?,?,?,?);\n";
            PreparedStatement s = con.prepareStatement(sql);
            s.setString(1, patient.getEmail());
            s.setString(2, patient.getPassword());
            s.setString(3, patient.getName());
            s.setString(4, patient.getYearOfBirth());
            s.setInt(5, patient.getGender());
            s.setString(6, patient.getWeight());
            s.setInt(7, patient.getActivities());
            s.setInt(8, patient.getDiet());
            s.setString(9, patient.getIllness());
            s.setString(10, patient.getAllergy());


            s.executeUpdate();

            //if everyting is OK, start the app
            this.login.setEnabled(false);
            this.register.setEnabled(false);
            this.appointmentMenu.setEnabled(true);
            this.email = patient.getEmail();
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

    @Override
    public void processLoginButton() {
        String email = this.logEmail.getText();
        String password = this.logPassword.getText();

        Connection con = null;
        try {

            Class.forName(Constant.driverName).newInstance();
            con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPass);
            String sql = "SELECT " +
                    "    `patient`.`name`\n" +
                    " FROM `cs_medical_app`.`patient`" +
                    " WHERE `patient`.`email`=? " +
                    " AND `patient`.`password`= ?";
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
                this.register.setEnabled(false);
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


}
