package ir.tamin.infra.ksp.service.kafka.producer;

//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import com.ibm.kafka.clients.producer.KafkaProducer;
//import com.ibm.kafka.clients.producer.ProducerRecord;
//import com.ibm.kafka.common.errors.SerializationException;
//import com.ibm.kafka.common.serialization.StringSerializer;
//import com.kafka.notify.models.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import spark.Request;
//import spark.Response;
//import spark.Spark;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;

public class MessageProducer {
//    @KafkaListener(id = "user", topics = { "sports-news", "tech-news", "entertainment-news" })
    public void listen(String news) {
        System.out.println("*******NEWS********");
        System.out.println(news);
    }
//    private static final String PRODUCER_PORT = ":8080";
//    private static final String KAFKA_SERVER_ADDRESS = "localhost:9092";
//    private static final String KAFKA_TOPIC = "notifications";
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);
//
//    private static final String ERR_USER_NOT_FOUND_IN_PRODUCER = "user not found";
//
//    public static void main(String[] args) {
//        Spark.port(Integer.parseInt(PRODUCER_PORT.substring(1)));
//
//        List<User> users = new ArrayList<>();
//        // Initialize users list
//
//        Spark.post("/notify", (Request request, Response response) -> {
//            int userId;
//            try {
//                userId = getIDFromRequest("userId", request);
//            } catch (NumberFormatException e) {
//                logger.error("Failed to parse ID from form value 'userId': {}", e.getMessage());
//                response.status(400);
//                return "Failed to parse ID from form value 'userId'";
//            }
//
//            User user;
//            try {
//                user = findUserByID(userId, users);
//            } catch (IllegalArgumentException e) {
//                logger.error(ERR_USER_NOT_FOUND_IN_PRODUCER);
//                response.status(404);
//                return ERR_USER_NOT_FOUND_IN_PRODUCER;
//            }
//
//            String message = request.queryParams("message");
//            if (message == null || message.isEmpty()) {
//                logger.error("Message is required");
//                response.status(400);
//                return "Message is required";
//            }
//
//            sendNotificationToKafka(user, message);
//            return "Notification sent";
//        });
//    }
//
//    private static User findUserByID(int id, List<User> users) {
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        throw new IllegalArgumentException(ERR_USER_NOT_FOUND_IN_PRODUCER);
//    }
//
//    private static void sendNotificationToKafka(User user, String message) {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", KAFKA_SERVER_ADDRESS);
//        props.put("key.serializer", StringSerializer.class.getName());
//        props.put("value.serializer", StringSerializer.class.getName());
//
//        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
//
//        Gson gson = new Gson();
//        String userJson = gson.toJson(user);
//
//        ProducerRecord<String, String> record = new ProducerRecord<>(KAFKA_TOPIC, userJson, message);
//        producer.send(record);
//        producer.flush();
//        producer.close();
//    }
}
