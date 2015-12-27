Polling Plugin
==============

Polling plugin for Cordova/PhoneGap.

To install the plugin:

    cordova plugins add https://github.com/binzma/pollingplugin.git

To invoke the plugin: 

    navigator.plugins.polling.set(date,
    function(){
      // SUCCESS
    }, 
    function(){
      // ERROR
    })
