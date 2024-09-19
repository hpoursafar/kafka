package ir.tamin.infra.ksp.model.notification;

import java.io.Serializable;
import java.util.List;

/**
 * Created by s_homayooni on 7/20/2019.
 */
public class NotificationModel implements Serializable {

    public enum NotificationTypeEnum {

        INFO("0", NotificationSeverity.INFO), WARN("1", NotificationSeverity.WARN), ERROR("2", NotificationSeverity.ERROR);

        private String code;
        private NotificationSeverity notificationSeverity;

        NotificationTypeEnum(String code, NotificationSeverity notificationSeverity) {
            this.code = code;
            this.notificationSeverity = notificationSeverity;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public NotificationSeverity getNotificationSeverity() {
            return notificationSeverity;
        }

        public void setNotificationSeverity(NotificationSeverity notificationSeverity) {
            this.notificationSeverity = notificationSeverity;
        }

        public static NotificationTypeEnum find(String code) {
            for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
                if(notificationTypeEnum.getCode().equals(code)) {
                    return notificationTypeEnum;
                }
            }

            throw new IllegalArgumentException("Couldn't find a NotificationTypeEnum for code " + code);
        }
    }



    String username;
    String messageKey;
    List<Object> params;
    String notificationType;

    public NotificationModel(String notificationType, String username, String messageKey, List<Object> params) {
        this.username = username;
        this.messageKey = messageKey;
        this.params = params;
        this.notificationType = notificationType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
