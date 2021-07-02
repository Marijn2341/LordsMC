package be.lordsmc.bot.commands.fun;

import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;

import java.util.Random;

public class CoinFlipCommand extends Command {
    public CoinFlipCommand() {
        super("coinflip", null, "/.", "kopofmunt");
    }



    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        String[] resultaten = {
                "Kop",
                "Munt"
        };
        Integer random = new Random().nextInt(2);
        msg.getChannel().sendMessage(Embed.footerenthumbnail("Je hebt **" + resultaten[random] + "** gegooid.", "© LordsMC", msg.getGuild().getIconUrl())).queue();
    }
}