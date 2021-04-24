package be.vtir.bot.util;

import be.vtir.bot.Main;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

public class Utils {
    public static boolean addWarns(String userId, int amount) {
        try {
            File warnsFile = new File(Settings.WARNS_PATH);
            warnsFile.getParentFile().mkdirs();
            JSONObject warnings;
            if (!warnsFile.exists()) {
                warnsFile.createNewFile();
                warnings = new JSONObject();
            } else {
                FileReader reader = new FileReader(warnsFile);
                warnings = (JSONObject) new JSONParser().parse(reader);
                reader.close();
            }

            int warnCount = warnings.containsKey(userId) ? Integer.parseInt("" + warnings.get(userId)) : 0;
            warnings.remove(userId);
            warnCount += amount;

            if (warnCount > 0) warnings.put(userId, warnCount);

            FileWriter writer = new FileWriter(warnsFile);
            writer.write(warnings.toJSONString());
            writer.close();
            return true;
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int getWarns(String userId) {
        try {
            File warnsFile = new File(Settings.WARNS_PATH);
            warnsFile.getParentFile().mkdirs();
            JSONObject warnings;
            if (!warnsFile.exists()) {
                warnsFile.createNewFile();
                warnings = new JSONObject();
            } else {
                FileReader reader = new FileReader(warnsFile);
                warnings = (JSONObject) new JSONParser().parse(reader);
                reader.close();
            }

            return warnings.containsKey(userId) ? Integer.parseInt(warnings.get(userId) + "") : 0;
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static boolean onlyContainsLink(String text) {
        return text.replaceAll(Cache.LINK_REGEX.pattern(), "").length() < 5 && text.length() >= 5;
    }

    public static void addRole(Member member, Role role) {
        member.getGuild().addRoleToMember(member, role).queue();
    }

    public static void removeRole(Member member, Role role) {
        member.getGuild().removeRoleFromMember(member, role).queue();
    }

    public static void newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
