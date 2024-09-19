package ir.tamin.infra.framework.util.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by s_tayari on 1/6/2019.
 */
public class Text {

    private String string;

    public Text(String string, String... params) {
        Pattern pattern = Pattern.compile("(\\$[^\\W]+)");
        Matcher matcher = pattern.matcher(string);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            matcher.appendReplacement(sb, params[i]);
            i++;
        }

        matcher.appendTail(sb);
        this.string = sb.toString();
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
