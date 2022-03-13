# Use RHEL implementation as a parent image
FROM registry.access.redhat.com/ubi8/openjdk-11:1.11

#switch URI if using Canary or Release
z

# Create directory /home/smile in the image and set this as working directory
WORKDIR /home/smile

#Set up config manager for RHEL.  
RUN microdnf install wget gzip && \
    wget -q -t 5 -T 99999 --no-check-certificate https://docker.smilecdr.com/downloads/$URI/$RELEASE_NAME/smilecdr-$RELEASE_NAME.$RELEASE.tar.gz && \
    tar xzf smilecdr-$RELEASE_NAME.$RELEASE.tar.gz && \
    chgrp -R 0 /home/smile && \
    chmod -R g=u /home/smile && \
    rm smilecdr-$RELEASE_NAME.$RELEASE.tar.gz

USER docker

############################## Enable the following to customize the container ##############

# Set the smilecdr folder as working directory
#WORKDIR /home/smile/smilecdr

# Copy modified properties file to the container.
#copy ./cdr-config-Master-modified.properties ./classes/cdr-config-Master.properties

# Copy modified environment settings file to the container.
#copy ./setenv_modified ./bin/setenv

# Add jar files to customerlib folder.
#copy ./sitelib.jar ./customerlib/sitelib.jar

############################################################################################

# Command that will be executed when the Container is launched.
CMD ["/home/smile/smilecdr/bin/smileutil", "run"]