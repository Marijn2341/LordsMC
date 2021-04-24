package be.vtir.bot.reactionroles.commands;

import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

public class MessagePlaceCommand extends Command {
    public MessagePlaceCommand() {
        super("messageplace", Permission.BAN_MEMBERS, "Plaats de message van de reaction roles.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        EmbedBuilder cmdm = new EmbedBuilder();
        cmdm.setTitle("VTI Roeselare || Reaction Roles");
        cmdm.setColor(Color.decode("#ffb133"));
        cmdm.setDescription(Settings.REACTION_ROLE_MESSAGE);
        msg.getChannel().sendMessage(cmdm.build()).queue(message -> {
            message.addReaction("\uD83D\uDFE9").queue(); //Minecraft
            message.addReaction("\uD83D\uDD2B").queue(); //Call Of Duty
            message.addReaction("\uD83D\uDCB0").queue(); //Roblox
            message.addReaction("⚽").queue(); //Fifa
            message.addReaction("\uD83D\uDDE1").queue(); //Among Us
            message.addReaction("\uD83C\uDFCE").queue(); //Rocket League
            message.addReaction("\uD83D\uDD27").queue(); //League Of lergends
            message.addReaction("\uD83D\uDE97").queue(); //RC Crawling
            message.addReaction("\uD83D\uDD2A").queue(); //Valorant
        });
    }
}