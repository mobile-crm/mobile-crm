package vo;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;


public class Config {
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String configContent;
	public String getConfigContent() {
		return configContent;
	}
	public void setConfigContent(String configContent) {
		this.configContent = configContent;
	}
	private Integer configType;
	public Integer getConfigType() {
		return configType;
	}
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}
	@Override
	public String toString() {
		Map<String, Object> values=new HashMap<String, Object>();
		Field[] fields=this.getClass().getDeclaredFields();
		try{
			for (Field f : fields) {
			values.put(f.getName(), f.get(this));
			}
			return values.toString();
		}catch(Exception e){
			return e.toString();
		}
	}
}