package be.vtir.bot.commands.staff;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import be.vtir.bot.util.Cache;
import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.Utils;
import be.vtir.bot.util.commands.Command;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WarnCommand extends Command {
    public WarnCommand() {
        super("warn", Permission.MESSAGE_MANAGE, "Warn mensen");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        //Check of er iemand getagged wordt.
        if (msg.getMentionedUsers().size() == 0) {
            msg.getChannel().sendMessage(Embed.message("Gebruik `" + Settings.PREFIX + "warn <user> [reden]`")).queue();
        }

        warn(msg.getMentionedUsers().get(0), msg,
                String.join(" ", Arrays.copyOfRange(args, 1, args.length)), false);
    }

    static void warn(User target, Message msg, String reden, boolean automod) {
        if (Utils.addWarns(target.getId(), 1)) {
            if (reden.isEmpty()) reden = "_Geen reden opgegeven_";
            int warns = Utils.getWarns(target.getId());
            String info = "\n\n**Reden:** " + reden + "\n**Nieuw aantal warns: **" + Utils.getWarns(target.getId());
            msg.getChannel().sendMessage(Embed.message(target.getAsMention() + " heeft een warn ontvangen" +
                    info, automod ? "AutoModerator" : msg.getMember().getEffectiveName())).queue();
            msg.delete().queue();

            Cache.STRAFFENLOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(target.getAsMention() + " heeft een warn ontvangen." +
                    info, "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();


            if (warns % 3 == 0 && warns != 0) {
                target.openPrivateChannel().queue(dm -> {
                    dm.sendMessage(Embed.footerenthumbnail("Je bent gekickt van de VTI Roeselare discord server omdat" +
                            " je een meervoud van 3 warns had.", "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();
                }, error -> {});
                msg.getGuild().kick(target.getId()).queue();

                //Verstuur bericht in logs (kick omdat 3 warns)
                Cache.STRAFFENLOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(target.getAsTag() + " is gekicked uit de server omdat hij/zij een meervoud had van 3 warns.",
                        "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();
            }

            msg.delete().queue();
        } else msg.getChannel().sendMessage(Embed.error()).queue();
    }

    public static void fakeWarn(User target, String reden) {
        Cache.BOT_CHANNEL.sendTyping().queue();
        Cache.BOT_CHANNEL.sendMessage(Settings.PREFIX + "warn " + target.getAsMention() + " " + reden)
            .queueAfter(new Random().nextInt(500) + 1000, TimeUnit.MILLISECONDS,
                msg -> warn(target, msg, reden, true));
    }
}
