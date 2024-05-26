package org.example;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;

import java.awt.font.TextMeasurer;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException,DummyException {

        try (var connection = new ConnectionImpl()) {

            connection.start();

            try (var session = connection.createSession(true)) {

                var destination = session.createDestination("testQueue");

                var producer = session.createProducer(destination);

                var messages = new String[]{
                        "Четыре", "Пять", "Шесть"
                };

                for (String message : messages) {
                    producer.send(message);
                    Thread.sleep(2000);
                }

            }
        }


    }
}

