package be.lordsmc.bot.commands.owner;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.commands.Command;

import java.util.Arrays;

public class SetWatchingCommand extends Command {
    public SetWatchingCommand() {
        super("setwatching", Permission.MESSAGE_MANAGE, "Verander de activity van de bot.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (!msg.getAuthor().getId().equals("287195080748433419")) return;
        if (args.length == 0) {
            msg.getChannel().sendMessage(Embed.message("`-setwatching <args>`")).queue();
        } else {
            String bericht = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
            msg.getJDA().getPresence().setActivity(Activity.watching(bericht));
            msg.getChannel().sendMessage(Embed.message("Je hebt de activity van de bot veranderd naar: `Watching " + bericht + "`")).queue();
        }
    }
}
