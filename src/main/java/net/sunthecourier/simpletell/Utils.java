package net.sunthecourier.simpletell;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    public static String[] reparseArguments(String[] args) {
        String str = String.join(" ", args);
        List<String> list = new ArrayList<>(args.length);
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
        while (m.find())
            list.add(m.group(1).replace("\"", ""));

        return list.toArray(new String[0]);
    }

    private static final Map<String, String> colorCodes = Arrays.stream(ChatColor.values()).
            collect(Collectors.toMap(chatColor ->
                    "&" + chatColor.getChar(), chatColor ->
                    "§" + chatColor.getChar()));

    public static String parseColors(String str) {
        String res = str;
        for (Map.Entry<String, String> entry : colorCodes.entrySet()) {
            res = res.replace(entry.getKey(), entry.getValue());
        }
        return res;
    }
}
