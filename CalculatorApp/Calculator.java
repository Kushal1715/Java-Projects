import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton optButton[] = new JButton[9];
    JButton numButton[] = new JButton[10];
    JButton addButton, subButton, mulButton, divButton, decButton, clrButton, negButton, eqButton, delButton;
    Font font = new Font("Ink Free", Font.BOLD, 30);
    JPanel panel;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Kushal's Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setEditable(false);
        textField.setFont(font);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
        eqButton = new JButton("=");
        delButton = new JButton("Del");

        negButton.setBounds(50, 430, 100, 50);
        negButton.setFont(font);
        negButton.setFocusable(false);
        delButton.setBounds(150, 430, 100, 50);
        delButton.setFocusable(false);
        delButton.setFont(font);
        clrButton.setBounds(250, 430, 100, 50);
        clrButton.setFont(font);
        clrButton.setFocusable(false);

        optButton[0] = addButton;
        optButton[1] = subButton;
        optButton[2] = mulButton;
        optButton[3] = divButton;
        optButton[4] = decButton;
        optButton[5] = clrButton;
        optButton[6] = negButton;
        optButton[7] = eqButton;
        optButton[8] = delButton;

        for (int i = 0; i < 9; i++) {
            optButton[i].addActionListener(this);
            optButton[i].setFocusable(false);
            optButton[i].setFont(font);
        }

        for (int i = 0; i < 10; i++) {
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].addActionListener(this);
            numButton[i].setFont(font);
            numButton[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numButton[1]);
        panel.add(numButton[2]);
        panel.add(numButton[3]);
        panel.add(addButton);
        panel.add(numButton[4]);
        panel.add(numButton[5]);
        panel.add(numButton[6]);
        panel.add(subButton);
        panel.add(numButton[7]);
        panel.add(numButton[8]);
        panel.add(numButton[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numButton[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new Calculator();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButton[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == eqButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;

                case '-':
                    result = num1 - num2;
                    break;

                case '*':
                    result = num1 * num2;
                    break;

                case '/':
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }

    }
}