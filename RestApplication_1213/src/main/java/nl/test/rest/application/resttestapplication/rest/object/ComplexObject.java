package nl.test.rest.application.resttestapplication.rest.object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nl.test.rest.application.resttestapplication.rest.generic.Namespace;

import org.apache.commons.lang3.StringUtils;

public class ComplexObject {

    private static final long serialVersionUID = 1L;

    private Namespace namespace;
    private String name;

    private static final String URI_REGEX = "^<\\w+:\\/(\\/[\\w\\.-]+)+[#\\/][\\w\\.\\-]+>$";
    private static final Pattern URI_PATTERN = Pattern.compile(URI_REGEX);

    private static final String CURIE_REGEX = "\\w+:[\\w\\d\\_]+";
    private static final Pattern CURIE_PATTERN = Pattern.compile(CURIE_REGEX);

    /**
     * Separates namespace and local name
     */
    public static final String URI_SEPARATOR = "/";
    /**
     * Separates namespace and local name
     */
    public static final String URI_SEPARATOR_HASH = "#";
    /**
     * Start character for uri
     */
    public static final String URI_START = "<";
    /**
     * End character for uri
     */
    public static final String URI_END = ">";
    /**
     * Separates namespace-prefix and local name
     */
    public static final String CURIE_SEPARATOR = ":";

    private static final String FORMAT_URI_SLASH = "<%s/%s>";
    private static final String FORMAT_URI_HASH = "<%s%s>";
    private static final String FORMAT_CURIE = "%s:%s";

    protected ComplexObject() {
        // default constructor for serializable
    }

    /**
     * Constructor.
     *
     * @param namespace
     * @param name
     */
    public ComplexObject(Namespace namespace, String name) {
        this.namespace = namespace;
        this.name = name;
        if (StringUtils.isEmpty(this.name)) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (this.namespace == null) {
            throw new IllegalArgumentException("Namespace is empty");
        }
    }

    /**
     * Constructor from a prefix and name.
     *
     * @param prefix
     * @param name
     */
    public ComplexObject(String prefix, String name) {
        this(Namespace.valueOfPrefix(prefix), name);
    }

    /**
     * Constructor.
     *
     * @param uri
     */
    public ComplexObject(String uri) {
        if (!isValidUri(uri) && !isValidCurie(uri)) {
            throw new IllegalArgumentException("Not a valid uri: " + uri);
        } else {
            if (isValidUri(uri)) {
                constructFromUri(uri);
            } else {
                constructFromCurie(uri);
            }

        }

    }

    /**
     * @param uri
     */
    private void constructFromCurie(String uri) {
        this.namespace = Namespace.valueOfPrefix(StringUtils.substringBefore(uri, CURIE_SEPARATOR));
        this.name = StringUtils.substringAfter(uri, CURIE_SEPARATOR);
    }

    /**
     * @param uri
     */
    private void constructFromUri(String uri) {
        int idxSeparator = uri.lastIndexOf(URI_SEPARATOR_HASH);
        if (idxSeparator < 0) {
            idxSeparator = uri.lastIndexOf(URI_SEPARATOR);
            separateWithSlash(uri, idxSeparator);
        } else {
            separateWithHash(uri, idxSeparator);
        }
    }

    public static boolean isValidCurie(String curie) {
        return curie == null ? false : CURIE_PATTERN.matcher(curie).matches();
    }

    /**
     * Utility method to test if the string is a valid uri.
     *
     * @param uri
     * @return true when valid
     */
    public static boolean isValidUri(String uri) {
        if (uri == null) {
            return false;
        }
        Matcher m = URI_PATTERN.matcher(uri);
        return m.matches();
    }

    private void separateWithHash(String uri, int idxSeparator) {
        // url contains hash
        this.namespace = Namespace.valueOfUrl(uri.substring(1, idxSeparator + 1));
        this.name = uri.substring(idxSeparator + 1, uri.length() - 1);
    }

    private void separateWithSlash(String uri, int idxSeparator) {
        this.namespace = Namespace.valueOfUrl(uri.substring(1, idxSeparator));
        this.name = uri.substring(idxSeparator + 1, uri.length() - 1);
    }

    /**
     * Get the namespace.
     *
     * @return namespace
     */
    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * Get the prefix of the namespace.
     *
     * @return prefix
     */
    public String getPrefix() {
        return namespace.getPrefix();
    }

    /**
     * Get the name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the uri (full name).
     *
     * @return uri
     */
    public String getUri() {
        if (getNamespace().getUrl().endsWith(URI_SEPARATOR_HASH)) {
            return String.format(FORMAT_URI_HASH, getNamespace().getUrl(), getName());
        } else {
            return String.format(FORMAT_URI_SLASH, getNamespace().getUrl(), getName());
        }
    }

    /**
     * Get the curie (compact uri).
     *
     * @return curie
     */
    public String getCurie() {
        return String.format(FORMAT_CURIE, getNamespace().getPrefix(), getName());
    }

    /**
     * Get the name from an uri for a specific namespace
     *
     * @param uri
     * @param namespace
     * @return name
     */
    public static String getName(String uri, Namespace namespace) {
        ComplexObject rdfUri = new ComplexObject(uri);
        if (rdfUri.getNamespace().equals(namespace)) {
            return rdfUri.getName();
        } else {
            throw new IllegalArgumentException("Uri has wrong namespace: " + uri);
        }
    }

    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof ComplexObject) {
            ComplexObject otherUri = (ComplexObject) other;
            if (this.namespace.equals(otherUri.namespace) && this.name.equals(otherUri.name)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return this.namespace.hashCode() * this.name.hashCode();
    }

    @Override
    public String toString() {
        return getUri();
    }
}
