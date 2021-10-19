package eg.com.uofcanada.medical.common;

import javax.swing.*;
import java.awt.*;

public abstract class BasicFrame extends JFrame {

    //protected to be accessible in sub-classes
    protected JPanel mainPanel;
    protected JMenuBar menuBar;
    protected JMenuItem login;
    protected CardLayout cardLayout;

    public final String loginPanelName = "loginPanel";
    public final String welcomePanelName = "welcomePanel";

    //login

    protected JTextField logEmail;
    protected JTextField logPassword;
    protected JButton loginSubmit;

    protected String email;

    public BasicFrame()
    {
        setSize(700, 600);
        this.addWindowListener(new CommonWindowAdapter());

        //creating menu Bar
        this.menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //creating 2nd menuu
        JMenu loginMenu = new JMenu("Login");
        loginMenu.setMnemonic('l');
        menuBar.add(loginMenu);


        //creating login sub menu
        login = new JMenuItem("login");
        login.setMnemonic('l');
        loginMenu.add(login);
        login.addActionListener(new BasicLoginActionListener(this));

        JMenuItem exit = new JMenuItem("exit");
        exit.setMnemonic('e');
        loginMenu.add(exit);
        exit.addActionListener(new ExitActionListener());

        this.mainPanel = new JPanel();
        this.add(this.mainPanel);
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        JPanel welcomePanel = createWelcomeCard();
        mainPanel.add(welcomePanelName,welcomePanel);

        JPanel loginPanel = createLoginCard();
        mainPanel.add(loginPanelName, loginPanel);

        this.cardLayout.show(this.mainPanel, welcomePanelName);
        setVisible(true);
    }

    public JPanel createWelcomeCard() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Welcome to the Medical App"));
        return panel;

    }

    public JPanel createLoginCard() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3,1));
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Email"));
        logEmail = new JTextField(30);
        namePanel.add(logEmail);
        loginPanel.add(namePanel);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Password"));
        logPassword = new JTextField(30);
        passwordPanel.add(logPassword);
        loginPanel.add(passwordPanel);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        loginSubmit = new JButton("OK");
        submitPanel.add(loginSubmit);
        loginPanel.add(submitPanel);
        loginSubmit.addActionListener(new LoginButtonActionListener(this));

        return loginPanel;

    }

    public void showPanel(String panelName)
    {
        this.cardLayout.show(this.mainPanel, panelName);
    }

    public abstract void processLoginButton();

}
