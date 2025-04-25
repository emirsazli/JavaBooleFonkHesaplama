package _24010310095_Emircan_Sazli;

import java.util.List;
import java.util.Map;

public class _24010310095_NotGate {
    private String input;
    private String output;

    public _24010310095_NotGate(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public List<String> getInputs() {
        return List.of(input);
    }

    public void evaluate(Map<String, Boolean> variablesMap) {
        boolean inputValue = variablesMap.get(input);
        variablesMap.put(output, !inputValue); // NOT işlemi
    }
}
