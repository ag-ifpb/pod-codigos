package ag.messenger.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ag.messenger.model.Message;
import ag.messenger.model.SharedRepository;


public class SharedRepositoryImpl implements SharedRepository {
	private final static String PATH = "rep/data.txt";
	private final Translate translate;
	
	public SharedRepositoryImpl() {
		translate = new TranslateImpl();
	}

	@Override
	public void store(Message m) {
		File file = new File(PATH);
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			out.write(translate.toJSON(m).getBytes());
			out.write("\n".getBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> select(int id) {
		File file = new File(PATH);
		if (!file.exists()) return new ArrayList<Message>();
		try {
			FileInputStream input = new FileInputStream(file);
			//
			List<String> lines = new ArrayList<String>();
			StringBuffer sb = new StringBuffer();
			while(true){
				byte[] b = new byte[1];
				//
				int r = input.read(b);
				if (r == -1) break;
				if (r ==  0) continue;
				//
				String charact = new String(b);
				if (charact.equals("\n")){
					lines.add(sb.toString());
					sb = new StringBuffer();
				} else {
					sb.append(charact);
				}
			}
			input.close();
			//
			List<Message> list = new ArrayList<Message>();
			for (String l : lines) {
				Message m = translate.fromJSON(l);
				if (m.getId() > id){
					list.add(m);
				}
			}
			//
			return list;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
