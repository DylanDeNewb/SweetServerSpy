package fun.sweetsmp.sweetserverspy.utils;

import fun.sweetsmp.sweetserverspy.SweetServerSpy;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class FileUtils {

    private File file;
    private Configuration cfg;

    public FileUtils(){}

    public Configuration create(String path, MultiConsumer<Configuration, Boolean> newFile) {

        if(!SweetServerSpy.getInstance().getDataFolder().exists()){
            SweetServerSpy.getInstance().getDataFolder().mkdir();
        }

        boolean newfilebool = false;
        file = new File(path);
        if (!file.exists()) {
            try {
                if(file.createNewFile()){
                    newfilebool = true;
                }
                cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            save();
        }

        newFile.accept(getCfg(), newfilebool);
        return getCfg();
    }

    public void save() {
        try {
            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getCfg() {
        try {
            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }

}
