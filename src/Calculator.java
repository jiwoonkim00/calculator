import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextArea area;
    private StringBuilder currentInput = new StringBuilder();
    private double result = 0;
    private String operator = "";
    // 생성자 정의
    public Calculator() {
        setTitle("Calculator");
        setLayout(new BorderLayout());  // 전체 레이아웃을 BorderLayout으로 설정

        showNorth();  // 북쪽 패널 추가
        showCenter(); // 중앙 버튼 패널 추가

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400); // 높이 조정
        setVisible(true);
    }

    // 북쪽 패널에 JTextArea 추가
    void showNorth() {
        JPanel panel = new JPanel();
        area = new JTextArea(4, 20);
        area.setEditable(false);  // 수정 불가
        area.setLineWrap(true);   // 텍스트 줄 바꿈 허용
        area.setWrapStyleWord(true);  // 단어 단위로 줄 바꿈

        // JTextArea를 JScrollPane으로 감싸기
        JScrollPane scrollPane = new JScrollPane(area);
        panel.add(scrollPane);  // 패널에 스크롤 패인 추가

        add(panel, BorderLayout.NORTH); // 북쪽에 패널 추가
    }

    // 중앙에 버튼 패널 추가
    void showCenter() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));  // 5x4 그리드 레이아웃 설정

        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=" // 0과 . 각각 한 칸 차지
        };

        // 버튼 추가
        for (String text : buttons) {
            JButton button = new JButton(text); //버튼 생성

            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);

        }

        // JComboBox 추가
        String[] calculatorTypes = { "기본 계산기", "공학용 계산기", "프로그래머용 계산기" };
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        // JComboBox 크기 조정
        comboBox.setPreferredSize(new Dimension(80, 30)); // 버튼과 비슷한 크기로 설정

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            // 선택된 계산기 유형에 대한 처리 로직 추가
            if (selected.equals("공학용 계산기")) {
                new ScientificCalculator(); // 공학용 계산기 인스턴스 생성
                dispose(); // 기본 계산기 창 닫기
            } else if (selected.equals("프로그래머용 계산기")) {
                new ProgrammerCalculator(); // 프로그래머용 계산기 인스턴스 생성
                dispose(); // 기본 계산기 창 닫기
            }
            System.out.println("선택된 계산기: " + selected);
        });

        // 버튼 패널에 JComboBox 추가
        buttonPanel.add(comboBox); // 마지막 칸에 추가

        add(buttonPanel, BorderLayout.CENTER); // 중앙에 버튼 패널 추가
    }
    //버튼 클릭 리스너(기본 계산기)
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command){
                case "C":
                    currentInput.setLength(0);
                    result = 0;
                    operator="";
                    area.setText("0");
                    break;
                case "=":
                    calculate();
                    break;
                case "+":
                case "-":
                case "x":
                case "/":
                    if (currentInput.length() > 0) {
                        operator = command.equals("x") ? "*" : command; // 'x'를 '*'로 변환
                        result = Double.parseDouble(currentInput.toString());
                        area.append(result + " " + operator + " "); // 계산 과정 표시
                        currentInput.setLength(0); // 입력 초기화
                    }
                    break;
                default:
                    currentInput.append(command); // 버튼 텍스트를 입력에 추가
                    area.setText(currentInput.toString()); // 현재 입력 표시
                    break;
            }
        }
    }
    // 계산 로직
    private void calculate() {
        if (operator.isEmpty() || currentInput.length() == 0) return;

        double value = Double.parseDouble(currentInput.toString());

        switch (operator) {
            case "+":
                result += value;
                break;
            case "-":
                result -= value;
                break;
            case "*":
                result *= value;
                break;
            case "/":
                if (value != 0) {
                    result /= value;
                } else {
                    area.setText("Error");
                    currentInput.setLength(0);
                    return;
                }
                break;
            default:
                break;
        }
        area.append(value + " = " + result + "\n"); //최종결과 표시
        currentInput.setLength(0); //입력 초기화
        operator = ""; //연산자 초기화
    }

    // 메인 메서드
    public static void main(String[] args) {
        new Calculator();  // Calculator 객체 생성
    }
}

// 공학용 계산기 클래스
class ScientificCalculator extends JFrame {

    public ScientificCalculator() {
        setTitle("공학용 계산기");
        setLayout(new BorderLayout());

        // JTextArea 추가
        JTextArea area = new JTextArea(4, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.NORTH);

        // 버튼 패널 추가
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=",
                "sin", "cos", "tan"  // 추가 버튼
        };

        for (String text : buttons) {
            buttonPanel.add(new JButton(text));  // 버튼 패널에 추가
        }

        // JComboBox 추가
        String[] calculatorTypes = { "기본 계산기", "프로그래머용 계산기", "공학용 계산기" };
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            // 선택된 계산기 유형에 대한 처리 로직 추가
            if (selected.equals("기본 계산기")) {
                new Calculator(); // 기본 계산기 인스턴스 생성
                dispose(); // 공학용 계산기 창 닫기
            } else if (selected.equals("프로그래머용 계산기")) {
                new ProgrammerCalculator(); // 프로그래머용 계산기 인스턴스 생성
                dispose(); // 공학용 계산기 창 닫기
            }
            System.out.println("선택된 계산기: " + selected);
        });

        // 버튼 패널에 JComboBox 추가
        buttonPanel.add(comboBox); // 마지막 칸에 추가

        add(buttonPanel, BorderLayout.CENTER);

        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// 프로그래머용 계산기 클래스
class ProgrammerCalculator extends JFrame {

    public ProgrammerCalculator() {
        setTitle("프로그래머용 계산기");
        setLayout(new BorderLayout());

        // JTextArea 추가
        JTextArea area = new JTextArea(4, 24);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.NORTH);

        // 버튼 패널 추가
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttons = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=",
                "AND", "OR", "XOR", "NOT"  // 비트 연산 추가
        };

        for (String text : buttons) {
            buttonPanel.add(new JButton(text));  // 버튼 패널에 추가
        }

        // JComboBox 추가
        String[] calculatorTypes = { "기본 계산기", "공학용 계산기", "프로그래머용 계산기" };
        JComboBox<String> comboBox = new JComboBox<>(calculatorTypes);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            // 선택된 계산기 유형에 대한 처리 로직 추가
            if (selected.equals("기본 계산기")) {
                new Calculator(); // 기본 계산기 인스턴스 생성
                dispose(); // 프로그래머용 계산기 창 닫기
            } else if (selected.equals("공학용 계산기")) {
                new ScientificCalculator(); // 공학용 계산기 인스턴스 생성
                dispose(); // 프로그래머용 계산기 창 닫기
            }
            System.out.println("선택된 계산기: " + selected);
        });

        // 버튼 패널에 JComboBox 추가
        buttonPanel.add(comboBox); // 마지막 칸에 추가

        add(buttonPanel, BorderLayout.CENTER);

        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
