//
//  TorchPlugin.h
//  facility
//
//  Created by facility logbook on 21/08/13.
//
//

#import <Foundation/Foundation.h>
#import <AVFoundation/AVFoundation.h>

#import <Cordova/CDVPlugin.h>

@interface TorchPlugin : CDVPlugin  {
    
    BOOL torchIsOn;
}

- (void) toggle:(CDVInvokedUrlCommand*)command;
//FIX:MANTIS:2976
- (void) turnOff:(CDVInvokedUrlCommand*)command;
//FIX:MANTIS:3012
- (void) isCapable:(CDVInvokedUrlCommand*)command;

- (void) isOn:(CDVInvokedUrlCommand*)command;

@end
