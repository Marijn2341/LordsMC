package be.lordsmc.bot.commands.staff;

import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ApproveSuggestieCommand extends Command {
    public ApproveSuggestieCommand() {
        super("approve", Permission.ADMINISTRATOR, "Approve een suggestie");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (msg.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            if (args.length == 0) {
                msg.getChannel().sendMessage("Geef een message id op.").queue(message -> {
                    message.delete().queueAfter(10, TimeUnit.SECONDS);
                });
            } else {
                Message bericht = msg.getGuild().getTextChannelById(Settings.SUGGESTIE_CHANNEL).retrieveMessageById(args[0]).complete();

                if (bericht.getEmbeds().isEmpty()) {
                    return;
                }
                EmbedBuilder embedBuilder = new EmbedBuilder(bericht.getEmbeds().get(0))
                        .setColor(Color.GREEN)
                        .setAuthor(bericht.getEmbeds().get(0).getAuthor().getName(), null, bericht.getEmbeds().get(0).getAuthor().getIconUrl())
                        .setDescription(bericht.getEmbeds().get(0).getDescription())
                        .setFooter("Deze suggestie is goedgekeurd!")
                        .setTimestamp(null);
                bericht.editMessage(embedBuilder.build()).queue();
                bericht.clearReactions().queue();

                msg.getChannel().sendMessage("Suggestie goedgekeurd ✅").queue();
            }
        }
    }
}
