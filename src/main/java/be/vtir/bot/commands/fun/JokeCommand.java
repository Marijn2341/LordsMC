package be.vtir.bot.commands.fun;

import be.vtir.bot.util.Embed;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.entities.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class JokeCommand extends Command {

    public JokeCommand() {
        super("joke", null, "Plaats een mop.", "mop");
    }



    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        try {
            URL apiString = new URL("http://api.apekool.nl/services/jokes/getjoke.php?type=alg");
            HttpURLConnection con = (HttpURLConnection) apiString.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)
            );
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            con.disconnect();
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(content.toString());

            msg.getChannel().sendTyping().queue();
            msg.getChannel().sendMessage(Embed.footerenthumbnail(data.get("joke").toString(), "© VTI Roeselare", msg.getGuild().getIconUrl())).queueAfter(5, TimeUnit.SECONDS);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}