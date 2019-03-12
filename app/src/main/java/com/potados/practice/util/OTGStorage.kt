package com.potados.practice.util

object OTGStorage {

    // /mnt/media_rw/0FFC-2B18
    // /storage/emulated/0

    private val points = listOf(
        "/storage/UsbDriveA",       // (all Samsung devices)
        "/storage/USBstorage1",     // (LG G4, V10, G3, G2, other LG devices)
        "/storage/usbdisk",         //(Moto Maxx, Turbo 2, Moto X Pure, other Motorola devices)
        "/storage/usbotg ",         // (Sony Xperia devices, Lenovo Tabs)
        "/storage/UDiskA ",         // (Oppo devices)
        "/storage/usb-storage",     // (Acer Iconia Tabs)
        "/storage/usbcard",         // (Dell Venue -- Vanilla Android 4.3 tablet)
        "/storage/usb",             // (HTC One M7, and some Vanilla Android devices)) /* test */
        "/storage/emulated"
    )

    fun getVolumes(): List<String> {
        val list: MutableList<String> = ArrayList()

        VolumeManager.getStorageSet().toList().forEach { storagePath ->
            points.forEach { point ->
                if (storagePath.contains(point)) list.add(point)
            }
        }

        return list
    }
}