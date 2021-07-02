package be.lordsmc.bot.commands.informatief;

import be.lordsmc.bot.util.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserInfoCommand extends Command {
    public UserInfoCommand() {
        super("userinfo", null, "Check info blablabla");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        Member target = msg.getMentionedMembers().size() == 0 ? msg.getMember() : msg.getMentionedMembers().get(0);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{

            String nickname = target.getNickname();
            if (target.getNickname() == null) nickname = "Gerbuiker heeft geen nickname";

            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.decode("#ffb133"))
                    .setAuthor(target.getUser().getName(), target.getUser().getAvatarUrl(), target.getUser().getEffectiveAvatarUrl())
                    .setThumbnail(target.getUser().getAvatarUrl())
                    .setDescription("**Account aangemaakt op:** `" + target.getTimeCreated().format(format) + "`\n" +
                            "**Server gejoined op:** `" + target.getTimeJoined().format(format) + "`\n" +
                            "**User ID:** `" + target.getId() + "`\n" +
                            "**User tag:** `" + target.getUser().getAsTag() + "`\n" +
                            "**User nickname:** `" + nickname + "`")
                    .setFooter("© LordsMC")
                    .setTimestamp(new Date().toInstant());
            msg.getChannel().sendTyping().queue();
            msg.getChannel().sendMessage(eb.build()).queueAfter(5, TimeUnit.SECONDS);

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}