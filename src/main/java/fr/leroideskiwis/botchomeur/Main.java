package fr.leroideskiwis.botchomeur;

import fr.leroideskiwis.botchomeur.commands.CommandManager;
import fr.leroideskiwis.botchomeur.commands.ServerInfoCommand;
import fr.leroideskiwis.botchomeur.commands.ShitPostCommand;
import fr.leroideskiwis.botchomeur.commands.SuggestCommand;
import fr.leroideskiwis.botchomeur.events.CommandEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    private JDA jda;
    private CommandManager commandManager = new CommandManager();

    public Main(String token) throws LoginException {
        this.jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).enableIntents(GatewayIntent.GUILD_PRESENCES).build();
        commandManager.addCommand("shitpost", new ShitPostCommand());
        commandManager.addCommand("suggest", new SuggestCommand());
        commandManager.addCommand("serverinfo", new ServerInfoCommand());
        jda.addEventListener(new CommandEvent(commandManager));
    }

    public static void main(String[] args) throws LoginException {
        new Main(args[0]);
    }

}
