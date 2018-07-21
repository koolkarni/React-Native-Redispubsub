# React-Native-Redispubsub

an RN lib which exposes pub/sub of redis in react native without any node server or socket or node redis client



usage:
````
import RedisClient from "react-native-redispubsub";
import {DeviceEventEmitter} from 'react-native';
constructor(props) {
    super(props);
    RedisClient.redisConnect("192.168.1.215:6379");
    RedisClient.subscribe("androidChannel");

    DeviceEventEmitter.addListener('androidChannel', function(e: Event) {
        alert(e);
      });
      RedisClient.publish("featureChannel","GOT IT :D");
  }
  ````


  You Might need to add in your applicaton android gradle.buildDir

  ````
  packagingOptions {
         pickFirst 'META-INF/INDEX.LIST'
         pickFirst 'META-INF/LICENSE'
         //pickFirst 'META-INF/rxjava.properties'
         pickFirst 'META-INF/io.netty.versions.properties'
     }
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
