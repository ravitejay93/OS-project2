package utd.persistentDataStore.datastoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import utd.persistentDataStore.utils.StreamUtil;

public class DatastoreClientImpl implements DatastoreClient
{
	private static Logger logger = Logger.getLogger(DatastoreClientImpl.class);

	private InetAddress address;
	private int port;

	public DatastoreClientImpl(InetAddress address, int port)
	{
		this.address = address;
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#write(java.lang.String, byte[])
	 */
	@Override
    public void write(String name, byte data[]) throws ClientException, ConnectionException
	{
		
		try {
			logger.debug("Executing Write Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("write\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			StreamUtil.writeLine(String.valueOf(data.length), outputStream);
			StreamUtil.writeData(data, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			if(! "ok".equalsIgnoreCase(result)){
				throw new ClientException(result);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConnectionException(e.getMessage(), e);
		}
		
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#read(java.lang.String)
	 */
	@Override
    public byte[] read(String name) throws ClientException, ConnectionException
	{
		
		
		
		try {
			logger.debug("Executing Read Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("read\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			if(! "ok".equalsIgnoreCase(result)){
				throw new ClientException(result);
			}
			
			
			String data_size = StreamUtil.readLine(inputStream);
			
			byte[] data = StreamUtil.readData(Integer.valueOf(data_size), inputStream);
			
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConnectionException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#delete(java.lang.String)
	 */
	@Override
    public void delete(String name) throws ClientException, ConnectionException
	{
		
		try {
			logger.debug("Executing Delete Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("delete\n", outputStream);
			StreamUtil.writeLine(name, outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			if(! "ok".equalsIgnoreCase(result)){
				throw new ClientException(result);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConnectionException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see utd.persistentDataStore.datastoreClient.DatastoreClient#directory()
	 */
	@Override
    public List<String> directory() throws ClientException, ConnectionException
	{
		
		
		try {
			logger.debug("Executing Directory Operation");
			Socket socket = new Socket();
			SocketAddress saddr = new InetSocketAddress(address, port);
			socket.connect(saddr);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			logger.debug("Writing Message");
			StreamUtil.writeLine("directory\n", outputStream);
			
			logger.debug("Reading Response");
			String result = StreamUtil.readLine(inputStream);
			logger.debug("Response " + result);
			if(! "ok".equalsIgnoreCase(result)){
				throw new ClientException(result);
			}
			
			int data_size = Integer.valueOf(StreamUtil.readLine(inputStream));
			List<String> data = new ArrayList<>();
			for(int i = 0;i < data_size;i++){
				data.add(StreamUtil.readLine(inputStream));
			}
			
			return data;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ConnectionException(e.getMessage(), e);
		}
		
	}

}
