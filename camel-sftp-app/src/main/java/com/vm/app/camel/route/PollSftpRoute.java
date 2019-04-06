/**
 * 
 */
package com.vm.app.camel.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author writemevenkat
 *
 */
@Component
public class PollSftpRoute extends RouteBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {

		from("{{sftp.endpoint1}}").routeId("pollSftpRoute1")
				.log(LoggingLevel.INFO, "Downloaded file from input folder 1.")
				.to("file:data/out1");

		from("{{sftp.endpoint2}}").routeId("pollSftpRoute2")
				.log(LoggingLevel.INFO, "Downloaded file from input folder 2.")
				.to("file:data/out2");

	}

}
