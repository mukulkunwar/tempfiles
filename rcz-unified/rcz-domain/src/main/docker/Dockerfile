FROM openjdk:17-jdk-alpine

# Set work directory for runtime (Spring will look here for config/log files)
WORKDIR /opt/etc
# Create log directory to avoid permission issues
RUN mkdir -p /tmp/logs
EXPOSE 8080

# Copy all app resources and JAR from TeamCity build (maven/opt → /opt)
COPY maven/opt /opt

# Download APM agent if needed
ADD http://repository.inetpsa.com/maven-virtual/co/elastic/apm/elastic-apm-agent/1.37.0/elastic-apm-agent-1.37.0.jar /opt/bin/apm/elastic-apm-agent.jar

# Launch app with Java options and Spring Boot JAR
CMD exec java $JAVA_OPTS -jar /opt/bin/rcz-domain.jar
