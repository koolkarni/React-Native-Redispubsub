# React-Native-Redispubsub

Follow the instructions or Open a ticket if you need any help.

an RN lib which exposes pub/sub of redis in react native without any node server or socket or node redis client



usage:
````
import RedisClient from "react-native-redispubsub";
import {DeviceEventEmitter} from 'react-native';
constructor(props) {
    super(props);
    RedisClient.redisConnect("192.168.1.215:6379");
    //subscriber to channel 
    RedisClient.subscribe("androidChannel");

    //listen to channel 
    DeviceEventEmitter.addListener('androidChannel', function(e: Event) {
        alert(e);
      });
      
      //publish to channel 
      RedisClient.publish("featureChannel","GOT IT :D");
  }
  ````


  You Might need to add in your applicaton android gradle.buildDir

  ````
  android {

    defaultConfig {
       multiDexEnabled true
       }
    }

  you can add it after buildTypes{...}

  packagingOptions {
         pickFirst 'META-INF/INDEX.LIST'
         pickFirst 'META-INF/LICENSE'
         //pickFirst 'META-INF/rxjava.properties'
         pickFirst 'META-INF/io.netty.versions.properties'
     }
  ````
update the gradle.properties
````
set org.gradle.jvmargs=-Xmx1536M
````

  Your Project needs to have an ext like below. as this will import the buildToolVersion from rootDir or root project

  `````
  ext {
    buildToolsVersion = "26.0.3"
    minSdkVersion = 16
    compileSdkVersion = 26
    targetSdkVersion = 26
    supportLibVersion = "26.1.0"
}
  `````
