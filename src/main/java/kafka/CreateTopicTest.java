package kafka;


import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;

import java.util.Properties;

/**
 * Created by qls on 18-5-19.
 */
public class CreateTopicTest {


    private static final String ZK_ADDR =  "localhost:2181";
    private static final String TOPIC   =  "t1";
    private static final int PARTITIONS   =  3;
    private static final int REPLICATIONS   =  1;

    public static void main(String[] args){

        ZkUtils zkUtils = ZkUtils.apply(ZK_ADDR, 30000, 30000, false);
        AdminUtils.createTopic(zkUtils, TOPIC, PARTITIONS, REPLICATIONS, new Properties());
        zkUtils.close();
    }

}
