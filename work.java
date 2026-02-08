import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    JTextField display;
    String expression = "";

    public SimpleCalculator() {

        setTitle("Simple Calculator");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ช่องแสดงกระบวนการ
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display, BorderLayout.NORTH);

        // ปุ่มทั้งหมด
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String buttons[] = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","%","=","+",
                "C"
        };

        for(String text : buttons){
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // เมื่อกดปุ่ม
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if(cmd.equals("C")) {
            expression = "";
            display.setText("");

        } else if(cmd.equals("=")) {
            try {
                double result = calculate(expression);
                display.setText(expression + " = " + result);
                expression = String.valueOf(result);
            } catch(Exception ex) {
                display.setText("Error");
                expression = "";
            }

        } else {
            expression += cmd;
            display.setText(expression);
        }
    }

    // ฟังก์ชันคำนวณ
    public double calculate(String exp) {

        exp = exp.replace("%", "/100");

        String[] tokens = exp.split("(?=[-+*/])|(?<=[-+*/])");

        double result = Double.parseDouble(tokens[0]);

        for(int i=1;i<tokens.length;i+=2) {

            String op = tokens[i];
            double num = Double.parseDouble(tokens[i+1]);

            switch(op) {
                case "+": result += num; break;
                case "-": result -= num; break;
                case "*": result *= num; break;
                case "/": result /= num; break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}