package fr.leroideskiwis.botchomeur.commands;

import fr.leroideskiwis.botchomeur.util.MessageHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class SuggestCommand extends CommandExecutor {

    private final long SUGGESTION_CHANNEL = 931990237264117830L;

    public SuggestCommand(){
        super("Faire une suggestion pour le serveur ou le bot");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        StringBuilder content = new StringBuilder();
        for(String arg : args){
            content.append(arg).append(" ");
        }
        EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.YELLOW);
        embedBuilder.setTitle("Suggestion");
        embedBuilder.setDescription(content.toString());
        List<Message.Attachment> attachments = event.getMessage().getAttachments();
        if(attachments.size() >= 1){
            Message.Attachment attachment = attachments.get(0);
            if(attachment.isImage()) embedBuilder.setImage(attachment.getUrl());
        }

        TextChannel suggestionChannel = event.getGuild().getTextChannelById(SUGGESTION_CHANNEL);
        TextChannel textChannel = event.getTextChannel();
        if(suggestionChannel == null) {
            MessageHandler.sendError(textChannel, "Le channel suggestion n'existe pas. Veuillez contacter l'administrateur.");
            return;
        }
        suggestionChannel.sendMessageEmbeds(embedBuilder.build()).queue(message -> {
            message.addReaction("❌").queue();
            message.addReaction("✅").queue();
        });
        textChannel.sendMessage("Suggestion envoyée avec succès :white_check_mark:").queue();
    }
}
