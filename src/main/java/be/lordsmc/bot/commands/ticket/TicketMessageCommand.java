package be.lordsmc.bot.commands.ticket;

import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;


public class TicketMessageCommand extends Command {
    public TicketMessageCommand() {
        super("ticketmessage", Permission.ADMINISTRATOR, "Ticket message");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (msg.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                msg.getChannel().sendMessage(Embed.footerenthumbnail("Heb jij een vraag of wil je iets melden?\nMaak dan een ticket door op \uD83D\uDD16 te klikken.", "© LordsMC", msg.getGuild().getIconUrl())).queue(message -> {
                    message.addReaction("\uD83D\uDD16").queue();
                });
                msg.delete().queue();
        }
    }
}