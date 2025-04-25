package _24010310095_Emircan_Sazli;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class _24010310095_OrGate {
    private String output;
    private List<String> inputs;

    public _24010310095_OrGate(String output) {
        this.output = output;
        this.inputs = new ArrayList<>();
    }

    public void addInput(String input) {
        inputs.add(input);
    }

    public List<String> getInputs() {
        return inputs;
    }

    public String getOutput() {
        return output;
    }

    public boolean evaluate(Map<String, Boolean> variablesMap) {
        boolean result = false;
        for (String input : inputs) {
            result |= variablesMap.get(input); // OR işlemi
        }
        return result;
    }
}
