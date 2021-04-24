package be.vtir.bot;

import be.vtir.bot.reactionroles.ReactionListener;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import be.vtir.bot.listeners.JoinLeaveListener;
import be.vtir.bot.listeners.MessageListener;
import be.vtir.bot.util.Cache;
import be.vtir.bot.util.Settings;
import be.vtir.bot.util.commands.CommandHandler;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.sql.SQLException;

public class Main {
    @Getter
    static JDA jda;
    @Getter
    static CommandHandler commandHandler;

    public static void main(String[] args) throws LoginException, SQLException {
        try {
            jda = JDABuilder.createDefault(Settings.TOKEN)
                    .setStatus(OnlineStatus.ONLINE)
                    .setActivity(Activity.listening("'Waar technisch talent toekomst wordt!'"))
                    .setLargeThreshold(50)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS)
                    .enableCache(CacheFlag.VOICE_STATE)
                    .enableIntents(GatewayIntent.GUILD_INVITES, GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MEMBERS)
                    .build().awaitReady();
        } catch (LoginException | InterruptedException ex) {
            ex.printStackTrace();
        }
        Cache.init();
        commandHandler = new CommandHandler();

        jda.addEventListener(new MessageListener());
        jda.addEventListener(new JoinLeaveListener());
        jda.addEventListener(new ReactionListener());

        try {
            System.out.println("Listening on " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("\033[1;31mDe discordbot van VTI Roeselare is opgestart");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (br.readLine().equalsIgnoreCase("end")) {
                    jda.shutdown();
                    System.out.println("\033[1;31mDe discordbot van VTI Roeselare is afgesloten");
                    return;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
