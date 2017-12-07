package org.jiaowei.util;

import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class RepeatedlyReadRequestWrapper extends HttpServletRequestWrapper {

	private static final int BUFFER_START_POSITION = 0;

	private static final int CHAR_BUFFER_LENGTH = 1024;

	/**
	 * input stream 的buffer
	 */
	private final String body;

	/**
	 * @param request
	 *            {@link HttpServletRequest} object.
	 */
	public RepeatedlyReadRequestWrapper(HttpServletRequest request)
			throws UnsupportedEncodingException {
		super(request);

		StringBuilder stringBuilder = new StringBuilder();

		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (inputStream != null) {

			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8" + ""))) {
				char[] charBuffer = new char[CHAR_BUFFER_LENGTH];
				int bytesRead;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, BUFFER_START_POSITION,
							bytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			stringBuilder.append("");
		}
		body = stringBuilder.toString();
		System.out.println("body处理前：   " + body);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				body.getBytes());
		return new DelegatingServletInputStream(byteArrayInputStream);
	}
}
