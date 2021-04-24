package be.vtir.bot.util;

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

public class ArchiefManager {

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
            File transcriptFile = new File("liveles-" + name.substring(0, name.length()) + ".log");
            FileWriter writer = new FileWriter(transcriptFile);
            writer.write("--- Transcript van liveles " + name + " ---  \n");
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
