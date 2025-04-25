package _24010310095_Emircan_Sazli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainGUI extends JFrame {

    private JTextField aField, bField, cField;
    private JLabel resultLabel;
    private _24010310095_CircuitBuilder builder;

    public MainGUI() {
        setTitle("Mantıksal Devre Simülatörü");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(34, 40, 49)); // Arka plan rengi yeşil tonları

        // Giriş etiketleri ve metin kutuları
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color labelColor = new Color(200, 255, 200); // Açık yeşil tonlarında etiketler

        // A Etiketi ve TextField
        add(createLabel("A için değer (0/1):", labelFont, labelColor));
        aField = new JTextField();
        aField.setBackground(new Color(55, 71, 79)); // Koyu yeşil arka plan
        aField.setForeground(Color.WHITE); // Beyaz yazı
        aField.setHorizontalAlignment(JTextField.CENTER); // Ortalanmış yazı
        add(aField);

        // B Etiketi ve TextField
        add(createLabel("B için değer (0/1):", labelFont, labelColor));
        bField = new JTextField();
        bField.setBackground(new Color(55, 71, 79));
        bField.setForeground(Color.WHITE);
        bField.setHorizontalAlignment(JTextField.CENTER);
        add(bField);

        // C Etiketi ve TextField
        add(createLabel("C için değer (0/1):", labelFont, labelColor));
        cField = new JTextField();
        cField.setBackground(new Color(55, 71, 79));
        cField.setForeground(Color.WHITE);
        cField.setHorizontalAlignment(JTextField.CENTER);
        add(cField);

        // Hesapla butonu
        JButton calcButton = new JButton("Hesapla");
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        calcButton.setBackground(new Color(76, 175, 80)); // Yeşil buton
        calcButton.setForeground(Color.WHITE);
        calcButton.setFocusPainted(false); // Fokus rengini kapat
        calcButton.addActionListener(e -> hesapla());

        // Sonuç etiketi
        resultLabel = new JLabel("Sonuç: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setForeground(new Color(144, 238, 144)); // Açık yeşil renk

        // Layout düzeni
        add(calcButton);
        add(resultLabel);

        devreyiHazirla(); // boole.txt'yi oku ve builder kur

        setVisible(true);
    }

    private void devreyiHazirla() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("boole.txt"));
            String line = br.readLine();
            br.close();

            _24010310095_BooleanFunctionParser parser = new _24010310095_BooleanFunctionParser();
            parser.parseFunction(line);

            builder = new _24010310095_CircuitBuilder(parser.getTerms(), parser.getVariables());
            builder.buildCircuit("F");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "boole.txt dosyası okunamadı: " + ex.getMessage());
        }
    }

    private void hesapla() {
        try {
            int a = Integer.parseInt(aField.getText());
            int b = Integer.parseInt(bField.getText());
            int c = Integer.parseInt(cField.getText());

            if (a != 0 && a != 1 || b != 0 && b != 1 || c != 0 && c != 1) {
                resultLabel.setText("Hatalı giriş! 0 veya 1 girin.");
                resultLabel.setForeground(Color.RED);
                return;
            }

            builder.setVariableValue("A", a == 1);
            builder.setVariableValue("B", b == 1);
            builder.setVariableValue("C", c == 1);

            boolean sonuc = builder.evaluateCircuit();
            resultLabel.setText("Sonuç: " + (sonuc ? "1" : "0"));
            resultLabel.setForeground(new Color(144, 238, 144)); // Sonuçta yeşil renk

        } catch (NumberFormatException e) {
            resultLabel.setText("Geçersiz giriş!");
            resultLabel.setForeground(Color.RED);
        }
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT); // Etiket sağa hizalı
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
