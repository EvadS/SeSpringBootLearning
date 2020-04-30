package com.se.date.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/datetime/")
final class DateTimeController {

    Logger logger = LoggerFactory.getLogger(DateTimeController.class);

    @ApiOperation(value = "Base converter", response = String.class)
    @RequestMapping(value = "date", method = RequestMethod.POST)
    public void processDate(@RequestParam("date") LocalDate date) {
        //Do stuff
        int a =10;
        logger.info("date : {}", date );
        //ConversionFailedException
    }

    @ApiOperation(value = "Base date time converter", response = String.class)
    @RequestMapping(value = "datetime", method = RequestMethod.POST)
    public void processDateTime(@RequestParam("datetime") LocalDateTime dateAndTime) {
        //Do stuff
        int a =10;
        logger.info("date : {}", dateAndTime );
        // TODO: Error
    }

    @ApiOperation(value = "How to use t to use the ISO 8601 date format (yyyy-MM-dd)", response = String.class)
    @RequestMapping(value = "correct-date-iso", method = RequestMethod.POST)
    public ResponseEntity processDate2(@RequestParam(name="date", defaultValue = "2020-10-10")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        //Do stuff
        int a =10;
        logger.info("date : {}", date );

        return  ResponseEntity.ok().body(date.toString());
    }

    @ApiOperation(value = "How to use own  date format (dd.MM.yyyy)", response = String.class)
    @RequestMapping(value = "correct-date-format", method = RequestMethod.POST)
    public ResponseEntity processDateFormat(@RequestParam(  name="date",defaultValue = "dd.MM.yyyy")
                            @DateTimeFormat(pattern = "10.10.2010") LocalDate date) {
        //Do stuff
        int a =10;
        logger.info("date : {}", date );

        return  ResponseEntity.ok().build();
    }

    @ApiOperation("generate Local date Time ")
    @GetMapping(value = "generate")
    public  String generate(){
//        yyyy-MM-dd’T’HH:mm:ss.SSSZ

  LocalDateTime localDateTime = LocalDateTime.now();
  return DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
    }

    @ApiOperation(value = "We want to use the ISO 8601 date and time format (yyyy-MM-dd’T’HH:mm:ss.SSSZ)", response = String.class)
    @RequestMapping(value = "correct-datetime3", method = RequestMethod.POST)
    public void processDateTime3(
            @RequestParam(name = "datetime",defaultValue="2017-02-17T04:23:17.452Z")
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateAndTime) {
        int a =10;
        logger.info("date : {}", dateAndTime );
    }

    @ApiOperation(value = "We want to use the ISO 8601 date and time format (dd.MM.yyyy HH:mm:ss.SSS)", response = String.class)
    @RequestMapping(value = "correct-datetime4", method = RequestMethod.POST)
    public void processDateTime4(@RequestParam(name = "datetime", defaultValue = "19.12.2010 04:23:17.452Z")
                                @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss.SSS") LocalDateTime dateAndTime) {
        //Do stuff
        int a =10;
        logger.info("date : {}", dateAndTime );

    }
}
