package fr.leroideskiwis.botchomeur.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;

public abstract class CommandExecutor {
    public final String description;

    public CommandExecutor(String description){
        this.description = description;
    }

    public CommandExecutor(){
        this("Aucune description");
    }

    public abstract void execute(MessageReceivedEvent event, String[] args);
    public boolean hasPermission(Guild guild, Member member){
        return true;
    }

}
