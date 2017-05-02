package com.criticalgnome.blog.entities;

import lombok.*;

/**
 * Util entity for Bootstrap alert window
 *
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
public class Alert {

    /**
     * Css class for alert window
     * may contains:
     * "alert-success"
     * "alert-info"
     * "alert-warning"
     * "alert-danger"
     */
    private String alertClass;

    /**
     * Text message for alert body
     */
    private String alertMessage;
}
