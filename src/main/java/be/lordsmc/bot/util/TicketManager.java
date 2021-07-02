package be.lordsmc.bot.util;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TicketManager {
    public static boolean create(Member member) {
        User user = member.getUser();
        Guild guild = member.getGuild();
        try {
            if (guild.getTextChannelsByName(user.getName(), true).size() > 0) return false;

            int TicketNumber = Utils.getTicketNumber() + 1;
            String TicketNaam = "ticket-" + TicketNumber;

            TextChannel ticket = Cache.TICKET_CATEGORY.createTextChannel(TicketNaam).complete();
            ticket.createPermissionOverride(member).setAllow(new Permission[] {
                    Permission.VIEW_CHANNEL,
                    Permission.MESSAGE_WRITE,
                    Permission.MESSAGE_READ,
                    Permission.MESSAGE_HISTORY,
                    Permission.MESSAGE_EMBED_LINKS,
                    Permission.MESSAGE_ATTACH_FILES
            }).queue();

            Utils.addNumberTicket(1);

            ticket.putPermissionOverride(guild.getPublicRole()).setDeny(new Permission[] { Permission.VIEW_CHANNEL }).queue();

            ticket.sendMessage(member.getAsMention())
                    .embed(Embed.message(String.format(Settings.TICKET_TEXT, user.getName()))).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public static File generateTranscript(TextChannel channel) {
        StringJoiner joiner = new StringJoiner("\n", "", "");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        List<Message> messages = channel.getHistory().retrievePast(100).complete();
        Collections.reverse(messages);

        for (Message msg : messages) {
            if (msg.getContentRaw().isEmpty()) continue;
            String text = msg.getContentDisplay();
            if (msg.getAttachments().size() > 0) text += "\n\tAttachments: " + msg.getAttachments().stream()
                    .map(Message.Attachment::getUrl).collect(Collectors.joining(", "));
            if (msg.getEmbeds().size() > 0) text += "\n\tEmbeds: " + msg.getEmbeds().stream()
                    .map(MessageEmbed::getDescription).collect(Collectors.joining(", "));

            joiner.add(String.format("[%s] %s: %s", msg.getTimeCreated().format(formatter),
                    msg.getAuthor().getAsTag(), text));
        }

        String name = channel.getName();

        try {
            File transcriptFile = new File("ticket-" + name.substring(0, name.length() - 4) + ".log");
            FileWriter writer = new FileWriter(transcriptFile);
            writer.write("--- Transcript van ticket " + name + " ---  \n");
            writer.write(joiner.toString());
            writer.write("\n--- Einde transcript ---  ");
            writer.close();
            return transcriptFile;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}