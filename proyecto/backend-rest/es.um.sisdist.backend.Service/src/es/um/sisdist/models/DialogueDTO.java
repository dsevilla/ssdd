package es.um.sisdist.models;

import java.util.Date;

import es.um.sisdist.backend.dao.models.Dialogue;

public class DialogueDTO {
    private String dialogueId;
    private String prompt;
    private String answer;
    private Date creationDate;
    private Date answerDate;

    
    public DialogueDTO(String dialogueId, String prompt, String answer, Date creationDate, Date answerDate) {
        this.dialogueId = dialogueId;
        this.prompt = prompt;
        this.answer = answer;
        this.creationDate = creationDate;
        this.answerDate = answerDate;
    }
    
    public String getDialogueId() {
        return dialogueId;
    }
    
    public void setDialogueId(String dialogueId) {
        this.dialogueId = dialogueId;
    }
    
    public String getPrompt() {
        return prompt;
    }
    
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Date getcreationDate() {
        return creationDate;
    }
    
    public void setcreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }
    
    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }
    
    public static DialogueDTO toDTO(Dialogue dialog){
        if (dialog == null) {
            return null;
        }
        return new DialogueDTO(dialog.getId(), dialog.getPrompt(), dialog.getAnswer(), dialog.getCreationDate(), dialog.getAnswerDate());
    }

    
    
    @Override
    public String toString() {
        return "DialogueDTO [dialogueId=" + dialogueId + ", prompt=" + prompt + ", answer=" + answer + ", creationDate="
                + creationDate + ", answerDate=" + answerDate + "]";
    }

    public DialogueDTO(){};
}
