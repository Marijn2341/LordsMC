package be.lordsmc.bot.commands.staff;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import be.lordsmc.bot.Main;
import be.lordsmc.bot.util.Cache;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import be.lordsmc.bot.util.Embed;
import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.commands.Command;

public class KickCommand extends Command {
    public KickCommand() {
        super("kick", Permission.KICK_MEMBERS, "Kick mensen die zich niet aan de regels houden.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        //Check of er iemand getagged wordt.
        if (msg.getMentionedMembers().size() == 0) {
            msg.getChannel().sendMessage(Embed.message("Gebruik `" + Settings.PREFIX + "kick <user> [reden]`")).queue();
        } else {

            Member moderator = msg.getMember();
            Member target = msg.getMentionedMembers().get(0);
            //Check of de moderator de target kan kicken.
            if (!moderator.canInteract(target)) {
                msg.getChannel().sendMessage(Embed.message("Deze persoon kan niet worden gekicked.")).queue();
            } else if (!msg.getGuild().getMember(Main.getJda().getSelfUser()).canInteract(target)) {
                msg.getChannel().sendMessage(Embed.message("Deze persoon kan niet worden gekicked.")).queue();
            } else {
                //Reden
                String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                if (reason.isEmpty()) reason = "Geen reden opgegeven";

                //Create embed
                EmbedBuilder privatechannel = new EmbedBuilder();
                privatechannel.setTitle("Gekicked uit " + msg.getGuild().getName());
                privatechannel.setColor(Color.decode("#FFA500"));
                privatechannel.setThumbnail(msg.getGuild().getIconUrl());
                privatechannel.addField("Reden", reason, true);
                privatechannel.setFooter("?? LordsMC");
                privatechannel.setTimestamp(new Date().toInstant());
                //Verstuur een bericht naar de target.
                target.getUser().openPrivateChannel().queue(dm -> {
                    dm.sendMessage(privatechannel.build()).queue();
                }, error -> {
                });

                //Kick de target.
                msg.getGuild().kick(target).reason(reason).queue();

                //Verstuur een bericht in de channel waar je de persoon hebt gekicked.
                msg.getChannel().sendMessage(Embed.message(
                        target.getUser().getAsTag() + " is gekicked\n**Reden:** " + reason)).queue(mes -> mes.delete().queueAfter(10, TimeUnit.SECONDS));

                //Verwijder commando
                msg.delete().queue();

                //Verstuur een bericht in het log channel.
                Cache.STRAFFENLOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(target.getUser().getName() + " is gekicked door " + msg.getAuthor().getAsMention() + ".\n**Reden:** " + reason,
                        "?? LordsMC", msg.getGuild().getIconUrl())).queue();
            }
        }
    }
}

