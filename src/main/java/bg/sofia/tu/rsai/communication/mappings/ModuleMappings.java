package bg.sofia.tu.rsai.communication.mappings;

/**
 * author: Aleksandar Karadzhinov
 * email: aleksandar.karadjinov@gmail.com
 * <p/>
 * created on 07/06/2017 @ 23:42.
 */
public enum ModuleMappings {
    DISPLAY("display","http://localhost:8181/communication/test"),
    GEAR("gear","exampleUrl.net");


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
