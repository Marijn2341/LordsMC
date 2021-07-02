package be.lordsmc.bot.listeners;

import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.TicketManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if (e.getChannel().getIdLong() == Settings.TICKET_CREATION_CHANNEL && !e.getUser().isBot()) {
            e.getReaction().removeReaction(e.getUser()).complete();
            TicketManager.create(e.getMember());
        }
    }
}