package be.vtir.bot.util.commands;

import be.vtir.bot.commands.*;
import be.vtir.bot.commands.fun.AskCommand;
import be.vtir.bot.commands.fun.AvatarCommand;
import be.vtir.bot.commands.leerkracht.LivelesCommand;
import be.vtir.bot.commands.owner.SetListeningCommand;
import be.vtir.bot.commands.owner.SetPlayingCommand;
import be.vtir.bot.commands.owner.SetStatusCommand;
import be.vtir.bot.commands.owner.SetWatchingCommand;
import be.vtir.bot.commands.rooster.RoosterCommand;
import be.vtir.bot.commands.staff.*;
import be.vtir.bot.reactionroles.commands.MessagePlaceCommand;
import lombok.Getter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import be.vtir.bot.util.Embed;
import be.vtir.bot.util.Settings;

import java.util.ArrayList;
import java.util.Arrays;


public class CommandHandler {
    @Getter ArrayList<Command> commands = new ArrayList<>();
    public CommandHandler() {
        commands.addAll(Arrays.asList(
            new AvatarCommand(),
            new SayCommand(),
            new MembersCommand(),
            new ClearCommand(),
            new WarnCommand(),
            new WarnsCommand(),
            new BanCommand(),
            new KickCommand(),
            new UptimeCommand(),
            new SetStatusCommand(),
            new SetListeningCommand(),
            new SetPlayingCommand(),
            new SetWatchingCommand(),
                new AskCommand(),
                new RoosterCommand(),
                new MessagePlaceCommand(),
                new BotInfoCommand(),
                new HelpCommand()
        ));
    }

    public void execute(Message msg) {
        if (msg.getChannelType() != ChannelType.TEXT || !msg.getContentRaw().startsWith(Settings.PREFIX)) return;
        String[] args = msg.getContentRaw().split(" ");
        if (args[0].length() < 1) return;
        Command cmd = getCommand(args[0].substring(Settings.PREFIX.length()));
        if (cmd == null || (cmd.getPermission() != null && !msg.getMember().getPermissions().contains(cmd.getPermission()))) return;
        try {
            cmd.onCommand(cmd, Arrays.copyOfRange(args, 1, args.length), msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            msg.getChannel().sendMessage(Embed.error()).queue();
        }
    }

    Command getCommand(String name) {
        for (Command cmd : commands) if (cmd.getName().equalsIgnoreCase(name) || cmd.getAliases().contains(name)) return cmd;
        return null;
    }

}
