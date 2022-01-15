package fr.leroideskiwis.botchomeur.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ShitPostCommand extends CommandExecutor {

    private Random random = new Random();

    public ShitPostCommand(){
        super("Envoie un message au hasard si vous avez la chance d'avoir la permission mdr");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String[] messages = new String[]{"Gros chibre !", "Wlh les chômeurs", "Bravo tu as gagné **RIEN**", "uwu nya", "Yamete kudasai tentacule-senpai uwu~"};
        event.getTextChannel().sendMessage(messages[random.nextInt(messages.length)])
                .queue(message -> message.editMessageFormat(messages[random.nextInt(messages.length)])
                                .queueAfter(3, TimeUnit.SECONDS));
    }

    @Override
    public boolean hasPermission(Guild guild, Member member) {
        return random.nextFloat() > 0.1f;
    }
}
