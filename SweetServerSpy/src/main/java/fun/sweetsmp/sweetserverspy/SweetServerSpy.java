package fun.sweetsmp.sweetserverspy;

import fun.sweetsmp.sweetserverspy.commands.ServerSpyCommand;
import fun.sweetsmp.sweetserverspy.listeners.ServerChatListener;
import fun.sweetsmp.sweetserverspy.utils.FileUtils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public final class SweetServerSpy extends Plugin {

    List<ProxiedPlayer> spies;

    private static SweetServerSpy instance;
    private FileUtils fileUtils;

    private Configuration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        this.fileUtils = new FileUtils();
        this.spies = new ArrayList<>();

        config = fileUtils.create("config.yml", (configuration, aBoolean) -> {
            if(configuration == null || !aBoolean){return;}

            configuration.set("format", "• (spy) (%server_name%) %user%: %message%");
        });

        fileUtils.save();

        getProxy().getPluginManager().registerCommand(this, new ServerSpyCommand(this));
        getProxy().getPluginManager().registerListener(this, new ServerChatListener(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        spies.clear();
    }

    public List<ProxiedPlayer> getSpies() {
        return spies;
    }

    public static SweetServerSpy getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config;
    }
}
