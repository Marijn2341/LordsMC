package be.lordsmc.bot.commands.staff;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.Utils;
import be.lordsmc.bot.util.commands.Command;

public class WarnsCommand extends Command {
    public WarnsCommand() {
        super("warns", null, "Krijg de het aantal warns van een gebruiker", "infractions");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        User target = msg.getAuthor();
        if (msg.getMentionedUsers().size() >= 1 && msg.getMember().hasPermission(Permission.MESSAGE_MANAGE))
            target = msg.getMentionedUsers().get(0);

        int warns = Utils.getWarns(target.getId());
        msg.getChannel().sendMessage(Embed.footerenthumbnail(target.getAsMention() + " heeft " + warns + " warns", "© LordsMC", msg.getGuild().getIconUrl())).queue();
    }
}
