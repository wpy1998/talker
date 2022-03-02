package Client;

import Client.Entity.NetworkTopology;
import Client.Entity.StreamHeader;
import Client.Hardware.Computer;
import Client.HttpInfo.GetInfo;
import Client.HttpInfo.PostInfo;
import Client.HttpInfo.PutInfo;
import com.alibaba.fastjson.JSONObject;
import net.juniper.netconf.Device;
import net.juniper.netconf.XML;
import org.xml.sax.SAXException;

import java.io.IOException;

import static Client.Hardware.Computer.device_ip;
import static Client.Hardware.Computer.host_name;

public class ClientApp {
    public static final String cuc_ip = "192.168.1.16";
    public static void main(String[] args) throws IOException, SAXException {
        Computer computer = new Computer();
        computer.refresh();
        System.out.println(computer.device_ip + ", " + computer.device_mac);

//        String url = "http://" + destionation_ip + ":8181/restconf/operations/talker:";
//        PostInfo postInfo = PostInfo.builder().url(url + "join").build();
//        StreamHeader header = new StreamHeader();
//        JSONObject stream_header = header.getJSONObject(false, true,
//                false, false, false,
//                false, false);
//        JSONObject stream = new JSONObject();
//        JSONObject input = new JSONObject();
//        stream.put("header", stream_header);
//        stream.put("body", "test join");
//        input.put("input", stream);
//        postInfo.postInfo(input.toString());
//        GetInfo getInfo = GetInfo.builder().url(url + "test").build();
//        getInfo.getInfo();
//        PostInfo postInfo1 = PostInfo.builder().url(url + "leave").build();
//        postInfo1.postInfo(input.toString());

        PutInfo putInfo = PutInfo.builder()
                .url("http://" + cuc_ip + ":8181/restconf/config/network-topology:network-topology" +
                        "/topology/topology-netconf/node/" + host_name + "/").build();
        JSONObject node = new NetworkTopology().buildNode_host();
        JSONObject input1 = new JSONObject();
        input1.put("urn:TBD:params:xml:ns:yang:network-topology:node", node);
        System.out.println(input1.toString());
        putInfo.putInfo(input1.toString());
    }
}

/*
<node xmlns="urn:TBD:params:xml:ns:yang:network-topology">
  <node-id>ubuntu</node-id>
  <host xmlns="urn:opendaylight:netconf-node-topology">127.0.0.1</host>
  <port xmlns="urn:opendaylight:netconf-node-topology">17830</port>
  <username xmlns="urn:opendaylight:netconf-node-topology">admin</username>
  <password xmlns="urn:opendaylight:netconf-node-topology">admin</password>
  <tcp-only xmlns="urn:opendaylight:netconf-node-topology">false</tcp-only>
  <!-- non-mandatory fields with default values, you can safely remove these if you do not wish to override any of these values-->
  <reconnect-on-changed-schema xmlns="urn:opendaylight:netconf-node-topology">false</reconnect-on-changed-schema>
  <connection-timeout-millis xmlns="urn:opendaylight:netconf-node-topology">20000</connection-timeout-millis>
  <max-connection-attempts xmlns="urn:opendaylight:netconf-node-topology">0</max-connection-attempts>
  <between-attempts-timeout-millis xmlns="urn:opendaylight:netconf-node-topology">2000</between-attempts-timeout-millis>
  <sleep-factor xmlns="urn:opendaylight:netconf-node-topology">1.5</sleep-factor>
  <!-- keepalive-delay set to 0 turns off keepalives-->
  <keepalive-delay xmlns="urn:opendaylight:netconf-node-topology">120</keepalive-delay>
</node>

{
"urn:TBD:params:xml:ns:yang:network-topology:node":{
"node-id":"ubuntu",
"host":"127.0.0.1",
"username":"admin",
"password":"admin",
"port":17830
}
}
* */