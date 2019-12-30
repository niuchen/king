package com.king.king.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.king.king.error.DevError;
import org.apache.ibatis.jdbc.AbstractSQL;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public abstract class SqlUtil {
    static final String SQL_EMPTY_SET = "(NULL)";
    private static final Pattern RE_WORD = Pattern.compile("^\\w+$");

    public SqlUtil() {
    }


    public static String toSqlString(CharSequence val) {
        if (StringUtils.isEmpty(val)) {
            throw new DevError("Parameter(val) is null or empty");
        } else {
            return escapeString(new StringBuilder(), val).toString();
        }
    }

    private static StringBuilder escapeString(StringBuilder sb, CharSequence input) {
        sb.append('\'');
        int i = 0;

        for (int n = input.length(); i < n; ++i) {
            char ch = input.charAt(i);
            sb = ch == '\'' ? sb.append("''") : sb.append(ch);
        }

        sb.append('\'');
        return sb;
    }


    public static String toSqlStringSet(Collection<? extends CharSequence> vals) {
        List<? extends CharSequence> nonNullVals = (List) vals.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return nonNullVals.isEmpty() ? "(NULL)" : (String) nonNullVals.stream().map(SqlUtil::toSqlString).collect(Collectors.joining(",", "(", ")"));
    }

    public static String toSqlNumberSet(Collection<? extends Number> vals) {
        List<? extends Number> nonNullVals = (List) vals.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return nonNullVals.isEmpty() ? "(NULL)" : (String) nonNullVals.stream().map(String::valueOf).collect(Collectors.joining(",", "(", ")"));
    }

    public static String unionAll(SQL... sqls) {
        return (String) Stream.of(sqls).map(AbstractSQL::toString).collect(Collectors.joining(" UNION ALL "));
    }

    public static String toSqlWord(String input) {
        if (!RE_WORD.matcher(input).matches()) {
            throw new IllegalArgumentException("[CORE-JDBC] Illegal inputted sql word: " + input);
        } else {
            return input;
        }
    }

    public static enum SqlLikeOption {
        BOTH_SIDES,
        LEFT_SIDE,
        RIGHT_SIDE;

        private SqlLikeOption() {
        }
    }
}
