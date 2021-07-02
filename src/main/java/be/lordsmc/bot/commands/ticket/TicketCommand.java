package be.lordsmc.bot.commands.ticket;

import be.lordsmc.bot.util.TicketManager;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;


public class TicketCommand extends Command {
    public TicketCommand() {
        super("ticket", null, "Maak tickets aan", "new");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        msg.getChannel().sendMessage(TicketManager.create(msg.getMember()) ? "Ticket aangemaakt!"
                : "Je hebt nog een ticket openstaan!").queue();
    }
}