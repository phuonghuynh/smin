package com.smin.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * Created by phuonghqh on 3/4/15.
 */
public class StringStreamTask extends Thread {

  private final Logger LOGGER = LoggerFactory.getLogger(StringStreamTask.class);

  private BufferedReader inputStream;

  private Consumer<String> consumer;

  public StringStreamTask(InputStream inputStream, Consumer<String> consumer) {
    this.consumer = consumer;
    this.inputStream = new BufferedReader(new InputStreamReader(inputStream));
  }

  public void run() {
    try {
      String line;
      while ((line = inputStream.readLine()) != null) {
        LOGGER.debug("Read: " + line);
        consumer.accept(line);
      }
      inputStream.close();
    }
    catch (Exception e) {
      LOGGER.error("Error: ", e);
    }
  }
}
