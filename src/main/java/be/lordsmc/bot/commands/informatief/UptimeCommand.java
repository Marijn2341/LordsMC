package be.lordsmc.bot.commands.informatief;

import be.lordsmc.bot.util.commands.Command;
import be.lordsmc.bot.util.Embed;
import net.dv8tion.jda.api.entities.Message;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class UptimeCommand extends Command {
    public UptimeCommand() {
        super("Uptime", null, "Zie hoelang de bot al online is");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long uptime = runtimeMXBean.getUptime();
        long uptimeInSeconds = uptime / 1000;
        long numberOfHours = uptimeInSeconds / (60 * 60);
        long numberOfMinutes = (uptimeInSeconds / 60) - (numberOfHours * 60);
        long numberOfSeconds = uptimeInSeconds % 60;
        String upt = numberOfHours + " uur, " + numberOfMinutes + " minuten, " + numberOfSeconds + " seconden.";
        msg.getChannel().sendMessage(Embed.footerenthumbnail("**Uptime:** `" + upt + "`", "© LordsMC", msg.getGuild().getIconUrl())).queue();
    }
}
