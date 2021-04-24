package be.vtir.bot.reactionroles;

import be.vtir.bot.util.Cache;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.Utils;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class ReactionListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {

        if (e.getChannel().getIdLong() == Settings.REACTION_ROLE_CHANNEL && !e.getUser().isBot()) {
            e.getReaction().removeReaction(e.getUser()).complete();
            List<Role> roles = e.getMember().getRoles();
            //Minecraft
            if (e.getReactionEmote().getName().equals("\uD83D\uDFE9")) {
                if (!roles.contains(Cache.MINECRAFT_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.MINECRAFT_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.MINECRAFT_ROLE);
                }
            }
            //Call Of Duty 817133997066027040
            if (e.getReactionEmote().getName().equals("\uD83D\uDD2B")) {
                if (!roles.contains(Cache.CALLOFDUTY_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.CALLOFDUTY_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.CALLOFDUTY_ROLE);
                }
            }
            //Roblox 817134038346235924
            if (e.getReactionEmote().getName().equals("\uD83D\uDCB0")) {
                if (!roles.contains(Cache.ROBLOX_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.ROBLOX_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.ROBLOX_ROLE);
                }
            }
            //Fifa 817134054301892688
            if (e.getReactionEmote().getName().equals("⚽")) {
                if (!roles.contains(Cache.FIFA_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.FIFA_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.FIFA_ROLE);
                }
            }
            //Among Us 817134083301048332
            if (e.getReactionEmote().getName().equals("\uD83D\uDDE1")) {
                if (!roles.contains(Cache.AMONGUS_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.AMONGUS_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.AMONGUS_ROLE);
                }
            }
            //Rocket League 817134148720001055
            if (e.getReactionEmote().getName().equals("\uD83C\uDFCE")) {
                if (!roles.contains(Cache.ROCKETLEAGUE_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.ROCKETLEAGUE_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.ROCKETLEAGUE_ROLE);
                }
            }
            //League of Legends 817134148720001055
            if (e.getReactionEmote().getName().equals("\uD83D\uDD27")) {
                if (!roles.contains(Cache.LEAGUEOFLEGENDS_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.LEAGUEOFLEGENDS_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.LEAGUEOFLEGENDS_ROLE);
                }
            }
            //RC Crawling 817134186019553340
            if (e.getReactionEmote().getName().equals("\uD83D\uDE97")) {
                if (!roles.contains(Cache.RCCRAWLING_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.RCCRAWLING_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.RCCRAWLING_ROLE);
                }
            }
            //Valorant 817888250035306497
            if (e.getReactionEmote().getName().equals("\uD83D\uDD2A")) {
                if (!roles.contains(Cache.VALORANT_ROLE)) {
                    Utils.addRole(e.getMember(), Cache.VALORANT_ROLE);
                } else {
                    Utils.removeRole(e.getMember(), Cache.VALORANT_ROLE);
                }
            }
        }
    }
}