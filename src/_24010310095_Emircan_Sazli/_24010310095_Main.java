package _24010310095_Emircan_Sazli;

import java.io.*;  // Dosya işlemleri için gerekli importlar
import java.util.*;

public class _24010310095_Main {
    public static void main(String[] args) {
        try {
            // Dosyayı okuma işlemi
            File file = new File("boole.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();

            _24010310095_BooleanFunctionParser parser = new _24010310095_BooleanFunctionParser();
            parser.parseFunction(line);

            _24010310095_CircuitBuilder builder = new _24010310095_CircuitBuilder(parser.getTerms(), parser.getVariables());
            builder.buildCircuit("F");

            builder.printCircuitInfo();

            // Kullanıcıdan A, B, C değerlerini alma
            Scanner scanner = new Scanner(System.in);
            for (String var : parser.getVariables()) {
                // Yeşil renk için ANSI escape kodu kullanalım
                System.out.print("\033[0;92m" + var + " için bir değer girin: (0/1) \033[0m"); // Açık yeşil renk, sonra normal renk
                int value = scanner.nextInt();
                
                // Girilen değeri kontrol et
                if (value != 0 && value != 1) {
                    System.out.println("Geçersiz giriş. Lütfen 0 veya 1 girin.");
                    return; // Geçersiz giriş olduğu için programı sonlandırıyoruz
                }
                builder.setVariableValue(var, value == 1); // Değeri boolean olarak ayarla
            }

            boolean result = builder.evaluateCircuit();
            System.out.println("Sonuç: " + (result ? "1" : "0"));
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
