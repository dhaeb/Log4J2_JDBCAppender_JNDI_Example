package com.github.dhaeb;

import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Duration;
/**
 * make sure to enable:
 * -Dlog42.configuration=log4j2.xml
 * -Dlog4j2.enableJndiJdbc=true
 * as VM Properties!
 *
 * */
public class Main {

    // https://howtodoinjava.com/log4j2/jdbcappender-example/
    public static void main(String[] args) throws Exception {

        val logger = LogManager.getRootLogger();
        logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j2.configuration"));
        val duration = Duration.ofSeconds(100);
        val producer = new RandomLogProducer(100, duration, "this logs are flying through JNDI");
        producer.scheduleLogCreationNow();
        Thread.sleep(duration.toMillis());
        producer.cancelAll();
    }
}
