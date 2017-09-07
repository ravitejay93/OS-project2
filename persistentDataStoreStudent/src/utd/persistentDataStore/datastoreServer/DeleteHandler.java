package utd.persistentDataStore.datastoreServer;

import java.io.IOException;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DeleteHandler extends ServerCommand{

	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		
		String name_file = StreamUtil.readLine(inputStream);
		
		FileUtil.deleteData(name_file);
		
		// send response
		this.sendOK();
	}

}
