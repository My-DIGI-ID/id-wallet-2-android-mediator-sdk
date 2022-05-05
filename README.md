# Mediator Android SDK

This framework uses the Aries Android SDK to communicate with a mediator service via DIDComm for

- connecting
- creating an inbox
- getting inbox items
- delete inbox items
- adding a route
- adding the device info

## Libraries

In order to use the Mediator Android SDK in your project, there are additional libraries you have to add to your project, which are not included in this repository:

### Android

The following sdk must be located in the app/libs folder of your project:

- Aries Android SDK

The following libraries must be located in your project in the app/src/main/jniLibs folder:

Get it from https://developer.android.com/ndk/downloads:
- src/libs-android/arm64-v8a/libc++_shared.so
- src/libs-android/armeabi-v7a/libc++_shared.so
- src/libs-android/x86/libc++_shared.so
- src/libs-android/x86_64/libc++_shared.so

Get it from: https://repo.sovrin.org/android/libindy/stable:
- src/libs-android/arm64-v8a/libindy.so
- src/libs-android/armeabi-v7a/libindy.so
- src/libs-android/x86/libindy.so
- src/libs-android/x86_64/libindy.so

Get the jar from : https://github.com/java-native-access/jna/tree/master/lib/native and extract the:
- libjnidispatch.so
