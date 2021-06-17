package com.CupidonSauce173.HubTransfer;

import dev.waterdog.waterdogpe.network.ServerInfo;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class HubTransfer extends Plugin {

    private void createDefaultConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceFile("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        //create config
        createDefaultConfig();
        Configuration config = getConfig();
        //get default server.
        ServerInfo defaultServer = this.getProxy().getServerInfo(this.getProxy().getConfiguration().getPriorities().get(0));
        //registers command.
        this.getProxy().getCommandMap().registerCommand((new HubCommand(defaultServer, config.getString("message"))));
    }
}
