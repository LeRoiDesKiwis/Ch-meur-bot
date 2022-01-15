package fr.leroideskiwis.botchomeur.commands;

import fr.leroideskiwis.botchomeur.util.MessageHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, CommandExecutor> commands = new HashMap<>();
    private String prefixe = ";";

    public CommandManager(){
        addCommand("help", new HelpCommand(commands));
    }

    public void addCommand(String command, CommandExecutor commandExecutor){
        commands.put(command, commandExecutor);
        System.out.println("Commande "+command+" chargée !");
    }

    public String[] getArgs(String[] args){
        String[] args1 = new String[args.length-1];
        System.arraycopy(args, 1, args1, 0, args1.length);
        return args1;
    }

    public void execute(MessageReceivedEvent event){
        String content = event.getMessage().getContentDisplay();
        if(content.startsWith(prefixe)){
            String commandContent = content.substring(prefixe.length());
            String[] split = commandContent.split(" ");
            String[] args = getArgs(split);
            String commandName = split[0];

            if(!commands.containsKey(commandName)) return;

            CommandExecutor commandExecutor = commands.get(commandName);
            if(!commandExecutor.hasPermission(event.getGuild(), event.getMember())) {
                MessageHandler.sendError(event.getTextChannel(), "Vous n'avez pas la permission d'exécuter cette commande !");
                return;
            }
            commandExecutor.execute(event, args);
        }
    }
}
