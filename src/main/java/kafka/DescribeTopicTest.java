package kafka;

import kafka.admin.AdminUtils;
import kafka.api.PartitionMetadata;
import kafka.api.TopicMetadata;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import scala.collection.Seq;

import java.util.*;

/**
 * Created by qls on 18-5-19.
 */
public class DescribeTopicTest {

    private static final String ZK_ADDR =  "localhost:2181";
    private static final String TOPIC   =  "t1";

    public static void main(String[] args) {

        ZkUtils zkUtils = ZkUtils.apply(ZK_ADDR, 3000, 3000, false);
        TopicMetadata topicMetadata= AdminUtils.fetchTopicMetadataFromZk(TOPIC, zkUtils);

        Seq<PartitionMetadata> partitionMetadataSeq = topicMetadata.partitionsMetadata();
        List<PartitionMetadata> partitionMetadataList = scala.collection.JavaConversions.seqAsJavaList(partitionMetadataSeq);

        System.out.println("Kafka Topic: " + String.valueOf(topicMetadata.topic()));
        for(PartitionMetadata partitionMetadata : partitionMetadataList){
            System.out.println(partitionMetadata.toString());
        }

        zkUtils.close();

    }
}
