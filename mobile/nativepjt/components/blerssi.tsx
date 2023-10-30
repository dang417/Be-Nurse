/**
 * Sample BLE React Native App
 */

import React, {useState, useEffect} from 'react';
import {
  NativeModules,
  NativeEventEmitter,
  Platform,
  PermissionsAndroid,
} from 'react-native';

const SECONDS_TO_SCAN_FOR = 10;
const SERVICE_UUIDS: string[] = [];
const ALLOW_DUPLICATES = true;

const BEACON_ADDRESS = {
  admin4: 'DF:8F:78:F0:06:1F',
  equip1: 'E3:2F:4B:F3:F2:77',
  admin8: 'CA:8D:AC:9C:63:64',
  admin7: 'CA:87:66:3E:6E:38',
};

import BleManager, {
  BleScanCallbackType,
  BleScanMatchMode,
  BleScanMode,
  Peripheral,
} from 'react-native-ble-manager';
const BleManagerModule = NativeModules.BleManager;
const bleManagerEmitter = new NativeEventEmitter(BleManagerModule);

declare module 'react-native-ble-manager' {
  // enrich local contract with custom state properties needed by App.tsx
  interface Peripheral {
    connected?: boolean;
    connecting?: boolean;
  }
}

const App = () => {
  const [isScanning, setIsScanning] = useState(false);
  const [peripherals, setPeripherals] = useState(
    new Map<Peripheral['id'], Peripheral>(),
  );
  const mac_add: string[] = [
    'E3:2F:4B:F3:F2:77',
    'DF:8F:78:F0:06:1F',
    'CA:8D:AC:9C:63:64',
    'CA:87:66:3E:6E:38',
  ];

  const addOrUpdatePeripheral = (id: string, updatedPeripheral: Peripheral) => {
    // new Map() enables changing the reference & refreshing UI.
    // TOFIX not efficient.
    setPeripherals(map => new Map(map.set(id, updatedPeripheral)));
  };

  const startScan = () => {
    if (!isScanning) {
      // reset found peripherals before scan
      setPeripherals(new Map<Peripheral['id'], Peripheral>());

      try {
        console.debug('[startScan] starting scan...');
        setIsScanning(true);
        BleManager.scan(SERVICE_UUIDS, SECONDS_TO_SCAN_FOR, ALLOW_DUPLICATES, {
          matchMode: BleScanMatchMode.Sticky,
          scanMode: BleScanMode.LowLatency,
          callbackType: BleScanCallbackType.AllMatches,
        })
          .then(() => {
            console.debug('[startScan] scan promise returned successfully.');
          })
          .catch(err => {
            console.error('[startScan] ble scan returned in error', err);
          });
      } catch (error) {
        console.error('[startScan] ble scan error thrown', error);
      }
    }
  };

  const handleStopScan = () => {
    setIsScanning(false);
    console.debug('[handleStopScan] scan is stopped.');
  };

  const handleDiscoverPeripheral = (peripheral: Peripheral) => {
    // console.debug('[handleDiscoverPeripheral] new BLE peripheral=', peripheral);
    // if (!peripheral.name) {
    //   peripheral.name = 'NO NAME';
    //   return
    // }
    // addOrUpdatePeripheral(peripheral.id, peripheral);
    if (mac_add.includes(peripheral.id)) {
      addOrUpdatePeripheral(peripheral.id, peripheral);
    }
  };

  const readRSSIbyBeacone = (address: string) => {
    BleManager.readRSSI(address)
      .then(rssi => {
        console.log('Current RSSI: ' + rssi);
      })
      .catch(error => {
        console.log(error);
      });
  };

  useEffect(() => {
    console.debug('useEffect');
    try {
      BleManager.start({showAlert: false})
        .then(() => console.debug('BleManager started.'))
        .catch(error =>
          console.error('BeManager could not be started.', error),
        );
    } catch (error) {
      console.error('unexpected error starting BleManager.', error);
      return;
    }

    const listeners = [
      bleManagerEmitter.addListener(
        'BleManagerDiscoverPeripheral',
        handleDiscoverPeripheral,
      ),
      bleManagerEmitter.addListener('BleManagerStopScan', handleStopScan),
    ];

    handleAndroidPermissions();

    return () => {
      BleManager.stopScan().then(() => {
        console.debug('[app]scan stopped by close modal');
      });
      console.debug('[app] main component unmounting. Removing listeners...');
      for (const listener of listeners) {
        listener.remove();
      }
    };
  }, []);

  const handleAndroidPermissions = () => {
    if (Platform.OS === 'android' && Platform.Version >= 31) {
      PermissionsAndroid.requestMultiple([
        PermissionsAndroid.PERMISSIONS.BLUETOOTH_SCAN,
        PermissionsAndroid.PERMISSIONS.BLUETOOTH_CONNECT,
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
      ]).then(result => {
        if (result) {
          console.debug(
            '[handleAndroidPermissions] User accepts runtime permissions android 12+',
          );
        } else {
          console.error(
            '[handleAndroidPermissions] User refuses runtime permissions android 12+',
          );
        }
      });
    }
  };

  return <></>;
};

export default App;