package utd.persistentDataStore.datastoreServer;

import java.io.IOException;
import java.util.List;

import utd.persistentDataStore.datastoreServer.commands.ServerCommand;
import utd.persistentDataStore.utils.FileUtil;
import utd.persistentDataStore.utils.ServerException;
import utd.persistentDataStore.utils.StreamUtil;

public class DirectoryHandler extends ServerCommand {

	@Override
	public void run() throws IOException, ServerException {
		// TODO Auto-generated method stub
		
		List<String> directory = FileUtil.directory();
		
		// send response
		this.sendOK();
		StreamUtil.writeLine(String.valueOf(directory.size()), outputStream);
		
		for(int i = 0; i < directory.size() ;i++){
			StreamUtil.writeLine(directory.get(i), outputStream);
		}
		
	}

}
