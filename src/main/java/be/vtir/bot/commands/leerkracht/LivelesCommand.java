package be.vtir.bot.commands.leerkracht;

import be.vtir.bot.util.ArchiefManager;
import be.vtir.bot.util.Cache;
import be.vtir.bot.util.commands.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.io.File;

public class LivelesCommand extends Command {

    public LivelesCommand() {
        super("liveles", Permission.MESSAGE_MANAGE, "Maak een liveles aan!");
    }

    @Override
    public void onCommand(Command cmd, String[] args, Message msg) {
        /*
        Guild guild = msg.getMember().getGuild();
        if (args.length > 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (msg.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                    if (msg.getMember().getVoiceState().inVoiceChannel()) {
                        if (!(guild.getRolesByName(args[1], true).size() <= 0)) {
                            String rolename = args[1];
                            Role role = guild.getRolesByName(rolename, true).get(0);

                            try {
                                TextChannel textlive = Cache.LIVELES_CATEG.createTextChannel(args[1] + "-live").complete();
                                VoiceChannel voicelive = Cache.LIVELES_CATEG.createVoiceChannel(args[1] + " Liveles").complete();

                                //text channels permissions
                                textlive.createPermissionOverride(msg.getMember()).setAllow(new Permission[]{
                                        Permission.VIEW_CHANNEL,
                                        Permission.MESSAGE_WRITE,
                                        Permission.MESSAGE_READ,
                                        Permission.MESSAGE_HISTORY,
                                        Permission.MESSAGE_EMBED_LINKS,
                                        Permission.MESSAGE_ATTACH_FILES,
                                        Permission.MESSAGE_MANAGE
                                }).queue();
                                textlive.putPermissionOverride(role).setAllow(new Permission[]{
                                        Permission.VIEW_CHANNEL,
                                        Permission.MESSAGE_WRITE,
                                        Permission.MESSAGE_READ,
                                        Permission.MESSAGE_HISTORY,
                                        Permission.MESSAGE_EMBED_LINKS,
                                        Permission.MESSAGE_ATTACH_FILES,
                                }).queue();

                                textlive.putPermissionOverride(guild.getPublicRole()).setDeny(new Permission[]{Permission.VIEW_CHANNEL}).queue();
                                //voice channel permssions
                                voicelive.createPermissionOverride(msg.getMember()).setAllow(new Permission[]{
                                        Permission.VIEW_CHANNEL,
                                        Permission.VOICE_CONNECT,
                                        Permission.VOICE_MUTE_OTHERS,
                                        Permission.PRIORITY_SPEAKER,
                                        Permission.VOICE_MOVE_OTHERS,
                                        Permission.VOICE_SPEAK
                                }).queue();
                                voicelive.putPermissionOverride(role).setAllow(new Permission[]{
                                        Permission.VIEW_CHANNEL,
                                        Permission.VOICE_CONNECT,
                                        Permission.VOICE_SPEAK
                                }).queue();

                                voicelive.putPermissionOverride(guild.getPublicRole()).setDeny(new Permission[]{Permission.VIEW_CHANNEL}).queue();

                                guild.moveVoiceMember(msg.getMember(), voicelive).queue();

                                msg.getChannel().sendMessage("De liveles voor " + args[1] + " is aangemaakt.").queue();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Ik kon deze klas niet vinden.").queue();
                        }
                    } else {
                        msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je moet in een voice kanaal zitten om deze command te kunnen uit voeren.").queue();
                    }
                } else {
                    msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kunt deze command alleen gebruiken als leerkracht.").queue();
                }
            }

            //add command
            if (args[0].equalsIgnoreCase("add")) {
                if (msg.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                    VoiceChannel vcchannel = msg.getMember().getVoiceState().getChannel();
                    if (msg.getCategory().getIdLong() == 807306334873321565L) {
                        if (vcchannel.getParent().getIdLong() == 807306334873321565L) {
                            if (msg.getMember().getVoiceState().inVoiceChannel()) {
                                if (!(guild.getRolesByName(args[1], true).size() <= 0)) {
                                    String rolename = args[1];
                                    Role role = guild.getRolesByName(rolename, true).get(0);

                                    try {

                                        vcchannel.putPermissionOverride(role).setAllow(new Permission[]{
                                                Permission.VIEW_CHANNEL,
                                                Permission.VOICE_CONNECT,
                                                Permission.VOICE_SPEAK
                                        }).queue();

                                        msg.getTextChannel().putPermissionOverride(role).setAllow(new Permission[] {
                                                Permission.VIEW_CHANNEL,
                                                Permission.MESSAGE_WRITE,
                                                Permission.MESSAGE_READ,
                                                Permission.MESSAGE_HISTORY,
                                                Permission.MESSAGE_EMBED_LINKS,
                                                Permission.MESSAGE_ATTACH_FILES,
                                        }).queue();
                                        msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", " + args[1] + " is succesvol toegevoegd aan de liveles.").queue();
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Ik kon deze klas niet vinden.").queue();
                                }
                            } else {
                                msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je moet in een voice kanaal zitten om deze command te kunnen uit voeren.").queue();
                            }
                        } else {
                            msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kan deze command alleen gebruiken als je in een liveles zit.").queue();
                        }
                    } else {
                        msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kunt deze command alleen gebruiken in een liveles kanaal.").queue();
                    }
                } else {
                    msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kunt deze command alleen gebruiken als leerkracht.").queue();
                }
            }

            if (args[0].equalsIgnoreCase("stop")) {
                if (msg.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                    VoiceChannel vcchannel = msg.getMember().getVoiceState().getChannel();
                    if (msg.getCategory().getIdLong() == 807306334873321565L) {
                        if (msg.getMember().getVoiceState().inVoiceChannel()) {
                             if (vcchannel.getParent().getIdLong() == 807306334873321565L) {
                                try {
                                    File transcript = ArchiefManager.generateTranscript(msg.getTextChannel());
                                    MessageAction logMessage = Cache.LIVELES_ARCHIEF_CHANNEL.sendMessage("Liveles " + msg.getChannel().getName() +
                                            " is afgesloten door " + msg.getAuthor().getAsTag() + ". Het transcript " +
                                            (transcript == null ? "heeft helaas niet kunnen genereren." : "is bijgevoegd."));
                                    if (transcript != null) logMessage.addFile(transcript).queue();
                                    else logMessage.queue();
                                    transcript.delete();
                                    vcchannel.delete().complete();
                                    msg.getTextChannel().delete().queue();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } else {
                            msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je moet in een voice kanaal zitten om deze command te kunnen uit voeren.").queue();
                        }
                    } else {
                        msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kunt deze command alleen gebruiken in een liveles kanaal.").queue();
                    }
                } else {
                    msg.getChannel().sendMessage(msg.getMember().getAsMention() + ", Je kunt deze command alleen gebruiken als leerkracht.").queue();
                }
            }

        } else {
            msg.getChannel().sendMessage("Usage: `!liveles <start/add/stop> <klas>`").queue();
        }

         */
    }
}