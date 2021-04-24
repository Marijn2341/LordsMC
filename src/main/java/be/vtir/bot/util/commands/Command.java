package be.vtir.bot.util.commands;

import lombok.Getter;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
    @Getter String name, description;
    @Getter Permission permission;
    @Getter ArrayList<String> aliases;

    public Command(String name, Permission perm, String description, String... aliases) {
        this.name = name;
        this.permission = perm;
        this.description = description;
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    public abstract void onCommand(Command cmd, String[] args, Message msg);
}
