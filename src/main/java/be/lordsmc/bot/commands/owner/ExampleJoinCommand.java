package be.lordsmc.bot.commands.owner;

import be.lordsmc.bot.util.Utils;
import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.util.Random;

public class ExampleJoinCommand extends Command {
    public ExampleJoinCommand() {
        super("exjoin", Permission.ADMINISTRATOR, "voorbeeld join message");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        if (msg.getMember().hasPermission(Permission.ADMINISTRATOR)) {

            Utils.newThread(() -> {
                String[] JOIN_TXT = {
                        "Hallo %s, welkom op LordsMC.",
                        "Welkom op de server %s!",
                        "Geef %s een warm welkom!",
                        "%s slided into the server.",
                        "%s sprong zo de server binnen.",
                        "Leuk dat je er bent %s!",
                        "%s welkom! Je hebt toch wel een koekje voor ons?"
                };
                Integer random = new Random().nextInt(7);

                EmbedBuilder eb = new EmbedBuilder()
                        .setColor(Color.decode("#ffb133"))
                        .setAuthor(msg.getMember().getUser().getName(), msg.getMember().getUser().getAvatarUrl(), msg.getMember().getUser().getEffectiveAvatarUrl())
                        .setThumbnail(msg.getGuild().getIconUrl())
                        .setDescription(String.format(JOIN_TXT[random], msg.getMember().getAsMention()))
                        .setFooter("Er zitten " + msg.getGuild().loadMembers().get().size() + " members in de discord server.");

                msg.getChannel().sendMessage(eb.build()).queue();
            });
        }
    }
}
