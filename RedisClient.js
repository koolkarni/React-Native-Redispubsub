/**
 * This exposes our Android code into our RN project
 */

import { NativeModules } from "react-native";

const { RedisClient } = NativeModules;

export {
  RedisClient
}
