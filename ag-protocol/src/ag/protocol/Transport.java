package ag.protocol;

import java.io.IOException;

public interface Transport {
	Frame send(byte[] content, boolean isbinary) throws IOException;
	Frame receive() throws IOException;
}
