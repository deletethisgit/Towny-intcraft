package com.palmergames.bukkit.towny.war.flagwar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.war.flagwar.TownyWar;

public class TownyWarOnlineListener implements Listener {
    
    public TownyWarOnlineListener(Towny plugin) {
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit (PlayerQuitEvent event) throws TownyException {
        
        Player player = event.getPlayer();   
        TownyWar.cancelIfNotEnoughPlayers(player);
        
    }
        
}

