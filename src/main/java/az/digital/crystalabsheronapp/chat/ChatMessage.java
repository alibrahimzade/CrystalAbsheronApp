package az.digital.crystalabsheronapp.chat;

import az.digital.crystalabsheronapp.chat.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
