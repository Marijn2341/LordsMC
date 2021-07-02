package be.lordsmc.bot.util;

import be.lordsmc.bot.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.regex.Pattern;

public class Cache {
    public static void init() {
        JDA jda = Main.getJda();
        MEMBER_ROLE = jda.getRoleById(Settings.MEMBER_ROLE);

        WELCOME_CHANNEL = jda.getTextChannelById(Settings.WELCOME_CHANNEL);
        BOT_CHANNEL = jda.getTextChannelById(Settings.BOT_CHANNEL);
        STRAFFENLOGS_CHANNEL = jda.getTextChannelById(Settings.STRAFFENLOGS_CHANNEL);
        JOINLEAVELOGS_CHANNEL = jda.getTextChannelById(Settings.JOINLEAVELOGS_CHANNEL);
        OVERIGELOGS_CHANNEL = jda.getTextChannelById(Settings.OVERIGELOGS_CHANNEL);
        TICKET_LOG_CHANNEL = jda.getTextChannelById(Settings.TICKET_L0G_CHANNEL);
        CREATE_VOICE_CHANNEL = jda.getVoiceChannelById(Settings.CREATE_VOICE_CHANNEL);

        TICKET_CATEGORY = jda.getCategoryById(Settings.TICKET_CATEGORY);
        VOICE_CATEGORY = jda.getCategoryById(Settings.VOICE_CATEGORY);
    }

    public static Role MEMBER_ROLE;

    public static TextChannel WELCOME_CHANNEL;
    public static TextChannel BOT_CHANNEL;
    public static TextChannel STRAFFENLOGS_CHANNEL;
    public static TextChannel JOINLEAVELOGS_CHANNEL;
    public static TextChannel OVERIGELOGS_CHANNEL;
    public static TextChannel TICKET_LOG_CHANNEL;

    public static VoiceChannel CREATE_VOICE_CHANNEL;

    public static Category TICKET_CATEGORY;
    public static Category VOICE_CATEGORY;

    public static Pattern DISCORD_LINK_REGEX = Pattern.compile("(https?://)?(www\\.)?(discord(app)?\\.com/invite|" +
            "discord\\.gg)/[a-zA-Z0-9]+");
    public static Pattern LINK_REGEX = Pattern.compile("(https?://)?(www)*\\w+\\.[a-zA-Z]{2,4}/?\\w*");
}
