package be.lordsmc.bot.commands.informatief;

import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", null, "Krijg een help message");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
       msg.getChannel().sendMessage("**Help Pagina**\n```" +
               "-Botinfo || Krijg info over de discord bot.\n" +
               "-Members || Zie hoeveel members er in de server zitten.\n" +
               "-Uptime || Zie wat de uptime van de bot is.\n" +
               "-Ask || Stel een vraag aan de bot.\n" +
               "-Avatar || Vergroot jou profielfoto of die van iemand anders.\n" +
               "-Meme || Laat de bot een meme plaatsen.\n" +
               "-Userinfo || Krijg informatie te zien over een bepaalde gebruiker.\n" +
               "-Coinflip || Speel kop of munt.\n" +
               "-Mop || Laat de bot een mop plaatsen.```").queue();
    }
}
