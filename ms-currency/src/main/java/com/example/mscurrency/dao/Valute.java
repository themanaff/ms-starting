package com.example.mscurrency.dao;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Valute {

    @XmlAttribute(name = "Code")
    String code;
    @XmlElement(name = "Nominal")
    String nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    BigDecimal value;

}
