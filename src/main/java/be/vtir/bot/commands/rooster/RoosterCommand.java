package be.vtir.bot.commands.rooster;



import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;

public class RoosterCommand extends Command {

    public RoosterCommand() {
        super("rooster", null, "Kijk wat er op jou rooster staat!");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        msg.getChannel().sendMessage("https://vtiroeselare.smartschool.be/index.php?module=Agenda").queue();
    }
}
