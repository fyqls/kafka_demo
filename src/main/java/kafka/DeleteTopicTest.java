package kafka;


import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;

/**
 * Created by qls on 18-5-19.
 */
public class DeleteTopicTest {


    private static final String ZK_ADDR =  "localhost:2181";
    private static final String TOPIC   =  "t1";

    public static void main(String[] args){

        ZkUtils zkUtils = ZkUtils.apply(ZK_ADDR, 30000, 30000, false);
        AdminUtils.deleteTopic(zkUtils, TOPIC);
        zkUtils.close();

    }

}
