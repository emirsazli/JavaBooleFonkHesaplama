package _24010310095_Emircan_Sazli;

import java.util.*;

public class _24010310095_BooleanFunctionParser {
    private List<List<String>> terms;
    private Set<String> variables;

    public void parseFunction(String function) {
        terms = new ArrayList<>();
        variables = new TreeSet<>();

        String[] split = function.split("=");
        String expression = split[1].trim().replace(" ", "");

        for (String term : expression.split("\\+")) {
            List<String> literals = new ArrayList<>();
            for (int i = 0; i < term.length(); i++) {
                char ch = term.charAt(i);
                if (Character.isLetter(ch)) {
                    if (i + 1 < term.length() && term.charAt(i + 1) == '\'') {
                        literals.add(ch + "'");
                        i++;
                    } else {
                        literals.add(String.valueOf(ch));
                    }
                    variables.add(String.valueOf(ch));
                }
            }
            terms.add(literals);
        }
    }

    public List<List<String>> getTerms() {
        return terms;
    }

    public Set<String> getVariables() {
        return variables;
    }
}

