package be.vtir.bot.commands.fun;

import be.vtir.bot.util.Embed;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class MemeCommand extends Command {
    public MemeCommand() {
        super("meme", null, "Plaats een meme.");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        JSONParser parser = new JSONParser();
        String postlink = "";
        String title = "";
        String url = "";

        try {
            URL memeURL = new URL("https://meme-api.herokuapp.com/gimme");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));
            String lines;

            while ((lines = bufferedReader.readLine()) != null) {
                JSONArray array = new JSONArray();
                array.add(parser.parse(lines));

                for (Object o : array) {
                    JSONObject jsonObject = (JSONObject) o;

                    postlink = (String) jsonObject.get("postlink");
                    title = (String) jsonObject.get("title");
                    url = (String) jsonObject.get("url");
                }
            }

            bufferedReader.close();

            msg.delete().queue();

            EmbedBuilder embedjuh = new EmbedBuilder()
                    .setTitle(title, postlink)
                    .setImage(url)
                    .setColor(Color.decode("#ffb133"))
                    .setTimestamp(new Date().toInstant())
                    .setFooter("Aangevraagd door: " + msg.getAuthor().getAsTag());
            msg.getChannel().sendMessage(embedjuh.build()).queue();

        } catch (Exception e) {
            msg.getChannel().sendMessage(Embed.error()).queue();
            e.printStackTrace();
        }
    }
}
