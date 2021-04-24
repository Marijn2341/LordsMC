package be.vtir.bot.commands;

import be.vtir.bot.util.commands.Command;
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
        msg.getChannel().sendMessageFormat(
                "Uptime: `%s uur, %s minuten, %s seconden.`",
                numberOfHours, numberOfMinutes, numberOfSeconds
        ).queue();
    }
}
