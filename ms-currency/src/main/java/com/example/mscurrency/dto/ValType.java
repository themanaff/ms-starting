package com.example.mscurrency.dto;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ValType")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ValType {

    @XmlAttribute(name = "Type")
    String type;


    @XmlElement(name = "Valute")
    List<Valute> valuteList;

}
