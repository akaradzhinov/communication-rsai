package bg.sofia.tu.rsai.communication.mappings;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 07/06/2017 @ 23:42.
 */

//[Requirement SRSReq07] Да поддържа предварително дефиниран набор от налични модули
public enum ModuleMappings {

    //[Requirement SRSReq08] Да може лесно и бързо да се добавят и променят модули
    GEAR("gear","http://localhost:8080/communication/test"),
    BELT("belt","http://localhost:8080/communication/test"),
    SPEED("speed","http://localhost:8080/communication/test");


    private String name;
    private String value;

    ModuleMappings(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getValue(String name) {
        for (ModuleMappings mapping : values()) {
            if(mapping.name.equals(name)) {
                return mapping.value;
            }
        }

        throw new IllegalArgumentException("No mapping found for moduleName: " + name);
    }
}
