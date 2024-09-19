package ir.tamin.infra.ksp.service.kafka;

import java.io.Serializable;

public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long alertId;
    private String stageId;
    private String alertLevel;
    private String alertMessage;

    public Alert(){

    }
    public Alert(Long alertId, String stageId, String alertLevel, String alertMessage) {
        this.alertId = alertId;
        this.stageId = stageId;
        this.alertLevel = alertLevel;
        this.alertMessage = alertMessage;
    }

    public Long getAlertId() {
        return alertId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }
}
