#PushPlugin for Cordova 3.x

This plugin is for use with Cordova, and allows your application to receive push notifications on both Android and iOS devices. The Android implementation uses Google's GCM (Google Cloud Messaging) service, whereas the iOS version is based on Apple APNS Notifications

Important - Push notifications are intended for real devices. The registration process will fail on the iOS simulator. Notifications can be made to work on the Android Emulator. However, doing so requires installation of some helper libraries, as outlined here, under the section titled "Installing helper libraries and setting up the Emulator".
