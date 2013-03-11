package com.sjsu.bikelet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ckempaiah
 * Date: 3/7/13
 * Time: 9:38 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PendingBillProjection implements Serializable{
    @Id
    Long userId;
    @Id
    Long monthId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMonthId() {
        return monthId;
    }

    public void setMonthId(Long monthId) {
        this.monthId = monthId;
    }
}
