package com.example.mscurrency.dto;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class Valute {

    @XmlAttribute(name = "Code")
    String code;
    @XmlElement(name = "Nominal")
    String nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    BigDecimal price;

}
