package be.vtir.bot.util;

import be.vtir.bot.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.regex.Pattern;

public class Cache {
    public static void init() {
        JDA jda = Main.getJda();
        MEMBER_ROLE = jda.getRoleById(Settings.MEMBER_ROLE);
        MINECRAFT_ROLE = jda.getRoleById(Settings.MINECRAFT_ROLE);
        CALLOFDUTY_ROLE = jda.getRoleById(Settings.CALLOFDUTY_ROLE);
        ROBLOX_ROLE = jda.getRoleById(Settings.ROBLOX_ROLE);
        FIFA_ROLE = jda.getRoleById(Settings.FIFA_ROLE);
        AMONGUS_ROLE = jda.getRoleById(Settings.AMONGUS_ROLE);
        ROCKETLEAGUE_ROLE = jda.getRoleById(Settings.ROCKETLEAGUE_ROLE);
        LEAGUEOFLEGENDS_ROLE = jda.getRoleById(Settings.LEAGUEOFLEAGENDS_ROLE);
        RCCRAWLING_ROLE = jda.getRoleById(Settings.RCCRAWLING_ROLE);
        VALORANT_ROLE = jda.getRoleById(Settings.VALORANT_ROLE);

        WELCOME_CHANNEL = jda.getTextChannelById(Settings.WELCOME_CHANNEL);
        BOT_CHANNEL = jda.getTextChannelById(Settings.BOT_CHANNEL);
        STRAFFENLOGS_CHANNEL = jda.getTextChannelById(Settings.STRAFFENLOGS_CHANNEL);
        JOINLEAVELOGS_CHANNEL = jda.getTextChannelById(Settings.JOINLEAVELOGS_CHANNEL);
        OVERIGELOGS_CHANNEL = jda.getTextChannelById(Settings.OVERIGELOGS_CHANNEL);
    }

    public static Role MEMBER_ROLE;
    public static Role MINECRAFT_ROLE;
    public static Role CALLOFDUTY_ROLE;
    public static Role ROBLOX_ROLE;
    public static Role FIFA_ROLE;
    public static Role AMONGUS_ROLE;
    public static Role ROCKETLEAGUE_ROLE;
    public static Role LEAGUEOFLEGENDS_ROLE;
    public static Role RCCRAWLING_ROLE;
    public static Role VALORANT_ROLE;

    public static TextChannel WELCOME_CHANNEL;
    public static TextChannel BOT_CHANNEL;
    public static TextChannel STRAFFENLOGS_CHANNEL;
    public static TextChannel JOINLEAVELOGS_CHANNEL;
    public static TextChannel OVERIGELOGS_CHANNEL;

    public static Pattern DISCORD_LINK_REGEX = Pattern.compile("(https?://)?(www\\.)?(discord(app)?\\.com/invite|" +
            "discord\\.gg)/[a-zA-Z0-9]+");
    public static Pattern LINK_REGEX = Pattern.compile("(https?://)?(www)*\\w+\\.[a-zA-Z]{2,4}/?\\w*");
}
