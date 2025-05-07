package projeto.chatweb.model;


import java.time.LocalTime;
import java.util.Date;
public class Message {
    public Message(String message, String username){
        this.message=message;
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
