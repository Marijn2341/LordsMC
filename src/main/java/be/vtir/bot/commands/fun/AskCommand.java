package be.vtir.bot.commands.fun;

import net.dv8tion.jda.api.entities.Message;
import be.vtir.bot.util.commands.Command;

import java.util.Random;

public class AskCommand extends Command {
    public AskCommand() {
        super("8ball", null, "vraag iets aan de bot");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {

        String[] Antwoorden = {
                "Ja!",
                "Nee!",
                "Mss.",
                "Laat mij gewoon met rust joh..",
                "Ik ga hier niet eens op antwoorden...",
                "Daar moet ik even over nadenken.",
                "Wie zal het weten.."
        };

        Integer random = new Random().nextInt(7);
        msg.getChannel().sendMessage("`" + Antwoorden[random] + "`").queue();
    }
}
