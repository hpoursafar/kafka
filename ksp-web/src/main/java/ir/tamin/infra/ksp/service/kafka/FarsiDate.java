package ir.tamin.infra.ksp.service.kafka;

import ir.tamin.framework.core.util.DateUtils;

import java.util.Date;

public class FarsiDate {

    public String convertDate(Date date){
        return DateUtils.format(date,"yyyyMMdd");
    }
}
