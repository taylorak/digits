package views.formdata;

import java.util.HashMap;
import java.util.Map;

public class TelephoneTypes {
  private static String[] types = {"Home", "Work", "Mobile"}; 
  
  /**
   * 
   * @return 
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> typeMap = new HashMap<>();
    for(String type: types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }
  
  /**
   * 
   * @param type
   * @return
   */
  public static Map<String, Boolean> getTypes(String telephoneType) {
    Map<String, Boolean> typeMap = TelephoneTypes.getTypes();
      if(isType(telephoneType)){
        typeMap.put(telephoneType, true);
      }
    return typeMap;
  }
  
  /**
   * 
   * @param type
   * @return
   */
  public static boolean isType(String telephoneType) {
    return TelephoneTypes.getTypes().keySet().contains(telephoneType);
  }
}
