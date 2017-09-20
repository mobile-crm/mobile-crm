package service;

import org.apache.log4j.Logger;
import vo.Config;

public class ConfigService {
	
		private Logger logger = Logger.getLogger(ConfigService.class);
		static ConfigService  instance=new ConfigService();
		public static  ConfigService  getInstance(){
			return instance;
		}
		private ConfigService(){
			
		}
}
