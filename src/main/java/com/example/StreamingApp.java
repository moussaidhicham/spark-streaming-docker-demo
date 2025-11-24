package com.example;

import org.apache.spark.SparkConf;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class StreamingApp {
    public static void main(String[] args) throws InterruptedException {
        // Spark Configuration
        SparkConf conf = new SparkConf()
                .setAppName("SparkStreamingDemo")
                .setMaster("spark://spark-master:7077") // Spark master URL (cluster mode)
                .set("spark.driver.host", "spark-app")   // Driver hostname (used in Docker or Kubernetes)
                .set("spark.driver.bindAddress", "0.0.0.0") // Bind to all interfaces
                .set("spark.executor.memory", "512m")    // Memory settings
                .set("spark.driver.memory", "512m");

        // Create Streaming Context with 1-second batch interval
        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(1));

        // Reduce log level to show only warnings and errors
        jssc.sparkContext().setLogLevel("WARN");

        System.out.println("Creating socket stream...");

        // Read from a TCP socket (host: producer, port: 9999)
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream(
                "producer",
                9999,
                StorageLevel.MEMORY_AND_DISK_SER_2()
        );

        System.out.println("Setting up transformations...");

        // Convert input strings to integers
        JavaDStream<Integer> numbers = lines.map(s -> {
            System.out.println("Received: " + s);
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse: " + s);
                return 0;
            }
        });

        // Compute the square of each number
        JavaDStream<Integer> squares = numbers.map(x -> {
            int result = x * x;
            System.out.println("Calculated square: " + x + " -> " + result);
            return result;
        });

        // Print the results to console
        squares.print();

        System.out.println("Starting streaming context...");
        jssc.start();
        jssc.awaitTermination();
    }
}
