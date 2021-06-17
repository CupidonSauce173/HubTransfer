package com.CupidonSauce173.HubTransfer;

import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.network.ServerInfo;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;


public class HubCommand extends Command{

    ServerInfo defaultServer;
    String message;

    public HubCommand(ServerInfo defaultServer, String message) {
        super("hub", CommandSettings.builder()
        .setDescription("To go back to the hub!")
        .setUsageMessage("/hub")
        .build()
        );
        this.defaultServer = defaultServer;
        this.message = message;
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args){
        if(sender.isPlayer()){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            sender.sendMessage(message);
            player.connect(defaultServer);
            return true;
        }
        return false;
    }
}
