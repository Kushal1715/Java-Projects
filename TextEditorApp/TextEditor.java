import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;

public class TextEditor extends JFrame implements ActionListener {

    JLabel fontLabel;
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSpinner;
    JButton colorButton;
    JComboBox fontComboBox;
    JMenu menu;
    JMenuBar menuBar;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    TextEditor() {

        colorButton = new JButton("Select a color");
        colorButton.addActionListener(this);
        colorButton.setFocusable(false);

        fontLabel = new JLabel("Font Size: ");

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontSpinner = new JSpinner();
        fontSpinner.setValue(20);
        fontSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSpinner.getValue()));
            }
        });

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontComboBox = new JComboBox(fonts);
        fontComboBox.addActionListener(this);
        fontComboBox.setSelectedItem("Arial");

        // -------MenuBar-------
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        saveItem = new JMenuItem("Save");
        saveItem.addActionListener(this);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        menu.add(openItem);
        menu.add(saveItem);
        menu.add(exitItem);
        menuBar.add(menu);

        // -------MenuBar-------

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Kushal TextEditor");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSpinner);
        this.add(colorButton);
        this.add(fontComboBox);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorButton) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(null, "Selecting a color", Color.BLACK);

            textArea.setForeground(color);
        }

        if (e.getSource() == fontComboBox) {
            textArea.setFont(
                    new Font((String) fontComboBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }

        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                    fileIn.close();
                }
            }
        }

        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (Exception e1) {
                    System.out.println("Something went wrong!!!");
                } finally {
                    fileOut.close();
                }
            }

        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

}
