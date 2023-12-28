package az.digital.crystalabsheronapp.rabbitmq.publisher;

import az.digital.crystalabsheronapp.dao.repository.UserRepository;
import az.digital.crystalabsheronapp.dto.UserDto;
import az.digital.crystalabsheronapp.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    private RabbitTemplate rabbitTemplate;
    private final UserRepository userRepository;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate, UserRepository userRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.userRepository = userRepository;
    }

    public void sendJsonMessage(UserDto user) {
        userRepository.findById(user.getId()).
                orElseThrow(()->new UserNotFoundException("This user not found"));
        LOGGER.info(String.format("Json message  sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }

}
