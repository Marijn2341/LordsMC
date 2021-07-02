package be.lordsmc.bot.commands.staff;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.commands.Command;

public class SayCommand extends Command {
    public SayCommand() {
        super("say", Permission.MANAGE_CHANNEL, "Laat de bot dingen zeggen");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        msg.getChannel().sendMessage(String.join(" ", args)).queue();
    }
}
