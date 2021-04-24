package be.vtir.bot.commands.staff;

import be.vtir.bot.Main;
import be.vtir.bot.util.Cache;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.commands.Command;

import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class
BanCommand extends Command {
    public BanCommand() {
        super("ban", Permission.BAN_MEMBERS, "Ban mensen die vervelend zijn");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        //Check of er iemand getagged wordt.
        if (msg.getMentionedMembers().size() == 0) {
            msg.getChannel().sendMessage(Embed.message("Gebruik `" + Settings.PREFIX + "ban <user> [reden]`")).queue();
        }

        Member moderator = msg.getMember();
        Member target = msg.getMentionedMembers().get(0);
        //Check of de moderator de target kan bannen.
        if (!moderator.canInteract(target)) {
            msg.getChannel().sendMessage(Embed.message("Deze persoon kan niet worden verbannen.")).queue(mes -> mes.delete().queueAfter(10, TimeUnit.SECONDS));
        } else if (!msg.getGuild().getMember(Main.getJda().getSelfUser()).canInteract(target)) {
            msg.getChannel().sendMessage(Embed.message("Deze persoon kan niet worden verbannen.")).queue(mes -> mes.delete().queueAfter(10, TimeUnit.SECONDS));
        } else {
            //Reden
            String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            if (reason.isEmpty()) reason = "Er is geen reden opgegeven.";

            //Create embed
            EmbedBuilder privatechannel = new EmbedBuilder();
            privatechannel.setTitle("Verbannen uit " + msg.getGuild().getName());
            privatechannel.setColor(Color.decode("#FFA500"));
            privatechannel.setThumbnail(msg.getGuild().getIconUrl());
            privatechannel.addField("Reden", reason, true);
            privatechannel.setFooter("© VTI Roeselare");
            privatechannel.setTimestamp(new Date().toInstant());
            //Verstuur een bericht naar de target.
            target.getUser().openPrivateChannel().queue(dm -> {
                dm.sendMessage(privatechannel.build()).queue();
            }, error -> {});

            //Verban de target.
            msg.getGuild().ban(target, 1).reason(reason).queue();

            //Verstuur een bericht in de channel waar je de persoon hebt gebanned.
            msg.getChannel().sendMessage(Embed.message(
                    target.getUser().getAsTag() + " is verbannen\n**Reden:** " + reason)).queue(mes -> mes.delete().queueAfter(10, TimeUnit.SECONDS));

            //Verwijder commando
            msg.delete().queue();

            //Verstuur een bericht in het log channel.
            Cache.STRAFFENLOGS_CHANNEL.sendMessage(Embed.footerenthumbnail(target.getUser().getName() + " is verbannen door " + msg.getAuthor().getAsMention() + ".\n**Reden:** " + reason,
                    "© VTI Roeselare", msg.getGuild().getIconUrl())).queue();

        }
    }
}
