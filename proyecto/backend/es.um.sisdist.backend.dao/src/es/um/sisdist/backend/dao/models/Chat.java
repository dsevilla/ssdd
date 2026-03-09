package es.um.sisdist.backend.dao.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import es.um.sisdist.backend.dao.models.utils.ChatStatus;
import es.um.sisdist.backend.dao.models.utils.DateUtils;

public class Chat {
    @BsonId
    private String id; // no cambia, es el identificador único de la conversación
    @BsonProperty("user_id")
    private String user_id; // no cambia, es el usuario que inicia la conversación
    private ChatStatus status; // READY, BUSY, FINISHED
    private String name; // no cambia, es el nombre de la conversación 
    
    private List<Dialogue> conversation; // cambia, es la lista de diálogos de la conversación
    
    private Date creationDate; // no cambia, es la fecha de creación de la conversación
    private Date lastUpdateDate; // cambia, es la fecha de la última actualización de la conversación
    
    private String nextToken; 
    private String endUrl; 
    
    public Chat(String id, String user_id, String name, ChatStatus status, List<Dialogue> conversation) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.status = status;

        // Si la conversación es nula, inicializarla como una lista vacía
        this.conversation = (conversation != null) ? conversation : new ArrayList<>();
        this.creationDate = DateUtils.getCurrentDateISO();
        this.lastUpdateDate = DateUtils.getCurrentDateISO();
        this.nextToken = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

    public Chat(String user_id, String name, ChatStatus status, List<Dialogue> conversation) {
        this(UUID.randomUUID().toString(), user_id, name, status, conversation);
    }

    public Chat(String user_id, String name, ChatStatus status) {
        this(UUID.randomUUID().toString(), user_id, name, status, null);
    }
    
    // Constructor por defecto para mongoDB
    public Chat() {}
    
    
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

    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ChatStatus getStatus() {
        return status;
    }
    
    public void setStatus(ChatStatus status) {
        this.status = status;
    }

    public List<Dialogue> getConversation() {
        return conversation;
    }
    
    public void setConversation(List<Dialogue> conversation) {
        this.conversation = conversation;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    public String getNextToken() {
        return nextToken;
    }
    
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    public String newNextToken(){
        String newToken = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        this.nextToken = newToken;
        return newToken;
    }

    public void addDialogue(Dialogue dialogue){
        this.conversation.add(dialogue);
    }
    
    public String getEndUrl() {
        return endUrl;
    }
    
    public void setEndUrl(String endUrl) {
        this.endUrl = endUrl;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", status=" + status +
                ", conversation=" + conversation +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", nextToken='" + nextToken + '\'' +
                ", endUrl='" + endUrl + '\'' +
                '}';
    }   
}
