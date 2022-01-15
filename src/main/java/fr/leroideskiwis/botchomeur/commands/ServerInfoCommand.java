package fr.leroideskiwis.botchomeur.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServerInfoCommand extends CommandExecutor {

    public ServerInfoCommand(){
        super("Pour obtenir des informations sur le serveur");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        Guild guild = event.getGuild();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        event.getGuild().loadMembers().onSuccess(members -> {
            int total = members.size();
            //TODO int onlineMembers = countMembers(members, member -> member.getOnlineStatus() != OnlineStatus.OFFLINE);
            int botMembers = countMembers(members, member -> member.getUser().isBot());

            embedBuilder.setTitle("Informations sur "+guild.getName())
                    .setThumbnail(guild.getIconUrl())
                    .addField("Nombre de membres", String.valueOf(total), false)
                    //.addField("Membres connectés", String.valueOf(onlineMembers), false)
                    .addField("Nombre de bots", String.valueOf(botMembers), false);
            event.getTextChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        });
    }

    private int countMembers(List<Member> members, Predicate<Member> predicate){
        return members.stream().filter(predicate).toList().size();
    }
}
