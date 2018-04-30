package nl.test.rest.application.resttestapplication.rest.generic;

/**
 * Enumeration of namespaces that are used in this project.
 *
 */
public enum Namespace {

    /** */
    TEST("test", "http://test/rest"),
    /** */
    INSTANCE("inst", "http://test/rest/instances");

    private String url;
    private String prefix;
    
    /**
     * The maximum length of the prefix is limited due to database column sizes.
     * The length is verified in the unittest.
     */
    protected static final int PREFIX_MAXIMUM_LENGTH = 4;
    
    private Namespace(String prefix, String url){
        this.prefix = prefix;
        this.url = url;
    }
    
    /**
     * Get the namespace prefix.
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }
    
    /**
     * Get the namespace url.
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Get the namespace from an url.
     * @param url
     * @return namespace
     */
    public static Namespace valueOfUrl(String url){
        for(Namespace ns: values()){
            if (ns.getUrl().equals(url)){
                return ns;
            }
        }
        throw new IllegalArgumentException("Namespace not defined for url: " + url);
    }

    /**
     * Get the namespace from a prefix.
     * @param prefix
     * @return namespace
     */
    public static Namespace valueOfPrefix(String prefix){
        for(Namespace ns: values()){
            if (ns.getPrefix().equals(prefix)){
                return ns;
            }
        }
        throw new IllegalArgumentException("Namespace not defined for prefix: " + prefix);
    }

}
