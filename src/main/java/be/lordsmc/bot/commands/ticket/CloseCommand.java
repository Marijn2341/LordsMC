package be.lordsmc.bot.commands.ticket;

import be.lordsmc.bot.util.Cache;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.TicketManager;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CloseCommand extends Command {
    public CloseCommand() {
        super("close", null, "Sluit een ticket", "sluit");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (msg.getCategory() == null ||
                msg.getCategory().getIdLong() == Settings.TICKET_CATEGORY) {
            msg.getChannel().sendMessage(Embed.message("Ticket wordt afgesloten over 5 seconden...")).queue(message -> {
                message.editMessage(Embed.message("Ticket wordt afgesloten over 4 seconden...")).queueAfter(1, TimeUnit.SECONDS);
                message.editMessage(Embed.message("Ticket wordt afgesloten over 3 seconde...")).queueAfter(2, TimeUnit.SECONDS);
                message.editMessage(Embed.message("Ticket wordt afgesloten over 2 seconde...")).queueAfter(3, TimeUnit.SECONDS);
                message.editMessage(Embed.message("Ticket wordt afgesloten over 1 seconde...")).queueAfter(4, TimeUnit.SECONDS);
            });
            try {
                msg.getTextChannel().delete().queueAfter(5, TimeUnit.SECONDS);
            } catch (Exception ignored) {}

            File transcript = TicketManager.generateTranscript(msg.getTextChannel());
            MessageAction logMessage = Cache.TICKET_LOG_CHANNEL.sendMessage("Ticket " + msg.getChannel().getName() +
                    " is afgesloten door " + msg.getAuthor().getAsTag() + ". Het transcript " +
                    (transcript == null ? "heeft helaas niet kunnen genereren." : "is bijgevoegd."));
            if (transcript != null) logMessage.addFile(transcript).queue();
            else logMessage.queue();
            transcript.delete();
        } else msg.getChannel().sendMessage(Embed.message("Deze command kun je alleen gebruiken in tickets.")).queue();
    }
}