package fr.leroideskiwis.botchomeur.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HelpCommand extends CommandExecutor {

    private Map<String, CommandExecutor> commands;

    public HelpCommand(Map<String, CommandExecutor> commands) {
        super("Donne les informations sur chaque commande");
        this.commands = commands;
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.YELLOW);
        embedBuilder.setTitle("Aide (il y a "+commands.size()+" commandes).");
        for(Map.Entry<String, CommandExecutor> command : commands.entrySet()){
            CommandExecutor commandExecutor = command.getValue();
            embedBuilder.addField(
                    (commandExecutor.hasPermission(event.getGuild(), event.getMember()) ? "" : "(ADMIN ONLY) ")
                    + command.getKey(),
                    commandExecutor.description, false);
        }

        event.getTextChannel().sendMessageEmbeds(embedBuilder.build()).queue();

    }
}
