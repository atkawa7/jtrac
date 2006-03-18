/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.jtrac.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Generic Item object that can represent a defect, task, etc.
 * An Item is customized by the <code>Metadata</code> of
 * the <code>Space</code> that it belongs to.
 */
public class Item implements Serializable {
    
    private long id;
    private long sequenceNum;
    private Integer type;
    private Item parent;
    private Space space;
    private String summary;
    private String detail;
    private User loggedBy;
    private User assignedTo;
    private Date timeStamp;
    private Double plannedEffort;
    //===========================
    private Integer status;
    private Integer severity;
    private Integer priority;
    private Integer cusInt01;
    private Integer cusInt02;
    private Integer cusInt03;
    private Integer cusInt04;
    private Integer cusInt05;
    private Integer cusInt06;
    private Integer cusInt07;
    private Integer cusInt08;
    private Integer cusInt09;
    private Integer cusInt10;
    private Double cusDbl01;
    private Double cusDbl02;
    private Double cusDbl03;
    private String cusStr01;
    private String cusStr02;
    private String cusStr03;
    private String cusStr04;
    private String cusStr05;
    private Date cusTim01;
    private Date cusTim02;
    private Date cusTim03;
    //=========================
    
    private Set history;
    private Set children;
    private Set attachments;    

    public String getStatusText() {
        return space.getStatusText(status);
    }
    
    public String getSeverityText() {
        return space.getSeverityText(severity);
    }
    
    public String getPriorityText() {
        return space.getPriorityText(priority);
    }
    
    public String getCusInt01Text() {
        return space.getCusInt01Text(cusInt01);
    }
    
    public String getCusInt02Text() {
        return space.getCusInt02Text(cusInt02);
    }
    
    public String getCusInt03Text() {
        return space.getCusInt03Text(cusInt03);
    }
    
    public String getCusInt04Text() {
        return space.getCusInt04Text(cusInt04);
    }
    
    public String getCusInt05Text() {
        return space.getCusInt05Text(cusInt05);
    }
    
    public String getCusInt06Text() {
        return space.getCusInt06Text(cusInt06);
    }
    
    public String getCusInt07Text() {
        return space.getCusInt07Text(cusInt07);
    }
    
    public String getCusInt08Text() {
        return space.getCusInt08Text(cusInt08);
    }
    
    public String getCusInt09Text() {
        return space.getCusInt09Text(cusInt09);
    }
    
    public String getCusInt10Text() {
        return space.getCusInt10Text(cusInt10);
    }
    
    //======================================================

    public Integer getStatus() {
        return status;
    }    
    
    public Integer getSeverity() {
        return severity;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getCusInt01() {
        return cusInt01;
    }

    public Integer getCusInt02() {
        return cusInt02;
    }

    public Integer getCusInt03() {
        return cusInt03;
    }

    public Integer getCusInt04() {
        return cusInt04;
    }

    public Integer getCusInt05() {
        return cusInt05;
    }

    public Integer getCusInt06() {
        return cusInt06;
    }

    public Integer getCusInt07() {
        return cusInt07;
    }

    public Integer getCusInt08() {
        return cusInt08;
    }

    public Integer getCusInt09() {
        return cusInt09;
    }

    public Integer getCusInt10() {
        return cusInt10;
    }

    public Double getCusDbl01() {
        return cusDbl01;
    }

    public Double getCusDbl02() {
        return cusDbl02;
    }

    public Double getCusDbl03() {
        return cusDbl03;
    }

    public String getCusStr01() {
        return cusStr01;
    }

    public String getCusStr02() {
        return cusStr02;
    }

    public String getCusStr03() {
        return cusStr03;
    }

    public String getCusStr04() {
        return cusStr04;
    }

    public String getCusStr05() {
        return cusStr05;
    }

    public Date getCusTim01() {
        return cusTim01;
    }

    public Date getCusTim02() {
        return cusTim02;
    }

    public Date getCusTim03() {
        return cusTim03;
    }

    //===============================================================
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }    
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setCusInt01(Integer cusInt01) {
        this.cusInt01 = cusInt01;
    }

    public void setCusInt02(Integer cusInt02) {
        this.cusInt02 = cusInt02;
    }

    public void setCusInt03(Integer cusInt03) {
        this.cusInt03 = cusInt03;
    }

    public void setCusInt04(Integer cusInt04) {
        this.cusInt04 = cusInt04;
    }

    public void setCusInt05(Integer cusInt05) {
        this.cusInt05 = cusInt05;
    }
   
    public void setCusInt06(Integer cusInt06) {
        this.cusInt06 = cusInt06;
    }
 
    public void setCusInt07(Integer cusInt07) {
        this.cusInt07 = cusInt07;
    }

    public void setCusInt08(Integer cusInt08) {
        this.cusInt08 = cusInt08;
    }

    public void setCusInt09(Integer cusInt09) {
        this.cusInt09 = cusInt09;
    }

    public void setCusInt10(Integer cusInt10) {
        this.cusInt10 = cusInt10;
    }

    public void setCusDbl01(Double cusDbl01) {
        this.cusDbl01 = cusDbl01;
    }

    public void setCusDbl02(Double cusDbl02) {
        this.cusDbl02 = cusDbl02;
    }

    public void setCusDbl03(Double cusDbl03) {
        this.cusDbl03 = cusDbl03;
    }

    public void setCusStr01(String cusStr01) {
        this.cusStr01 = cusStr01;
    }

    public void setCusStr02(String cusStr02) {
        this.cusStr02 = cusStr02;
    }

    public void setCusStr03(String cusStr03) {
        this.cusStr03 = cusStr03;
    }

    public void setCusStr04(String cusStr04) {
        this.cusStr04 = cusStr04;
    }

    public void setCusStr05(String cusStr05) {
        this.cusStr05 = cusStr05;
    }

    public void setCusTim01(Date cusTim01) {
        this.cusTim01 = cusTim01;
    }

    public void setCusTim02(Date cusTim02) {
        this.cusTim02 = cusTim02;
    }

    public void setCusTim03(Date cusTim03) {
        this.cusTim03 = cusTim03;
    }        
    
    //=======================================================================
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(long sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public User getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(User loggedBy) {
        this.loggedBy = loggedBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getPlannedEffort() {
        return plannedEffort;
    }

    public void setPlannedEffort(Double plannedEffort) {
        this.plannedEffort = plannedEffort;
    }
    
    public Set getHistory() {
        return history;
    }

    public void setHistory(Set history) {
        this.history = history;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    public Set getAttachments() {
        return attachments;
    }

    public void setAttachments(Set attachments) {
        this.attachments = attachments;
    }
    
}
