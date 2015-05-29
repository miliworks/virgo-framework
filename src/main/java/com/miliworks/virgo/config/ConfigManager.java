package com.miliworks.virgo.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.metadata.Property;

import com.miliworks.virgo.log.Log;

public class ConfigManager {

	private static ConfigManager _instance;

	private String confPath;
	private Map<String, String> mapConfig;

	public static ConfigManager getInstance() {
		if (_instance == null)
			_instance = new ConfigManager();

		return _instance;
	}

	private ConfigManager() {
		confPath = System.getProperty("user.dir") + "/conf";
		mapConfig = new HashMap<String, String>();
	}

	public void setConfPath(String confPath) {
		this.confPath = confPath;
	}

	public boolean init() {
		// 遍历配置目录下的所有*.conf文件，获取配置项
		Log.d(Consts.TAG, "loading virgo config in " + confPath + "...");
		load(confPath);

		// 启动配置检查器，定时检查配置，如果有配置变更，通知observers

		return true;
	}

	public boolean addListener(ConfigManagerObserver observer) {
		return true;
	}

	public boolean removeListener(ConfigManagerObserver observer) {
		return true;
	}

	private void load(String path) {
		// �ݹ����Ŀ¼�����е�.conf��β���ļ�
		File f = new File(path);
		if (f.isFile() && path.endsWith(Consts.CONFIG_EXTENSION))
			loadConf(path);
		else if (f.isDirectory()) {
			File[] children = f.listFiles();

			for (int i = 0; i < children.length; i++) {
				load(children[i].getAbsolutePath());
			}
		}
	}

	private void loadConf(String confFile) {
		File f = new File(confFile);
		if (f.exists() && f.isFile() && confFile.endsWith(Consts.CONFIG_EXTENSION)) {
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));

				String line = null;
				StringBuffer sb = new StringBuffer();
				line = br.readLine();
				while (line != null) {
					// 解析配置项
					if (!line.startsWith("#") && line.indexOf("=") > 0) {
						int pos = line.indexOf("=");
						String key = line.substring(0, pos).trim();
						String value = line.substring(pos + 1).trim();

						mapConfig.put(key, value);
					}
					line = br.readLine();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// ////////////////////////////////////////////////////////////
	// 通过aa.bb.cc形式获取配置项

	// string类型：默认null
	public String getString(String configPoint, String defaultValue) {
		if (mapConfig.get(configPoint) != null)
			return mapConfig.get(configPoint);

		return defaultValue;
	}

	public String getString(String configPoint) {
		return getString(configPoint, null);
	}

	// boolean类型：默认false
	public boolean getBoolean(String configPoint, boolean defaultValue) {
		if (mapConfig.get(configPoint) != null)
			return mapConfig.get(configPoint).equals("true");

		return defaultValue;
	}

	public boolean getBoolean(String configPoint) {
		return getBoolean(configPoint);
	}

	// int类型：默认0
	public int getInt(String configPoint, int defaultValue) {
		if (mapConfig.get(configPoint) != null)
			return Integer.valueOf(mapConfig.get(configPoint));

		return defaultValue;
	}

	public int getInt(String configPoint) {
		return getInt(configPoint, 0);
	}
}
