package be.lordsmc.bot.listeners;

import be.lordsmc.bot.util.Cache;
import be.lordsmc.bot.util.Settings;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent e) {
        Member target = e.getMember();
        Guild guild = e.getGuild();
        if (e.getChannelJoined().getIdLong() == Settings.CREATE_VOICE_CHANNEL) {
            try {
                VoiceChannel newchannel = Cache.VOICE_CATEGORY.createVoiceChannel(target.getUser().getName() + "#" +
                        target.getUser().getDiscriminator()).complete();
                newchannel.createPermissionOverride(target).setAllow(new Permission[]{
                        Permission.VIEW_CHANNEL,
                        Permission.MANAGE_CHANNEL,
                        Permission.VOICE_SPEAK
                }).queue();

                guild.moveVoiceMember(target, newchannel).queue();
            }  catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent e) {
        Member target = e.getMember();
        VoiceChannel channel = e.getChannelLeft();
        if (e.getChannelLeft().getParent().getIdLong() == Settings.VOICE_CATEGORY) {
            if (channel.getName().equalsIgnoreCase(target.getUser().getName() + "#" +
                    target.getUser().getDiscriminator())) {
                channel.delete().queue();
        } else {
                if (e.getChannelLeft().getMembers().size() == 0) {
                    if (!(e.getChannelLeft().getIdLong() == Settings.CREATE_VOICE_CHANNEL)) {
                        channel.delete().queue();
                    }
                }
            }
        }
    }
}
