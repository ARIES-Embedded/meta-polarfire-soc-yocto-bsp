require recipes-kernel/linux/mpfs-linux-common.inc

LINUX_VERSION ?= "5.12.1"
KERNEL_VERSION_SANITY_SKIP="1"

BRANCH = "mpfs-linux-5.12.x"
SRCREV = "${AUTOREV}"
SRC_URI = " \
    git://github.com/polarfire-soc/linux.git;branch=${BRANCH} \
"

SRC_URI_append_icicle-kit-es = " \
    file://icicle-kit-es-microchip.dts \
 "

SRC_URI_append_icicle-kit-es-amp = " \
    file://icicle-kit-es-microchip-context-a.dts \
 "

SRC_URI_append_m100pfsevp = " \
    file://m100pfsevp.dtsi \
    file://m100pfsevp-emmc.dts \
    file://m100pfsevp-sdcard.dts \
    file://0001-gpio-microsemi-enumerate-GPIOs-starting-from-0.patch \
    file://0002-usb-musb-start-a-new-session-also-if-vbus-error-occu.patch \
    file://0003-riscv-Add-Aries-M100PFSEVP-PolarFire-SoC-FPGA-Platfo.patch \
 "

do_configure_prepend_icicle-kit-es() {
    cp -f ${WORKDIR}/icicle-kit-es-microchip.dts ${S}/arch/riscv/boot/dts/microchip/microchip-mpfs-icicle-kit.dts
}

do_configure_prepend_icicle-kit-es-amp() {
    cp -f ${WORKDIR}/icicle-kit-es-microchip-context-a.dts ${S}/arch/riscv/boot/dts/microchip/microchip-mpfs-icicle-kit-context-a.dts
}

do_configure_prepend_m100pfsevp() {
    cp -f ${WORKDIR}/m100pfsevp.dtsi ${S}/arch/riscv/boot/dts/aries
    cp -f ${WORKDIR}/m100pfsevp-emmc.dts ${S}/arch/riscv/boot/dts/aries
    cp -f ${WORKDIR}/m100pfsevp-sdcard.dts ${S}/arch/riscv/boot/dts/aries
}
 
SRC_URI_append_icicle-kit-es = " file://defconfig"
SRC_URI_append_icicle-kit-es-amp = " file://defconfig"
SRC_URI_append_m100pfsevp = " file://defconfig"




