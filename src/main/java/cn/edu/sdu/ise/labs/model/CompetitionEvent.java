package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

@Data
public class CompetitionEvent {
    private Integer id;

    private String competitionEventCode;

    private String competitionEventName;

    private Integer suiteType;

    private String rangeCode;

    private Date planStartAt;

    private Date planEndAt;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    private String updatedBy;

}