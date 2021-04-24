package be.vtir.bot.commands.owner;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import be.vtir.bot.util.Embed;
import be.vtir.bot.util.commands.Command;

import java.util.Arrays;

public class SetPlayingCommand extends Command {
    public SetPlayingCommand() {
        super("setplaying", Permission.MESSAGE_MANAGE, "Verander de activity van de bot.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (!msg.getAuthor().getId().equals("287195080748433419")) return;
        if (args.length == 0) {
            msg.getChannel().sendMessage(Embed.message("`-setplaying <args>`")).queue();
        } else {
            String bericht = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
            msg.getJDA().getPresence().setActivity(Activity.playing(bericht));
            msg.getChannel().sendMessage(Embed.message("Je hebt de activity van de bot veranderd naar: `Playing " + bericht + "`")).queue();
        }
    }
}
