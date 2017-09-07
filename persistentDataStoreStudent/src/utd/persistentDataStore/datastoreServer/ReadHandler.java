package utd.persistentDataStore.datastoreServer;

import java.io.IOException;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class ReadHandler extends ServerCommand {

	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		
		String name_file = StreamUtil.readLine(inputStream);
		
		byte[] response = FileUtil.readData(name_file);
		
		//send response
		
		this.sendOK(); //send OK
		StreamUtil.writeLine(String.valueOf(response.length), outputStream); // send the size
		StreamUtil.writeData(response, outputStream); //send the data
		
	}

}
