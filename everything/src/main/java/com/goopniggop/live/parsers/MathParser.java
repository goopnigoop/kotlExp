package com.goopniggop.live.parsers;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.LongBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathParser implements Parser<Long> {
    private static final String REGEXP = "^(?<first>^\\d+)\\s*(?<action>[+*/-])\\s*(?<second>\\d+)";
    private static final Pattern pattern = Pattern.compile(REGEXP);

    private static final EnumMap<Action, LongBinaryOperator> mapWithActions = new EnumMap<>(Action.class);

    static {
        mapWithActions.put(Action.PLUS, Long::sum);
        mapWithActions.put(Action.MINUS, (a, b) -> a - b);
        mapWithActions.put(Action.MULTIPLY, (a, b) -> a * b);
        mapWithActions.put(Action.DIVIDE, (a, b) -> a / b);
    }

    @Override
    public Long parse(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("The input is empty string");
        }
        final Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Wrong input string");
        }

        final long first = Long.parseLong(matcher.group("first"));
        final String sign = matcher.group("action");
        final long second = Long.parseLong(matcher.group("second"));
        final LongBinaryOperator longBinaryOperator = mapWithActions.get(Action.getBySign(sign));
        return longBinaryOperator.applyAsLong(first, second);
    }

    private enum Action {
        PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");
        private final String sign;

        Action(String sign) {
            this.sign = sign;
        }

        public static Action getBySign(String sign) {
            return Arrays.stream(Action.values()).filter(action -> action.sign.equals(sign)).findAny()
                         .orElseThrow(() -> new IllegalArgumentException("Action from the input is wrong"));
        }
    }

}
