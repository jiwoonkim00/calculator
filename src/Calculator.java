import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextArea area;
    private StringBuilder currentInput = new StringBuilder();
    private double result = 0;
    private String operator = "";

    public Calculator() {
        setTitle("Calculator");
        setLayout(new BorderLayout());

        showNorth();   // 입력 영역 패널
        showCenter();  // 버튼 패널
        showSouth();   // JComboBox 패널

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setVisible(true);
    }

    void showNorth() {
        JPanel panel = new JPanel();
        area = new JTextArea(4, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        panel.add(scrollPane);

        add(panel, BorderLayout.NORTH);
    }

    void showCenter() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    void showSouth() {
        JPanel comboPanel = new JPanel();
        String[] calculatorTypes = {"기본 계산기", "공학용 계산기", "프로그래머용 계산기"};
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "공학용 계산기" -> {
                    new ScientificCalculator();
                    dispose();
                }
                case "프로그래머용 계산기" -> {
                    new ProgrammerCalculator();
                    dispose();
                }
            }
        });

        comboPanel.add(comboBox);
        add(comboPanel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C" -> {
                    currentInput.setLength(0);
                    result = 0;
                    operator = "";
                    area.setText("0");
                }
                case "=" -> calculate();
                case "+", "-", "x", "/" -> {
                    if (!currentInput.isEmpty()) {
                        operator = command.equals("x") ? "*" : command;
                        result = Double.parseDouble(currentInput.toString());
                        area.setText(result + " " + operator); // 입력 초기화 후 표시
                        currentInput.setLength(0);
                    }
                }
                default -> {
                    currentInput.append(command);
                    area.setText(currentInput.toString());
                }
            }
        }
    }

    private void calculate() {
        if (operator.isEmpty() || currentInput.isEmpty()) return;

        double value = Double.parseDouble(currentInput.toString());

        switch (operator) {
            case "+" -> result += value;
            case "-" -> result -= value;
            case "*" -> result *= value;
            case "/" -> {
                if (value != 0) {
                    result /= value;
                } else {
                    area.setText("Error");
                    currentInput.setLength(0);
                    return;
                }
            }
        }
        area.setText(String.valueOf(result)); // 결과 덮어쓰기
        currentInput.setLength(0);
        operator = "";
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

class ScientificCalculator extends JFrame {
    public ScientificCalculator() {
        setTitle("공학용 계산기");
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea(4, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=",
                "sin", "cos", "tan"
        };

        for (String text : buttons) {
            buttonPanel.add(new JButton(text));
        }


        add(buttonPanel, BorderLayout.CENTER);
        showSouth();

        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void showSouth() {
        JPanel comboPanel = new JPanel();
        String[] calculatorTypes = {"기본 계산기", "공학용 계산기", "프로그래머용 계산기"};
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "기본 계산기" -> {
                    new Calculator();
                    dispose();
                }
                case "프로그래머용 계산기" -> {
                    new ProgrammerCalculator();
                    dispose();
                }
            }
        });

        comboPanel.add(comboBox);
        add(comboPanel, BorderLayout.SOUTH);
    }
}

class ProgrammerCalculator extends JFrame {
    public ProgrammerCalculator() {
        setTitle("프로그래머용 계산기");
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea(4, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=",
                "AND", "OR", "XOR", "NOT"
        };

        for (String text : buttons) {
            buttonPanel.add(new JButton(text));
        }

        add(buttonPanel, BorderLayout.CENTER);
        showSouth();

        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void showSouth() {
        JPanel comboPanel = new JPanel();
        String[] calculatorTypes = {"기본 계산기", "공학용 계산기", "프로그래머용 계산기"};
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "기본 계산기" -> {
                    new Calculator();
                    dispose();
                }
                case "공학용 계산기" -> {
                    new ScientificCalculator();
                    dispose();
                }
            }
        });

        comboPanel.add(comboBox);
        add(comboPanel, BorderLayout.SOUTH);
    }
}
