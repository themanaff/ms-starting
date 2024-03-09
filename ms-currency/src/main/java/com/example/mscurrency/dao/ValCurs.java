package com.example.mscurrency.dao;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ValCurs {

    @XmlAttribute(name = "Date")
    String date;

    @XmlAttribute(name = "Name")
    String name;

    @XmlAttribute(name = "Description")
    String description;

    @XmlElement(name = "ValType")
    List<ValType> typeList;

}
