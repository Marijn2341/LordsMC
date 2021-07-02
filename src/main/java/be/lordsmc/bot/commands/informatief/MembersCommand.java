package be.lordsmc.bot.commands.informatief;

import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.Utils;

public class MembersCommand extends Command {
    public MembersCommand() {
        super("members", null, "Laat zien hoeveel mensen de discord heeft", "membercount");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        msg.getChannel().sendMessage(Embed.message("Members laden...")).queue(message -> {
            Utils.newThread(() -> {
                message.editMessage(Embed.footerenthumbnail("Members: " + msg.getGuild().loadMembers().get().size(), "© LordsMC", msg.getGuild().getIconUrl())).queue();
            });
        });
    }
}
