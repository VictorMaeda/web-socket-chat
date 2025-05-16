package projeto.chatweb.model;


import java.time.LocalTime;
import java.util.Date;
public class Message {
    public Message(String text, String username){
        this.message=text;
        this.username=username;
        this.horario = LocalTime.now();
    }
    private String message;
    private String username;
    private LocalTime  horario;

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
    public LocalTime  getHorario() {
        return horario;
    }
}
