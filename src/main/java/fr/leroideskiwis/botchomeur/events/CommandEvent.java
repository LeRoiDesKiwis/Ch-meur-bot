package fr.leroideskiwis.botchomeur.events;

import fr.leroideskiwis.botchomeur.commands.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandEvent extends ListenerAdapter {

    private CommandManager commandManager;

    public CommandEvent(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        commandManager.execute(event);
    }
}
