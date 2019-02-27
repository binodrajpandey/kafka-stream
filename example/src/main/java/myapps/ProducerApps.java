package myapps;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class ProducerApps
{
    public static Logger logger = LoggerFactory.getLogger(ProducerApps.class);

    public static void main(String[] args)
    {
        Properties properties = new Properties();
        String topicName = StringConstant.INPUT_TOPIC_NAME;

        try
        {
            InputStream input = new FileInputStream(StringConstant.PRODUCER_CONFIG_LOCATION);
            properties.load(input);
            Producer<String, String> producer = new KafkaProducer<>(properties);

            Scanner scanner=new Scanner(System.in);
            logger.info("Enter string: ");
            String str=scanner.next();
                try
                {
                    producer.send(new ProducerRecord<>(topicName, str));

                }
                catch (Exception ex)
                {
                    logger.error("Could not send value you have entered:");
                }

            producer.close();
        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }

    }
}
