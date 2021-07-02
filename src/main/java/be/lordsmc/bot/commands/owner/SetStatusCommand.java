package be.lordsmc.bot.commands.owner;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.commands.Command;

public class SetStatusCommand extends Command {
    public SetStatusCommand() {
        super("setstatus", Permission.MESSAGE_MANAGE, "Verander de status van de bot.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (!msg.getAuthor().getId().equals("287195080748433419")) return;
        if (args.length == 0) {
            msg.getChannel().sendMessage(Embed.message("`-setstatus ONLINE/IDLE/DO_NOT_DISTURB/INVISIBLE`")).queue();
        } else {
            msg.getJDA().getPresence().setStatus(OnlineStatus.valueOf(args[0]));
            msg.getChannel().sendMessage(Embed.message("Je hebt de status van de bot veranderd naar " + args[0])).queue();
        }
    }
}
