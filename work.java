import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// แก้ไขชื่อคลาสให้ตรงกับชื่อไฟล์ work.java
public class work extends JFrame implements ActionListener {

    JTextField display;
    String expression = "";

    public work() {
        setTitle("Simple Calculator");
        setSize(300, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ช่องแสดงผล
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT); 
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setPreferredSize(new Dimension(300, 60));
        add(display, BorderLayout.NORTH);

        // ส่วนของปุ่ม
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "%", "=", "+",
                "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("C")) {
            expression = "";
            display.setText("");
        } else if (cmd.equals("=")) {
            if (!expression.isEmpty()) {
                try {
                    double result = calculate(expression);
                    if (result % 1 == 0) {
                        display.setText(String.valueOf((int) result));
                    } else {
                        display.setText(String.valueOf(result));
                    }
                    expression = display.getText();
                } catch (Exception ex) {
                    display.setText("Error");
                    expression = "";
                }
            }
        } else {
            expression += cmd;
            display.setText(expression);
        }
    }

    public double calculate(String exp) {
        exp = exp.replace("%", "/100");
        String[] tokens = exp.split("(?<=[-+*/])|(?=[-+*/])");
        
        if (tokens.length < 1) return 0;
        if (tokens.length < 3) return Double.parseDouble(tokens[0]);

        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i];
            double num = Double.parseDouble(tokens[i + 1]);

            switch (op) {
                case "+": result += num; break;
                case "-": result -= num; break;
                case "*": result *= num; break;
                case "/": 
                    if (num == 0) throw new ArithmeticException("Divide by zero");
                    result /= num; 
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // แก้ไขชื่อ constructor ให้ตรงกับชื่อคลาสใหม่
        SwingUtilities.invokeLater(() -> new work());
    }
}