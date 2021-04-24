package be.vtir.bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.Instant;
import java.util.Date;

public class Embed {
    public static EmbedBuilder base() {
        return new EmbedBuilder().setColor(Color.decode("#ffb133")).setAuthor("VTI Roeselare").setTimestamp(new Date().toInstant());
    }

    public static MessageEmbed message(String text) {
        return message("VTI Roeselare", text, null);
    }

    public static MessageEmbed message(String text, String footer) {
        return base().setDescription(text).setFooter(footer).setTimestamp(Instant.now()).build();
    }

    public static MessageEmbed footerenthumbnail(String text, String footer, String thumbnail) {
        return base().setDescription(text).setFooter(footer).setTimestamp(Instant.now()).setThumbnail(thumbnail).build();
    }

    public static MessageEmbed message(String author, String text, String footer) {
        return base().setAuthor(author).setDescription(text).setFooter(footer).build();
    }

    public static MessageEmbed error() {
        return base().setDescription("Er ging iets fout").build();
    }
}
