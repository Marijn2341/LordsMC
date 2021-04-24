package be.vtir.bot.util;

import java.util.ArrayList;

public class Settings {
    public static String TOKEN = "ODA3MzA3MDAxNTczOTMzMTM3.YB2FPA.BV2hTn0nFvH8zJYYjWHTe6g3vUc";
    public static String PREFIX = "!";

    //Channels
    public static long WELCOME_CHANNEL = 816360908850331722L;
    public static long BOT_CHANNEL = 807331668384808980L;
    public static long REACTION_ROLE_CHANNEL = 817125567937511454L;
    public static long SUGGESTIE_CHANNEL = 834870341109153883L;
    public static long STRAFFENLOGS_CHANNEL = 835215563889442966L;
    public static long JOINLEAVELOGS_CHANNEL = 835215675509309462L;
    public static long OVERIGELOGS_CHANNEL = 835215777064812605L;

    //Category's
    public static long MAIN_CATEGORY = 807306334873321564L;

    //Roles
    public static long MEMBER_ROLE = 817892707258073120L;
    public static long MINECRAFT_ROLE = 817133975003987999L;
    public static long CALLOFDUTY_ROLE = 817133997066027040L;
    public static long ROBLOX_ROLE = 817134038346235924L;
    public static long FIFA_ROLE = 817134054301892688L;
    public static long AMONGUS_ROLE = 817134083301048332L;
    public static long ROCKETLEAGUE_ROLE = 817134118684459019L;
    public static long LEAGUEOFLEAGENDS_ROLE = 817134148720001055L;
    public static long RCCRAWLING_ROLE = 817134186019553340L;
    public static long VALORANT_ROLE = 817888250035306497L;


    public static ArrayList<String> ANTI_SWEAR_WORDS = new ArrayList<String>() {{
       add("kanker");
       add("hoer");
       add("homo");
       add("hoerezoon");
       add("kankerhoer");
       add("kankerkind");
       add("kankerlijer");
       add("whale");
    }};

    public static String WARNS_PATH = "./warnings.json";

    public static String TICKET_TEXT = "**Ticket van %s**\nWelkom in je ticket, " +
            "hier kan je vragen stellen over dingen die te maken hebben met school. " +
            "Wanneer je klaar bent, doe dan `-sluit` om het ticket af te sluiten.";

    public static String JOIN_TEXT = "%s welkom op de VTI Roeselare discord.";
    public static String LEAVE_TEXT = "%s heeft de discord verlaten.";

    public static String REACTION_ROLE_MESSAGE = "Klik op een emoji om toegang te krijgen tot een bepaalde channel.\n\n" +
            "\uD83D\uDFE9 | <@&817133975003987999>\n" + //Minecraft
            "\uD83D\uDD2B | <@&817133997066027040>\n" + //Call Of Duty
            "\uD83D\uDCB0 | <@&817134038346235924>\n" + //Roblox
            "⚽ | <@&817134054301892688>\n" + // Fifa
            "\uD83D\uDDE1 | <@&817134083301048332>\n" + // Among Us
            "\uD83C\uDFCE | <@&817134118684459019>\n" + //Rocket League
            "\uD83D\uDD27 | <@&817134148720001055>\n" +  //league Of legends
            "\uD83D\uDE97 | <@&817134186019553340>\n"  + //RC Crawling
            "\uD83D\uDD2A | <@&817888250035306497>"; //Valorant

}
