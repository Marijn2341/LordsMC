package be.vtir.bot.listeners;

import be.vtir.bot.Main;
import be.vtir.bot.util.AutoModerator;
import be.vtir.bot.util.Settings;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getAuthor().isBot() || e.isWebhookMessage()) return;

        Message msg = e.getMessage();
        Category cat = msg.getCategory();
        if (cat == null) return;

        Main.getCommandHandler().execute(msg);

        if (msg.getChannel().getIdLong() == Settings.SUGGESTIE_CHANNEL) {
            msg.addReaction("\uD83D\uDC4D").queue(a -> {
                msg.addReaction("\uD83D\uDC4E").queue();
            });
        }

        AutoModerator.handle(msg, false);
    }
}
