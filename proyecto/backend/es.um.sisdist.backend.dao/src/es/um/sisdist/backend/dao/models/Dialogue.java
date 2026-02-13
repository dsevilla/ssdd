package es.um.sisdist.backend.dao.models;

import java.util.Date;
import java.util.UUID;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import es.um.sisdist.backend.dao.models.utils.DateUtils;

public class Dialogue {

    @BsonId
    private String id;
    @BsonProperty(value="chat_id")
    private String chat_id;
    @BsonProperty("prompt")
    private String prompt;
    @BsonProperty("creationDate")
    private Date creationDate;
    @BsonProperty("answer")
    private String answer;
    @BsonProperty("answerDate")
    private Date answerDate;
    

    public Dialogue() {}

    public Dialogue(String id, String chat_id, String prompt, String answer, Date creationDate, Date ansDate){
        this.id = id;
        this.chat_id = chat_id;
        this.prompt = prompt;
        this.answer = answer;
        this.creationDate = creationDate;
        this.answerDate = ansDate;
    }

    public Dialogue(String id, String chat_id, String prompt, String answer) {
        this(id, chat_id, prompt, answer, DateUtils.getCurrentDateISO(), null);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;  
    }

    /**
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }
    /**
     * @return the chat_id
     */
    public String getChat_id() {
        return chat_id;      
    }
    /**
     * @param chat_id the chat_id to set
     */
    public void setChat_id(final String chat_id) {
        this.chat_id = chat_id;
    }

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }   
    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }
    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }
    /**
     * @param answer the answer to set
     */
    public void setAnswer(final String answer) {
        this.answer = answer;
    }
    /**
     * @return the answerDate
     */
    public Date getAnswerDate() {
        return answerDate;
    }

    /**
     * @param answerDate the answerDate to set
     */
    public void setAnswerDate(final Date answerDate) {
        this.answerDate = answerDate;
    }
    
    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + id + '\'' +
                ", chat_id='" + chat_id + '\'' +
                ", prompt='" + prompt + '\'' +
                ", creationDate=" + creationDate +
                ", answer='" + answer + '\'' +
                ", answerDate=" + answerDate +
                '}';
    }
}
