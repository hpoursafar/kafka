package ir.tamin.infra.ksp.model.notification;

import ir.tamin.framework.domain.BaseEntity;
import ir.tamin.framework.ws.rest.repository.annotation.RESTResource;

import javax.persistence.*;

/**
 * Created by s_tayari on 9/13/2018.
 */
/*@Entity
@Table(name = "CLSU_NOTIFICATION")*/
@RESTResource
public class Notification extends BaseEntity<Long> {

    @Id
    @Column(name = "NOTIFICATION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "READ")
    private Boolean read;

    @Enumerated(EnumType.ORDINAL)
    private NotificationSeverity severity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public NotificationSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(NotificationSeverity severity) {
        this.severity = severity;
    }

    public Notification forUser(String username) {
        this.username = username;
        return this;
    }

    public Notification withMessage(String message) {
        this.message = message;
        return this;
    }

    public Notification withSeverity(NotificationSeverity severity) {
        this.severity = severity;
        return this;
    }

    @Override
    public Long getIdentifierInstance() {
        return id;
    }
}
