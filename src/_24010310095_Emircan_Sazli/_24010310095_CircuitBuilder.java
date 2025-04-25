package _24010310095_Emircan_Sazli;

import java.util.*;

public class _24010310095_CircuitBuilder {
    private List<List<String>> terms;
    private Set<String> variables;
    private List<_24010310095_NotGate> notGates;
    private List<_24010310095_AndGate> andGates;
    private _24010310095_OrGate orGate;
    private Map<String, Boolean> variablesMap;

    public _24010310095_CircuitBuilder(List<List<String>> terms, Set<String> variables) {
        this.terms = terms;
        this.variables = variables;
        this.notGates = new ArrayList<>();
        this.andGates = new ArrayList<>();
        this.variablesMap = new HashMap<>();
    }

    public void buildCircuit(String outputName) {
        List<String> andOutputs = new ArrayList<>();

        // Her terimi işleyerek AND ve NOT kapıları oluşturuyoruz
        for (int i = 0; i < terms.size(); i++) {
            List<String> term = terms.get(i);
            List<String> andInputs = new ArrayList<>();

            for (String literal : term) {
                if (literal.endsWith("'")) { // NOT kapısı
                    String var = literal.substring(0, 1);
                    String notOutput = var + "_NOT";
                    if (!variablesMap.containsKey(notOutput)) {
                        _24010310095_NotGate notGate = new _24010310095_NotGate(var, notOutput);
                        notGates.add(notGate);
                        andInputs.add(notOutput);
                    }
                } else {
                    andInputs.add(literal); // Doğrudan girişler
                }
            }

            // AND kapısı oluşturuluyor
            String andOutput = "AND" + i;
            _24010310095_AndGate andGate = new _24010310095_AndGate(andOutput);
            for (String in : andInputs) {
                andGate.addInput(in);
            }
            andGates.add(andGate);
            andOutputs.add(andOutput); // AND kapısının çıktısı
        }

        // OR kapısını oluşturuyoruz
        orGate = new _24010310095_OrGate(outputName);
        for (String in : andOutputs) {
            orGate.addInput(in);
        }
    }

    public void printCircuitInfo() {
        if (!notGates.isEmpty()) {
            System.out.println("1. Seviye İçin:");
            System.out.println("Kapı türü: DEĞİL, " + notGates.size() + " tane kapı var");
            int i = 1;
            for (_24010310095_NotGate gate : notGates) {
                System.out.println(" " + i + ". Kapının girişi: " + gate.getInputs().get(0));
                i++;
            }
        }

        if (!andGates.isEmpty()) {
            System.out.println("2. Seviye İçin:");
            System.out.println("Kapı türü: VE, " + andGates.size() + " tane kapı var");
            int i = 1;
            for (_24010310095_AndGate gate : andGates) {
                System.out.print(" " + i + ". Kapı " + gate.getInputs().size() + "-girişli ve girişleri: ");
                System.out.println(String.join(", ", gate.getInputs()));
                i++;
            }
        }

        if (orGate != null) {
            System.out.println("3. Seviye İçin:");
            System.out.println("Kapı türü: VEYA, 1 tane kapı var");
            System.out.print(" 1. Kapı " + orGate.getInputs().size() + "-girişli ve girişleri: ");
            System.out.println(String.join(", ", orGate.getInputs()));
        }
    }

    public void setVariableValue(String var, boolean value) {
        variablesMap.put(var, value);
    }

    public boolean evaluateCircuit() {
        for (_24010310095_NotGate not : notGates) {
            not.evaluate(variablesMap);
        }
        for (_24010310095_AndGate and : andGates) {
            and.evaluate(variablesMap);
        }
        return orGate.evaluate(variablesMap);
    }
}
