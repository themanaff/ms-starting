package com.example.mscurrency.service;

import com.example.mscurrency.client.CurrencyClient;
import com.example.mscurrency.dao.ValCurs;
import com.example.mscurrency.dao.ValType;
import com.example.mscurrency.dao.Valute;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CurrencyService {
    CurrencyClient currencyClient;

    public String getCurrencyData(String date){
        return currencyClient.getCurrencyData(date);
    }

    public BigDecimal getValueFromXml(String code , String date){
        ValCurs valCurs = xmlToJavaObject(date);
        ValType type = valCurs.getTypeList().stream()
                .filter(valType -> valType.getType().equals("Xarici valyutalar") ).findFirst().get();
        Valute userInputCode = type.getValuteList().stream()
                .filter(valute -> valute.getCode().equals(code))
                .findFirst().orElseThrow(()->{throw new RuntimeException("No '" + code + "' code found in xml format");});
        return userInputCode.getValue().divide(BigDecimal.valueOf(Long.parseLong(userInputCode.getNominal())));
    }

    @SneakyThrows
    private ValCurs xmlToJavaObject(String date) {
        JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(getCurrencyData(date)));

        ValType type = valCurs.getTypeList().stream()
                .filter(valType -> valType.getType().equals("Xarici valyutalar"))
                .findFirst()
                .orElse(null);

        if (type != null) {
            type.getValuteList().add(Valute.builder()
                    .code("AZN")
                    .nominal("1")
                    .name("1 Azerbaycan manati")
                    .value(BigDecimal.ONE)
                    .build());
        }
            return valCurs;
        }

    }
