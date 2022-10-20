package com.github.dhaeb;

import lombok.Value;
import lombok.val;

import java.time.Duration;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.LongStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Value
public class RandomLogProducer {

    /*
     * For more info, see:
     * https://stackoverflow.com/questions/37970682/passing-lambda-to-a-timer-instead-of-timertask
     */
    private static TimerTask wrap(Runnable r) {
        return new TimerTask() {

            @Override
            public void run() {
                r.run();
            }
        };
    }

    private static final Logger logger = LogManager.getLogger(RandomLogProducer.class);

    private int amountLogs;
    private Duration timeDuration;
    private String logs;

    private Timer timer = new Timer();

    public RandomLogProducer(int amountLogs, Duration timeDuration, String logs){
        this.amountLogs = amountLogs;
        this.timeDuration = timeDuration;
        this.logs = logs;
    }

    private void createLog(){
        logger.debug(logs);
    }

    void scheduleLogCreationNow(){
        val random = new Random();
        val millisTotal = timeDuration.toMillis();
        val currentTimeMillis = System.currentTimeMillis();
        LongStream.range(0, amountLogs).map(i -> random.nextLong(millisTotal))
                                      .forEach(delayForTheLogToSpawn -> {
                                          timer.schedule(wrap(this::createLog), delayForTheLogToSpawn);
                                      });

    }

    public void cancelAll(){
        timer.cancel();
    }
}
