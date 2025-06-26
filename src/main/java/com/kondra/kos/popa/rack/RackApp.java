/**
 * (C) Copyright 2024, Kondra, All rights reserved.
 */
package com.kondra.kos.popa.rack;

import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.vfs.VFSSource;
import com.tccc.kos.commons.kab.KabFile;
import com.tccc.kos.core.manifest.ResolvedManifestSection;
import com.tccc.kos.core.service.app.SystemApplication;
import com.tccc.kos.core.service.browser.BrowserService;
import com.tccc.kos.core.service.device.DeviceService;
import com.tccc.kos.core.service.device.serialnum.SerialNumberProvider;
import com.tccc.kos.core.service.device.serialnum.config.ConfigSerialNumberProvider;
import com.tccc.kos.ext.cms.service.screen.ScreenContext;
import com.tccc.kos.ext.cms.service.screen.ScreenService;


import com.kondra.kos.popa.rack.HelloWorld;



import lombok.extern.slf4j.Slf4j;


/**
 * System application for Popa rack.
 *
 * @author David Vogt (david@kondra.com)
 * @version 2024-07-07
 */
@Slf4j
public class RackApp extends SystemApplication<RackAppConfig> {

    // kab type for the ui
    private static final String UI_KAB_TYPE = "popa.rack.ui";

    @Autowired
    private BrowserService browserService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private DeviceService deviceService;
    private ScreenContext screenCtx;
    private VFSSource source;

    @Override
    public void load() throws Exception {
        // use config property for serial number provider
        SerialNumberProvider provider = new ConfigSerialNumberProvider();
        addToCtx(provider);
        deviceService.setSerialNumberProvider(provider);
    }

    @Override
    public void start() throws Exception {
        KabFile kab;

        // find the ui kab and mount it
        if ((kab = getSection().getKabByType(UI_KAB_TYPE)) != null) {
            source = getVfs().mount("/", kab);
        } else {
            log.error("No ui kab found.");
        }

        // add any screens found in the screens section
        screenCtx = new ScreenContext();
        screenCtx.add(getSection("screens"));
        for (ResolvedManifestSection section : getSectionsByNamePrefix("screens.")) {
            screenCtx.add(section);
        }
        screenService.mount("rack", screenCtx);

        log.info("-------------------------------------------------------------------------------------------");
        log.info("🤖 Reading Values from Video execution starting 🤖");
        log.info("-------------------------------------------------------------------------------------------");

        // Recorremos las pantallas disponibles
        if (screenCtx.getScreens() != null && !screenCtx.getScreens().isEmpty()) {
            for (var screen : screenCtx.getScreens()) {
                log.info("📺 Screen Name: {}", screen.getName());
                log.info("🎞️ Video Loops (numberOfTimesVideoPlays): {}", screen.getVideoLoops());
                log.info("🖼️ Resolution: {}x{}", screen.getWidth(), screen.getHeight());
                log.info("📁 Content Type: {}", screen.getContentType());
                log.info("🏷️ Screen Type: {}", screen.getScreenType());
                log.info("📅 Start Date: {}", screen.getStartDate());
                log.info("📊 Priority: {}", screen.getPriority());
                log.info("---------------------------------------------------");
            }
        } else {
            log.warn("⚠️ No screens found in screen context.");
        }

    }

    @Override
    public void started() {
        log.info("🔧 Método started() executed.");
        ExecutedBinary.startBinary();
        log.info("🔧 Método started() Finish.");


        // nav to the ui
        if (source != null) {
            browserService.goToUrl(source.getFullPath("index.html"));
            //browserService.goToUrl("https://www.google.com");
        }

    }
}
