package com.home.HomeEnvironment.server;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/websocket/{sid}", encoders = {})
public class WebSocketServer {

    private static int onlineCount = 0;
//    线程安全存储Session
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    /**
     *  与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    private String id;

    /**
     * 连接建立成功调用的方法
     * @param id
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "id") String id, Session session) {
        this.session = session;
        this.id = id;//接收到发送消息的人员编号
        webSocketSet.put(id, this);//加入set中
        addOnlineCount();//在线数加1
        System.err.println("【websocket消息】有新的站点连接"+id+", 总数:{"+getOnlineCount()+"}");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.err.println("【websocket消息】连接断开, 总数:{"+getOnlineCount()+"}");
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        System.err.println("【websocket消息】收到客户端发来的消息:{"+message+"}");
//        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        //群发消息
        for (WebSocketServer webSocketServer : webSocketSet.values()) {
            try {
                Map result = new HashMap();
                result.put("message", message);
                webSocketServer.sendMessage(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("发生错误："+error);
    }

    /**
     * 发送客户端Object信息
     * @param t
     * @throws IOException
     */
    public void sendMessage(Map<String, Object> t) throws IOException {
        try {
            if (session.isOpen()){
                this.session.getBasicRemote().sendObject(t);
            } else {
                System.err.println("当前站点不在线，无法发送信息！");
            }
        } catch (EncodeException e){
            e.printStackTrace();
        }
    }

    /**
     * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
     * @param callVideoList：推送视频集合
     * @throws IOException
     */
    public Map sendToUser(Map<String, Object> callVideoList) {
        Map result = new HashMap();
        List<String> num = new ArrayList<>();
        if (null == callVideoList.get("stationIds") || "".equals(callVideoList.get("stationIds"))){
            result.put("state", 4001);
            result.put("message", "请指定播放站点！");
        } else if (null == callVideoList.get("filepath") || "".equals(callVideoList.get("filepath"))){
            result.put("state", 4002);
            result.put("message", "请添加播放视频的地址！");
        } else {
            String[] stationIdss = callVideoList.get("stationIds").toString().split(",");
            try {
                if (stationIdss.length > 0){
                    for (int i = 0; i < stationIdss.length; i++){
                        if (webSocketSet.get(stationIdss[i]) != null) {
                            if(!id.equals(stationIdss[i]))
                                webSocketSet.get(stationIdss[i]).sendMessage(callVideoList);
                            else
                                webSocketSet.get(stationIdss[i]).sendMessage(callVideoList);
                        } else {
                            System.out.println("该站点："+ stationIdss[i] +"不在线");
                            num.add(stationIdss[i]);
                        }
                    }
                    result.put("state", 200);
                    if (num.size() > 0){
                        result.put("message", "已经发送到指定站点信息！其中有："+ num +"站点不在线，无法发送！");
                    } else {
                        result.put("message", "已经发送到指定站点信息！");
                    }
                } else {
                    result.put("state", 4003);
                    result.put("message", "请输入指定的站点ID集合！");
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return result;
//        else {
//            //如果用户不在线则返回不在线信息给自己
//            sendToUser("当前用户不在线",id);
//        }
    }

    /**
     * 推送视频到全部站点
     * @param callVideoList：视频集合
     * @return
     */
    public Map sendToAll(Map<String, Object> callVideoList) {
        Map result = new HashMap();
        if (null == callVideoList.get("filepath")){
            result.put("state", 5001);
            result.put("message", "请添加播放视频地址！");
        } else {
            for (String key: webSocketSet.keySet()) {
//            System.err.println("【websocket消息】广播消息, message={""}");
//            log.info("【websocket消息】广播消息, message={}", message);
                try {
                    webSocketSet.get(key).sendMessage(callVideoList);
//                webSocketSet.get(key).session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            result.put("state", 200);
            result.put("message", "已对全部站点发送信息！");
        }
        return result;
    }
}
