package be.vtir.bot.listeners;

import be.vtir.bot.util.Embed;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import be.vtir.bot.util.Cache;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.Utils;

public class JoinLeaveListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member member = e.getMember();
        Utils.addRole(member, Cache.MEMBER_ROLE);
        Utils.newThread(() -> {
            Cache.WELCOME_CHANNEL.sendMessage(String.format(Settings.JOIN_TEXT, member.getAsMention())).queue();
            Cache.JOINLEAVELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail("\uD83D\uDCC8 " + member.getAsMention() + " heeft de server betreden.", "© VTI Roeselare", e.getGuild().getIconUrl())).queue();
        });
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        Utils.newThread(() -> {
            Cache.WELCOME_CHANNEL.sendMessage(String.format(Settings.LEAVE_TEXT, e.getUser().getName())).queue();
            Cache.JOINLEAVELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail("\uD83D\uDC4B " + e.getUser().getName() + " heeft de server verlaten.", "© VTI Roeselare", e.getGuild().getIconUrl())).queue();

        });

    }
}
