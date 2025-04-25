package _24010310095_Emircan_Sazli;

import java.util.*;

public abstract class _24010310095_Gate {
    protected List<String> inputs = new ArrayList<>();
    protected String output;

    public void addInput(String input) {
        inputs.add(input);
    }

    public List<String> getInputs() {
        return inputs;
    }

    public String getOutput() {
        return output;
    }

    public abstract boolean evaluate(Map<String, Boolean> values);
}

