package com.vm.app.camel.comp;

import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.remote.SftpComponent;
import org.apache.camel.component.file.remote.SftpConfiguration;
import org.apache.camel.component.file.remote.SftpEndpoint;
import org.apache.camel.component.file.remote.SftpRemoteFile;
import org.apache.camel.runtimecatalog.TimePatternConverter;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("customSftp")
public class CustomSftpComponent extends SftpComponent {

	private static final Logger LOG = LoggerFactory.getLogger(CustomSftpComponent.class);

	@Value("${sftp.username}")
	private String username;

	@Value("${sftp.password}")
	private String password;

	@Value("${sftp.disconnect}")
	private boolean disconnect;

	@Value("${sftp.delay:#{5*60*1000}}")
	private String delay;

	@Value("${sftp.reconnectDelay:#{5*60*1000}}")
	private String reconnectDelay;

	@SuppressWarnings("rawtypes")
	protected void afterPropertiesSet(GenericFileEndpoint<SftpRemoteFile> endpoint) throws Exception {
		LOG.info("-----------------------CustomSftpComponent customize endpoint config for {}----------------------", endpoint);
		SftpEndpoint sftpEndpoint = (SftpEndpoint) endpoint;
		SftpConfiguration config = (SftpConfiguration) sftpEndpoint.getConfiguration();

		// allow username in URI configuration to take precedence
		if (ObjectHelper.isEmpty(config.getUsername())) {
			config.setUsername(username);
		}

		// allow password in URI configuration to take precedence
		if (ObjectHelper.isEmpty(config.getPassword())) {
			config.setPassword(password);
		}

		// allow disconnect in URI configuration to take precedence
		if (!sftpEndpoint.isDisconnect()) {
			sftpEndpoint.setDisconnect(disconnect);
		}

		// allow delay in URI configuration to take precedence. Default Delay is 500ms
		if (sftpEndpoint.getDelay() == 500) {
			sftpEndpoint.setDelay(TimePatternConverter.toMilliSeconds(delay));
		}

		// allow reconnectDelay in URI configuration to take precedence. Default
		// reconnectDelay is 1000ms
		if (sftpEndpoint.getReconnectDelay() == 1000) {
			sftpEndpoint.setReconnectDelay(TimePatternConverter.toMilliSeconds(reconnectDelay));
		}

		LOG.info("-----------------------CustomSftpComponent customize endpoint config for {}----------------------", endpoint);
	}

}
