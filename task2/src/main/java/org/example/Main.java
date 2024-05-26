package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;

public class Main {
    public static void main(String[] args) throws InterruptedException, DummyException, IOException {


        try (var connection = new ConnectionImpl()) {

            try (var session = connection.createSession(true)) {
                connection.start();

                var destination = session.createDestination("testQueue");

                var producer = session.createProducer(destination);

                List<String> allLines = Files.readAllLines(Paths.get(args[0]));

                var index = 0;

                do {
                    var line = allLines.get(index);
                    producer.send(line);
                    Thread.sleep(2000);
                    index += 1;
                    index = index % allLines.size();
                }
                while (true);
            }
        }
    }
}
//приостановить вывод не получилось :( 