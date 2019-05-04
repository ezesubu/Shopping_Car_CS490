package edu.mum.cs490.shoppingcart.utility;
import java.io.*;
import javax.activation.*;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class BufferedDataSourceUtility implements DataSource {

    private byte[] _data;
    private java.lang.String _name;

    public BufferedDataSourceUtility(byte[] data, String name) {
        _data = data;
        _name = name;
    }

    public String getContentType() { return "application/pdf";}
    public InputStream getInputStream() throws IOException { return new ByteArrayInputStream(_data);}
    public String getName() { return _name;}

    /**
     * Returns an OutputStream from the DataSource
     * @returns OutputStream Array of bytes converted into an OutputStream
     */
    public OutputStream getOutputStream() throws IOException {
        OutputStream out = new ByteArrayOutputStream();
        out.write(_data);
        return out;
    }
}
