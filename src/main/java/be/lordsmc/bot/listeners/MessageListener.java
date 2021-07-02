package be.lordsmc.bot.listeners;

import be.lordsmc.bot.Main;
import be.lordsmc.bot.util.AutoModerator;
import be.lordsmc.bot.util.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;

        Message msg = e.getMessage();
        Category cat = msg.getCategory();
        if (cat == null) return;

        Main.getCommandHandler().execute(msg);

        if (msg.getChannel().getIdLong() == Settings.SUGGESTIE_CHANNEL) {
            String suggest = msg.getContentDisplay();
            msg.delete().queue();
            Member target = e.getMember();
            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.decode("#ffb133"))
                    .setAuthor(target.getUser().getName(), target.getUser().getAvatarUrl(), target.getUser().getEffectiveAvatarUrl())
                    .setDescription(suggest);
            msg.getChannel().sendMessage(eb.build()).queue(message -> {
                message.addReaction("check:860102108619341844").queue();
                message.addReaction("reject:860102145125908510").queue();
            });
        }
        AutoModerator.handle(msg, false);
    }
}
