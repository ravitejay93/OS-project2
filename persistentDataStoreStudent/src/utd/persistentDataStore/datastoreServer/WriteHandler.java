package utd.persistentDataStore.datastoreServer;

import java.io.IOException;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class WriteHandler extends ServerCommand{

	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		String name_file = StreamUtil.readLine(inputStream);
		
		String byte_size = StreamUtil.readLine(inputStream);
		byte[] data = StreamUtil.readData(Integer.valueOf(byte_size), inputStream);
		
		FileUtil.writeData(name_file, data);
		
		// send response
		
		this.sendOK();
	}	

}
