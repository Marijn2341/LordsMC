package be.vtir.bot.commands;

import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", null, "Krijg een help message");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
       msg.getChannel().sendMessage("**Help Pagina**\n```Botinfo || Krijg info over de discord bot.\n" +
               "Members || Zie hoeveel members er in de server zitten.\n" +
               "Uptime || Zie wat de uptime van de bot is.\n" +
               "8ball || Stel een vraag aan de bot.\n" +
               "Avatar || Vergroot jou profielfoto of die van iemand anders.```").queue();
    }
}
