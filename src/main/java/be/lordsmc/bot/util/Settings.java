package be.lordsmc.bot.util;

import java.util.ArrayList;

public class Settings {
    public static String TOKEN = "ODU2OTc5NjYzMDEyNjI2NTEy.YNI6gQ.ov_kmytVk7qGNxcCprCoHj3hGAM";
    public static String PREFIX = "-";

    //Channels
    public static long WELCOME_CHANNEL = 858045506059763722L;
    public static long BOT_CHANNEL = 857611836148809749L;
    public static long SUGGESTIE_CHANNEL = 858068874117120000L;
    public static long STRAFFENLOGS_CHANNEL = 858069093353127947L;
    public static long JOINLEAVELOGS_CHANNEL = 858069150572478484L;
    public static long OVERIGELOGS_CHANNEL = 858069200518119424L;
    public static long TICKET_L0G_CHANNEL = 858074382895022080L;
    public static long TICKET_CREATION_CHANNEL = 858070371714727946L;
    public static long CREATE_VOICE_CHANNEL = 859121673655681044L;

    //Category's
    public static long MAIN_CATEGORY = 807306334873321564L;
    public static long TICKET_CATEGORY = 858073901354188820L;
    public static long VOICE_CATEGORY = 859119544898551850L;

    //Roles
    public static long MEMBER_ROLE = 858069303463247873L;




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
    public static String TICKET_PATH = "./ticketnumb.json";

    public static String TICKET_TEXT = "**Ticket van %s**\nWelkom in je ticket, " +
            "hier kan je vragen stellen over dingen die te maken hebben met LordsMC. " +
            "Wanneer je klaar bent, doe dan `-sluit` om het ticket af te sluiten.";

    public static String JOIN_TEXT = "%s welkom op de LordsMC discord.";
    public static String LEAVE_TEXT = "%s heeft de discord verlaten.";
}
