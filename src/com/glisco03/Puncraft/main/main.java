package com.glisco03.Puncraft.main;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.glisco03.Puncraft.Timers.Timer1L;
import com.glisco03.Puncraft.Timers.Timer2400L;
import com.glisco03.Puncraft.Utils.CombinerManager;
import com.glisco03.Puncraft.Utils.EventListener;
import com.glisco03.Puncraft.Utils.Infinity;
import com.glisco03.Puncraft.commands.command_combiner;
import com.glisco03.Puncraft.commands.command_freeze;
import com.glisco03.Puncraft.commands.command_getitem;
import com.glisco03.Puncraft.commands.command_puntest;
import com.glisco03.Puncraft.commands.command_stickwand;
import com.glisco03.Puncraft.commands.command_umfrage;
import com.glisco03.Puncraft.Utils.WorldManager;

import java.util.Collection;

public class main extends JavaPlugin{
	
	public static JavaPlugin pcplugin;

	public static Economy eco;

	@Override
	public void onEnable() {
		new vars();
		pcplugin = this;

		Bukkit.getScheduler().runTaskTimer(this, new Timer1L(), 1L, 1L);
		Bukkit.getScheduler().runTaskTimer(this, new Timer2400L(), 2400L, 2400L);

		new recipes();
		new CombinerManager();
		new Infinity();

		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			eco = economyProvider.getProvider();
		}

		new EventListener(this, eco);

		getCommand("umfrage").setExecutor(new command_umfrage());
		getCommand("stickwand").setExecutor(new command_stickwand());
		getCommand("freeze").setExecutor(new command_freeze());
		getCommand("combiner").setExecutor(new command_combiner());
		getCommand("getitem").setExecutor(new command_getitem());
		getCommand("puntest").setExecutor(new command_puntest(eco));
		//getCommand("infinity").setExecutor(new command_infinity());
		//getCommand("setinfi").setExecutor(new command_setinfi());

		/*Collection<Entity> j = Bukkit.getWorld("world").getNearbyEntities(new Location(Bukkit.getWorld("world"), 165, 118, 236), 2, 6, 2);
		for(Entity e : j){
			if(e.getType() == EntityType.ARMOR_STAND){
				e.remove();
			}
		}
		WorldManager.spawnText(new Location(Bukkit.getWorld("world"), 167.0, 122, 237.2), "�b---- �2Leaderboard �b----", Bukkit.getWorld("world"));*/
	}
	
	public static NamespacedKey key(String name) {
		return new NamespacedKey(pcplugin, name);
	}
}
