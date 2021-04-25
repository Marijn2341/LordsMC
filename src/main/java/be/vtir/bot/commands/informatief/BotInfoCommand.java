package be.vtir.bot.commands.informatief;

import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.Utils;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;

import java.time.format.DateTimeFormatter;

public class BotInfoCommand extends Command {
    public BotInfoCommand() {
        super("botinfo", null, "Zie de bot info", "bi");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //Time formatter
        msg.getChannel().sendMessage(Embed.footerenthumbnail("**Bot prefix:** `" + Settings.PREFIX + "`\n**Bot versie:** `1.0`\n" +
                "**Aangemaakt op:** `" + msg.getJDA().getSelfUser().getTimeCreated().format(format) + "`\n" + "**Developer:** <@287195080748433419>", "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();
    }
}
