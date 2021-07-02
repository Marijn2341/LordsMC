package be.lordsmc.bot.listeners;

import be.lordsmc.bot.util.Embed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import be.lordsmc.bot.util.Cache;
import be.lordsmc.bot.util.Settings;
import be.lordsmc.bot.util.Utils;

import java.awt.*;
import java.util.Random;

public class JoinLeaveListener extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Member member = e.getMember();

        // Geeft Member role
        Utils.addRole(member, Cache.MEMBER_ROLE);
        //Join messages
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
                    .setAuthor(e.getMember().getUser().getName(), e.getMember().getUser().getAvatarUrl(), e.getMember().getUser().getEffectiveAvatarUrl())
                    .setThumbnail(e.getGuild().getIconUrl())
                    .setDescription(String.format(JOIN_TXT[random], e.getMember().getAsMention()))
                    .setFooter("Er zitten " + e.getGuild().loadMembers().get().size() + " members in de discord server.");

            Cache.WELCOME_CHANNEL.sendMessage(eb.build()).queue();
            //Logs
            Cache.JOINLEAVELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail("\uD83D\uDCC8 " + member.getAsMention() + " heeft de server betreden.", "© LordsMC", e.getGuild().getIconUrl())).queue();
        });
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent e) {
        Utils.newThread(() -> {
            EmbedBuilder eb = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setAuthor(e.getMember().getUser().getName(), e.getMember().getUser().getAvatarUrl(), e.getMember().getUser().getEffectiveAvatarUrl())
                    .setThumbnail(e.getGuild().getIconUrl())
                    .setDescription(e.getMember().getEffectiveName() + "#" + e.getMember().getUser().getDiscriminator() + " heeft de discord verlaten..")
                    .setFooter("Er zitten " + e.getGuild().loadMembers().get().size() + " members in de discord server.");
            Cache.WELCOME_CHANNEL.sendMessage(eb.build()).queue();
            Cache.JOINLEAVELOGS_CHANNEL.sendMessage(Embed.footerenthumbnail("\uD83D\uDC4B " + e.getUser().getName() + " heeft de server verlaten.", "© LordsMC", e.getGuild().getIconUrl())).queue();
        });

    }
}
