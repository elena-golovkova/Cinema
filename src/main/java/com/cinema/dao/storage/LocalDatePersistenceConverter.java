package com.cinema.dao.storage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/*
 <dependency>
 <groupId>org.hibernate.javax.persistence</groupId>
 <artifactId>hibernate-jpa-2.1-api</artifactId>
 <version>1.0.0.Final</version>
 </dependency>
 */

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

   @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
