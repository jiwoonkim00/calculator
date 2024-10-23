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
        setSize(300, 350);
        setVisible(true);
    }

    // 북쪽 패널에 JTextArea 추가
    void showNorth() {
        JPanel panel = new JPanel();
        JTextArea area = new JTextArea(3, 20);
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
                "0", "0", ".", "="
        };

        for (String text : buttons) {
            buttonPanel.add(new JButton(text));  // 버튼 패널에 추가
        }

        add(buttonPanel, BorderLayout.CENTER); // 중앙에 버튼 패널 추가
    }

    // 메인 메서드
    public static void main(String[] args) {
        new Calculator();  // Calculator 객체 생성
    }
}
