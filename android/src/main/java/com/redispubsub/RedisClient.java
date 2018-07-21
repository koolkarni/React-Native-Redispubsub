package com.redispubsub;

import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.RedisConnectionException;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;


public class RedisClient extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;
    private RedissonClient mredissonClient;

    public RedisClient(ReactApplicationContext reactContext){
        super(reactContext);
        this.reactContext = reactContext;
    }
    @Override
    public String getName() {
        return "RedisClient";
    }

    @ReactMethod
    public void redisConnect(String ipPort){
        //Redisson client setup to be done in background thread
        try {
            //42.42.42.77 Pandari machine IP
            //42.42.42.42 Coros Van IP
            Config config = new Config();
            config.useSingleServer().setAddress(ipPort); //IPAddress of Redis and port
            mredissonClient = Redisson.create(config);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ReactMethod
    public void subscribe(String channelName){

        if (mredissonClient != null) {
            RTopic subscribeTopic = mredissonClient.getTopic(channelName, StringCodec.INSTANCE); //Channel to listen and Codec.
            subscribeTopic.addListener(new MessageListener<String>() {
                @Override
                public void onMessage(String channel, String msg) {
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit(channel, msg);
                }
            });
        } else {

        }

    }

    @ReactMethod
    public long publish(String ChannelName,String msg){
        long clientsReceivedMessage =0;
        if (mredissonClient != null) {
            //Redisson publish
            RTopic topic = mredissonClient.getTopic(ChannelName,StringCodec.INSTANCE);
            try {

                 clientsReceivedMessage = topic.publish(msg); //Publishing lat lon to Coros
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {

        }
        return clientsReceivedMessage;

    }


}
