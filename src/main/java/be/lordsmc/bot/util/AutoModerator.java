package be.lordsmc.bot.util;

import net.dv8tion.jda.api.entities.Message;

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
                Cache.OVERIGELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(msg.getAuthor().getAsMention() + " heeft gescholden.\n**Bericht:** `" + msg.getContentRaw() + "`", "© LordsMC", msg.getGuild().getIconUrl())).queue();
            }
        }
    }
}
