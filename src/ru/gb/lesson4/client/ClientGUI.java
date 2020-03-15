package ru.gb.lesson4.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final String SEPARATOR = System.lineSeparator();
    private static final String PATH_TO_LOGFILE = "src/ru/gb/lesson4/sources/log.txt";
    private StringBuilder logBuilder = new StringBuilder();
    private String userName;

    //Изменено под экран ноутбука 15,6
    public static final int POS_X = 550;
    public static final int POS_Y = 250;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Alex");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //   setSize(WIDTH, HEIGHT);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setTitle("Chat client");
        String[] users = {"Alex", "user2", "user3", "user4", "user5",
                "user_with_an_exceptionally_long_name_in_this_chat"};
        //Пробовал прикрутить имя к отправителю
        userName = users[0] + ": ";

        userList.setListData(users);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        FileWriter fw = new FileWriter(PATH_TO_LOGFILE);
                        //Побаловался и прикрутил имя, инициализация на 51 строке
                        fw.write(logBuilder.append(userName).append(tfMessage.getText()).append(SEPARATOR).toString());
                        fw.flush();
                        fw.close();
                        updateLogWindow(log);
                    } catch (Exception ex) {
                        ex.getMessage();
                        ex.printStackTrace();
                    }
                    //Делаем поле пустым после отправления
                    tfMessage.setText("");
                }
            }
        });

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrollLog, BorderLayout.CENTER);

        add(scrollUsers, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);

        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Определяем источник действия
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend) {
            try {
                FileWriter fw = new FileWriter(PATH_TO_LOGFILE);
                //Побаловался и прикрутил имя, инициализация на 51 строке
                fw.write(logBuilder.append(userName).append(tfMessage.getText()).append(SEPARATOR).toString());
                fw.flush();
                fw.close();
                updateLogWindow(log);
            } catch (Exception ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
            //Делаем поле пустым после отправления
            tfMessage.setText("");
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = "Exception in thread " + t.getName() + " " + e.getClass().getCanonicalName() +
                ": " + e.getMessage() + "\n\t" + ste[0];
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    public void updateLogWindow(JTextArea log) {
        try {
            FileReader fileReader = new FileReader(PATH_TO_LOGFILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder textToDisplay = new StringBuilder();
            String textFieldReadable = bufferedReader.readLine();

            while (textFieldReadable != null) {
                textToDisplay.append(textFieldReadable + SEPARATOR);
                textFieldReadable = bufferedReader.readLine();
            }
            log.setText(textToDisplay.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
