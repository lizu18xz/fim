package com.fayayo.fim.transport.protocol;

import com.fayayo.fim.transport.protocol.request.LoginRequestPacket;
import com.fayayo.fim.transport.protocol.request.LogoutRequestPacket;
import com.fayayo.fim.transport.protocol.request.MessageRequestPacket;
import com.fayayo.fim.transport.protocol.response.LoginResponsePacket;
import com.fayayo.fim.transport.protocol.response.LogoutResponsePacket;
import com.fayayo.fim.transport.protocol.response.MessageResponsePacket;
import com.fayayo.fim.transport.serialize.Serializer;
import com.fayayo.fim.transport.serialize.impl.HessianSerializer;
import com.fayayo.fim.transport.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.fayayo.fim.transport.protocol.command.Command.*;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 封装为二进制过程
 */
@Slf4j
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Serializer> serializerMap;
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;


    private PacketCodeC() {

        //初始化序列化信息
        serializerMap = new HashMap<Byte, Serializer>();
        Serializer jsonSerializer=new JSONSerializer();
        Serializer hessianSerializer=new HessianSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlogrithm(), jsonSerializer);
        serializerMap.put(hessianSerializer.getSerializerAlogrithm(), hessianSerializer);

        //初始化 packet
        packetTypeMap = new HashMap<Byte, Class<? extends Packet>>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);

        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);


        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);

        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

    }


    public void encode(ByteBuf byteBuf, Packet packet){
        // 1. 创建 ByteBuf 对象
        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 实际编码过程  魔数,版本,序列化算法,指令,数据长度,数据
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        if (requestType==null){
            throw new RuntimeException("没有配置参数类型");
        }
        log.info("请求参数类型:{}",requestType.getName());
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
