package be.vtir.bot.util;

import be.vtir.bot.Main;
import be.vtir.bot.commands.staff.WarnCommand;
import be.vtir.bot.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AutoModerator {

    public static void handle(Message msg, boolean edit) {
        antiSwear(msg);
    }

    static void antiSwear(Message msg) {
        String content = msg.getContentRaw().toLowerCase();
        for (String word : Settings.ANTI_SWEAR_WORDS) {
            if (content.contains(word)) {
                msg.delete().queue();
                msg.getChannel().sendMessage(msg.getAuthor().getAsMention() + ", Schelden is niet toegestaan!").queue(mes -> mes.delete().queueAfter(1, TimeUnit.MINUTES));
                Cache.OVERIGELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(msg.getAuthor().getAsMention() + " heeft gescholden.\n**Bericht:** `" + msg.getContentRaw() + "`", "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();
            }
        }
    }
}
