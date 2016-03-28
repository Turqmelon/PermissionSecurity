package com.turqmelon.PermissionSecurity;

/******************************************************************************
 * Copyright (c) 2016.  Written by Devon "Turqmelon": http://turqmelon.com    *
 * For more information, see LICENSE.TXT.                                     *
 ******************************************************************************/

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermSecurity extends JavaPlugin implements Listener {

    private List<String> flaggedCommands = new ArrayList<>();
    private String securityWord = null;

    @Override
    public void onEnable() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveDefaultConfig();
        }

        for (String cmd : getConfig().getStringList("flagged-commands")) {
            cmd = cmd.toLowerCase();
            flaggedCommands.add(cmd);
        }

        String word = getConfig().getString("security-word");
        if (word == null || word.equalsIgnoreCase("CHANGEME")) {
            word = UUID.randomUUID().toString();
            getConfig().set("security-word", word);
            saveConfig();
        }

        this.securityWord = word;

        getServer().getPluginManager().registerEvents(this, this);


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsoleCommand(ServerCommandEvent event) {
        CommandSender player = event.getSender();
        String msg = event.getCommand();

        if (isFlagged(msg)) {
            if (!msg.endsWith(securityWord)) {
                player.sendMessage("Usage of this command is restricted.");
                player.sendMessage("Please append the security word to the end of the command.");
                player.sendMessage("(For details, speak with your server administrator.)");
                event.setCancelled(true);
                return;
            }
        } else {
            return;
        }


        int index = msg.indexOf(securityWord);
        msg = msg.substring(0, (index - 1));
        event.setCommand(msg);
    }

    private boolean isFlagged(String cmd) {
        for (String flagged : getFlaggedCommands()) {
            if (cmd.startsWith(flagged)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String msg = event.getMessage();

        if (isFlagged(msg.replace("/", ""))) {
            if (!msg.endsWith(securityWord)) {
                player.sendMessage("§c§lUsage of this command is restricted.");
                player.sendMessage("§cPlease append the security word to the end of the command.");
                player.sendMessage("§7§o(For details, speak with your server administrator.)");
                event.setCancelled(true);
                return;
            }
        } else {
            return;
        }


        int index = msg.indexOf(securityWord);
        msg = msg.substring(0, (index - 1));
        event.setMessage(msg);

    }

    public List<String> getFlaggedCommands() {
        return flaggedCommands;
    }
}
