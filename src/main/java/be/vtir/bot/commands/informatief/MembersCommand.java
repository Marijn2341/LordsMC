package be.vtir.bot.commands.informatief;

import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;
import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Utils;

public class MembersCommand extends Command {
    public MembersCommand() {
        super("members", null, "Laat zien hoeveel mensen de discord heeft", "membercount");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        msg.getChannel().sendMessage(Embed.message("Members laden...")).queue(message -> {
            Utils.newThread(() -> {
                message.editMessage(Embed.footerenthumbnail("Members: " + msg.getGuild().loadMembers().get().size(), "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();
            });
        });
    }
}
