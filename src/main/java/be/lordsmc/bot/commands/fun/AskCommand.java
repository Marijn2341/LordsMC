package be.lordsmc.bot.commands.fun;

import be.lordsmc.bot.util.Embed;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.commands.Command;

import java.util.Arrays;
import java.util.Random;

public class AskCommand extends Command {
    public AskCommand() {
        super("ask", null, "vraag iets aan de bot");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {

        String vraag = String.join(" ", Arrays.copyOfRange(args, 0, args.length));

        if (vraag.isEmpty()) {
            msg.getChannel().sendMessage(msg.getAuthor().getAsMention() + ", Je moet wel een vraag stellen!").queue();

        } else {
            String[] Antwoorden = {
                    "Ja!",
                    "Nee!",
                    "Mss.",
                    "Laat mij gewoon met rust..",
                    "Ik ga hier niet eens op antwoorden...",
                    "Daar moet ik even over nadenken.",
                    "Wie zal het weten.."
            };

            Integer random = new Random().nextInt(7);

            msg.getChannel().sendTyping().queue();
            msg.getChannel().sendMessage(Embed.footerenthumbnail("**Vraag:** `" + vraag + "`\n" +
                    "**Antwoord:** `" + Antwoorden[random] + "`", "© LordsMC", msg.getGuild().getIconUrl())).queue();
        }
    }
}
