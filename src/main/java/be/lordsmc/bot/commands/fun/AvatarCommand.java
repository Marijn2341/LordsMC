package be.lordsmc.bot.commands.fun;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import be.lordsmc.bot.util.commands.Command;

public class AvatarCommand extends Command {
    public AvatarCommand() {
        super("avatar", null, "Krijg iemands avatar");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        User target = msg.getMentionedUsers().size() == 0 ? msg.getAuthor() : msg.getMentionedUsers().get(0);
        msg.getChannel().sendMessage(target.getAvatarUrl()).queue();
    }
}
