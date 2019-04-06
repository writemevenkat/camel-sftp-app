package com.vm.app.camel.comp;

import org.apache.camel.component.file.GenericFileEndpoint;
import org.apache.camel.component.file.remote.SftpComponent;
import org.apache.camel.component.file.remote.SftpConfiguration;
import org.apache.camel.component.file.remote.SftpRemoteFile;
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

	@SuppressWarnings("rawtypes")
	protected void afterPropertiesSet(GenericFileEndpoint<SftpRemoteFile> endpoint) throws Exception {
		LOG.info("-----------------------CustomSftpComponent.afterPropertiesSet----------------------");
		SftpConfiguration config = (SftpConfiguration) endpoint.getConfiguration();
		config.setUsername(username);
		config.setPassword(password);
		LOG.info("-----------------------CustomSftpComponent.afterPropertiesSet----------------------");
	}

}
