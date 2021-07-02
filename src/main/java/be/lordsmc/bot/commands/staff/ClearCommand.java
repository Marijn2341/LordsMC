package be.lordsmc.bot.commands.staff;

import be.lordsmc.bot.util.Cache;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.commands.Command;

import java.util.concurrent.TimeUnit;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", Permission.MESSAGE_MANAGE, "Verwijder berichten", "purge");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        try {
            int aantal = Integer.parseInt(args[0]);

            if (aantal <= 0) throw new IndexOutOfBoundsException();

            msg.delete().queue();
            msg.getTextChannel().deleteMessages(msg.getChannel().getHistory().retrievePast(aantal).complete()).queue();
            msg.getChannel().sendMessage(Embed.message(aantal + " berichten verwijderd.")).queue(mes -> mes.delete().queueAfter(10, TimeUnit.SECONDS));
            Cache.OVERIGELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(msg.getAuthor().getAsMention() + " heeft " + aantal + " berichten verwijderd" +
                   " in " + msg.getChannel().getName(), "© LordsMC", msg.getGuild().getIconUrl()));
        } catch (Exception ex) {
            msg.getChannel().sendMessage(Embed.message("Gebruik " + Settings.PREFIX + "clear <aantal berichten>")).queue();
        }
    }
}
