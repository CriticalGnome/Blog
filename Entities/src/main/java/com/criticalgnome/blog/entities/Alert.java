package com.criticalgnome.blog.entities;

import lombok.*;

/**
 * Project Blog
 * Created on 26.03.2017.
 *
 * @author CriticalGnome
 */
@Data
@AllArgsConstructor
public class Alert {
    private String alertClass;
    private String alertMessage;
}
