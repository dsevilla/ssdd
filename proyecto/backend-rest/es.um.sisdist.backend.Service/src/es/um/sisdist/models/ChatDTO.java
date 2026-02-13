package es.um.sisdist.models;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.LinkedList;
import java.util.List;

import es.um.sisdist.backend.dao.models.Chat;
import es.um.sisdist.backend.dao.models.utils.ChatStatus;

@XmlRootElement
public class ChatDTO {

    private String id;
    private String name;
    private String nextUrl;
    private ChatStatus status;

    private List<DialogueDTO> conversation;
    
    public ChatDTO(String id, String name, String next, ChatStatus status){
        this.id = id;
        this.name = name;
        this.nextUrl = next;
        this.status = status;
        this.conversation = new LinkedList<>();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public ChatStatus getStatus() {
        return status;
    }

    public void setStatus(ChatStatus status) {
        this.status = status;
    }

    public static ChatDTO toDTO(Chat chat) {
        if (chat == null) {
            return null;
        }
        return new ChatDTO(chat.getId(), chat.getName(), chat.getNextToken(), chat.getStatus());
    }

    public List<DialogueDTO> getconversation() {
        return conversation;
    }

    public void addDialogue(DialogueDTO nuevo){
        this.conversation.add(nuevo);
    }
    

    @Override
    public String toString() {
        return "ChatDTO [id=" + id + ", name=" + name + ", nextUrl=" + nextUrl + ", status=" + status + ", conversation="
                + conversation + "]";
    }

    public ChatDTO(){};
}
