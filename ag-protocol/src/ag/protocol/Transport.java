package ag.protocol;

import java.io.IOException;

public interface Transport {
	Frame send(int id, byte[] content, boolean isbinary, boolean req) throws IOException;
	Frame receive() throws IOException;
}
