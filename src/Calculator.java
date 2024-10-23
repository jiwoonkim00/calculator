import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

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
        JTextArea area = new JTextArea(4, 24);
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
            buttonPanel.add(new JButton(text));  // 버튼 패널에 추가
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
